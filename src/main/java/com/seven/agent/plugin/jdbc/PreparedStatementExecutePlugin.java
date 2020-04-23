package com.seven.agent.plugin.jdbc;

import com.seven.agent.plugin.AbstractPlugin;
import com.seven.agent.plugin.InterceptPoint;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

public class PreparedStatementExecutePlugin extends AbstractPlugin {
    @Override
    public String getName() {
        return "jdbc-statement-execute";
    }


    @Override
    public InterceptPoint buildInterceptPoint() {
        return new InterceptPoint() {
                    @Override
                    public ElementMatcher<TypeDescription> buildTypesMatcher() {
                        return ElementMatchers.<TypeDescription>named("org.apache.ibatis.executor.BatchExecutor")
                                .or(ElementMatchers.named("org.apache.ibatis.executor.SimpleExecutor"))
                                .or(ElementMatchers.named("org.apache.ibatis.executor.ReuseExecutor"))
                                .or(ElementMatchers.named("org.apache.ibatis.executor.BaseExecutor"))
                                .or(ElementMatchers.named("com.baomidou.mybatisplus.core.executor.MybatisSimpleExecutor"))
                                .or(ElementMatchers.named("com.baomidou.mybatisplus.core.executor.MybatisBatchExecutor"))
                                .or(ElementMatchers.named("com.baomidou.mybatisplus.core.executor.MybatisReuseExecutor"));
                    }
                    @Override
                    public ElementMatcher<MethodDescription> buildMethodsMatcher() {
                        return ElementMatchers.any()
                                .and(ElementMatchers.named("query"))
                                .or(ElementMatchers.named("update"))
                                .or(ElementMatchers.named("queryCursor"))
                                .and(ElementMatchers.<MethodDescription>isPublic());
                    }
        };
    }

    @Override
    public Class interceptorAdviceClass() {
        return PreparedStatementExecuteAdvice.class;
    }
}
