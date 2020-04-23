package com.seven.agent.plugin;

public interface IPlugin {
    //名称
    String getName();

    //监控点
    InterceptPoint buildInterceptPoint();

    //拦截器类
    Class interceptorAdviceClass();
}
