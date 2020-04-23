package com.seven.agent.plugin.link;

import com.seven.agent.plugin.AbstractPlugin;
import com.seven.agent.plugin.InterceptPoint;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

public class LinkPlugin extends AbstractPlugin {
    @Override
    public String getName() {
        return "link";
    }

    @Override
    public InterceptPoint buildInterceptPoint() {
        return  new InterceptPoint() {
                    @Override
                    public ElementMatcher<TypeDescription> buildTypesMatcher() {
                        return ElementMatchers.nameStartsWith("com.zhidao.oper.business")
                                .and(ElementMatchers.not(ElementMatchers.nameContains("$")));
                    }
                    @Override
                    public ElementMatcher<MethodDescription> buildMethodsMatcher() {
                        return ElementMatchers.isMethod()
                                .and(ElementMatchers.any())
                                .and(ElementMatchers.not(ElementMatchers.nameStartsWith("main")))
                                .and(ElementMatchers.not(ElementMatchers.nameStartsWith("get")))
                                .and(ElementMatchers.not(ElementMatchers.nameStartsWith("set")))
                                .and(ElementMatchers.not(ElementMatchers.named("toString")))
                                .and(ElementMatchers.not(ElementMatchers.nameContains("$")));
                    }

        };
    }

    @Override
    public Class interceptorAdviceClass() {
        return LinkAdvice.class;
    }

}
