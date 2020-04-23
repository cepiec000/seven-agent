package com.seven.agent.plugin.jdbc;

import com.alibaba.fastjson.JSON;
import com.seven.agent.track.Span;
import com.seven.agent.track.TrackContext;
import com.seven.agent.track.TrackManager;
import com.seven.agent.utils.LogStackString;
import net.bytebuddy.asm.Advice;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.UUID;

public class PreparedStatementExecuteAdvice {
    @Advice.OnMethodEnter()
    public static void enter(
            @Advice.Origin Class className,
            @Advice.Origin Method method,
            @Advice.AllArguments Object[] allParams) {
        System.out.println("[SQL执行监控]: start: " + className);
        Span currentSpan = TrackManager.getCurrentSpan();
        if (null == currentSpan) {
            String linkId = UUID.randomUUID().toString();
            TrackContext.setLinkId(linkId);
        }
        TrackManager.createEntrySpan();
    }


    /**
     * 如果需要返回值，在方法里添加注解和参数@Advice.Return(readOnly = false) Object result,result的类型要和实际返回值类型一致,需要修改参数readOnly置为false
     */
    @Advice.OnMethodExit(onThrowable = Throwable.class)
    public static void exit(
            @Advice.Origin("#t") String className,
            @Advice.Origin Method method,
            @Advice.AllArguments Object[] allParams,
            @Advice.Thrown Throwable t) {
        Span exitSpan = TrackManager.getExitSpan();
        if (null == exitSpan) return;
        if (t != null) {
            System.out.println("[SQL执行监控]：异常=" + LogStackString.errInfo(t));
        }
        System.out.println("[SQL执行监控]: method:" + method.getName());
        toString(allParams);
        System.out.println("[SQL执行监控]: end: " + className + " 耗时：" + (System.currentTimeMillis() - exitSpan.getEnterTime().getTime()) + "ms");
    }

    public static void toString(Object[] a) {
        if (a == null)
            return;
        String id = null;
        String sql = null;
        try {
            for (int i = 0;i<a.length ; i++) {
                Object object = a[i];
                if (String.valueOf(object).contains("MappedStatement")) {
                    Class<?> userClass = Class.forName("org.apache.ibatis.mapping.MappedStatement");
                    Method getId = userClass.getMethod("getId");//得到方法对象
                    id = (String) getId.invoke(object, null);
                }

                if (String.valueOf(object).contains("BoundSql")) {
                    Class<?> userClass = Class.forName("org.apache.ibatis.mapping.BoundSql");
                    Method getSql = userClass.getMethod("getSql");//得到方法对象
                    sql = (String) getSql.invoke(object, null);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println("[SQL执行监控]: 方法:" + id + " SQL:" + sql);
    }



}
