package com.seven.agent;


import com.seven.agent.bean.FieldDefine;
import com.seven.agent.plugin.AbstractPlugin;
import com.seven.agent.plugin.InterceptPoint;
import com.seven.agent.plugin.PluginLoader;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;
import java.util.List;

public class MonitorMain {

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("---------------------------------Welcome Seven Monitor ---------------------------------------");
        linkIntercept(agentArgs,inst);

    }


    private static void linkIntercept(String agentArgs, Instrumentation inst) {

        List<AbstractPlugin> plugins = PluginLoader.loadPlugins();
        AgentBuilder agentBuilder = new AgentBuilder.Default().ignore(ElementMatchers.nameStartsWith("com.seven.agent."));
        for (int i = 0; i < plugins.size(); i++) {
            final AbstractPlugin plugin = plugins.get(i);
            final InterceptPoint interceptPoint = plugin.buildInterceptPoint();
            AgentBuilder.Transformer transformer = new AgentBuilder.Transformer() {
                @Override
                public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder,
                                                        TypeDescription typeDescription,
                                                        ClassLoader classLoader, JavaModule javaModule) {
                    builder = builder.visit(Advice.to(plugin.interceptorAdviceClass()).on(interceptPoint.buildMethodsMatcher()));
                    FieldDefine[] fields = plugin.buildFieldDefine();
                    if (fields != null && fields.length > 0) {
                        for (int x = 0; x < fields.length; x++) {
                            builder = builder.defineField(fields[x].name, fields[x].type, fields[x].modifiers);
                        }
                    }
                    return builder;
                }
            };
            agentBuilder = agentBuilder.type(interceptPoint.buildTypesMatcher()).transform(transformer);
        }

        //监听
        AgentBuilder.Listener listener = new AgentBuilder.Listener() {
            @Override
            public void onDiscovery(String s, ClassLoader classLoader, JavaModule javaModule, boolean b) {

            }

            @Override
            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean b, DynamicType dynamicType) {
//                System.out.println("onTransformation：" + typeDescription);
                //修改后的类输出
            }

            @Override
            public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean b) {

            }

            @Override
            public void onError(String s, ClassLoader classLoader, JavaModule javaModule, boolean b, Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onComplete(String s, ClassLoader classLoader, JavaModule javaModule, boolean b) {

            }

        };

        agentBuilder.with(listener).installOn(inst);
    }

}
