package com.seven.agent.plugin.link;

import com.seven.agent.track.Span;
import com.seven.agent.track.TrackContext;
import com.seven.agent.track.TrackManager;
import com.seven.agent.utils.LogStackString;
import net.bytebuddy.asm.Advice;

import java.util.UUID;

public class LinkAdvice {

    @Advice.OnMethodEnter()
    public static void enter(@Advice.Origin("#t") String className, @Advice.Origin("#m") String methodName) {
        Span currentSpan = TrackManager.getCurrentSpan();
        if (null == currentSpan) {
            String linkId = UUID.randomUUID().toString();
            TrackContext.setLinkId(linkId);
        }
        TrackManager.createEntrySpan();
    }

    @Advice.OnMethodExit(onThrowable = Throwable.class)
    public static void exit(@Advice.Origin("#t") String className, @Advice.Origin("#m") String methodName,@Advice.Thrown Throwable t) {
        Span exitSpan = TrackManager.getExitSpan();
        if (null == exitSpan) return;
        if (t!=null){
            System.out.println("链路追踪异常:"+LogStackString.errInfo(t));
        }
        System.out.println("链路追踪(MQ)：" + exitSpan.getLinkId() + " " + className + "." + methodName + " 耗时：" + (System.currentTimeMillis() - exitSpan.getEnterTime().getTime()) + "ms");
    }


//    @Advice.OnMethodEnter()
//    public static void exit(
//            @Advice.Origin("#t") String className,
//            @Advice.Origin("#m") String methodName,
//            @Advice.AllArguments Object[] allParams
//    ) {
//        Span currentSpan = TrackManager.getCurrentSpan();
//        if (null == currentSpan) {
//            String linkId = UUID.randomUUID().toString();
//            TrackContext.setLinkId(linkId);
//        }
//        TrackManager.createEntrySpan();
//        System.out.println("params="+JSON.toJSONString(allParams));
//    }
//
//    @Advice.OnMethodExit(onThrowable = Throwable.class)
//    public static void exit(
//            @Advice.Origin("#t") String className,
//            @Advice.Origin("#m") String methodName,
//            @Advice.AllArguments Object[] allParams,
//            @Advice.Thrown Throwable t,
//            @Advice.Return Object returnValue,
//            @Advice.FieldValue(value = "body") String body) {
//        Span exitSpan = TrackManager.getExitSpan();
//        if (null == exitSpan) return;
//        System.out.println("链路追踪(MQ)：" + exitSpan.getLinkId() + " " + className + "." + methodName + " 耗时：" + (System.currentTimeMillis() - exitSpan.getEnterTime().getTime()) + "ms");
//        System.out.println("returnValue="+returnValue);
//
//    }
}
