package com.seven.agent.plugin.logger;

import com.seven.agent.entity.Logger;
import com.seven.agent.utils.LogStackString;
import net.bytebuddy.asm.Advice;

import java.util.ArrayList;
import java.util.Arrays;

public class LoggerAdvice {
    @Advice.OnMethodEnter()
    public static void enter(
            @Advice.Origin("#t") String className,
            @Advice.Origin("#m") String methodName,
            @Advice.AllArguments Object[] allParams,
            @Advice.FieldValue("name") String name) {
        if ("error".equals(methodName) || "info".equals(methodName)) {
            Logger logger = new Logger("business-api", className, methodName, name, getLoggerInfo(allParams));
            System.out.println(logger.toString());
        }
    }

    public static String getLoggerInfo(Object[] allArguments) {
        StringBuilder logBuff = new StringBuilder();
        for (int i = 0; i < allArguments.length; i++) {
            Object arg = allArguments[i];
            if (i > 0) {
                logBuff.append(" | ");
            }
            if (arg == null) {
                continue;
            }
            if (arg instanceof Throwable) {
                logBuff.append(LogStackString.errInfo((Throwable) arg));
            } else if (arg.getClass().isArray()) {
                Object[] array = (Object[]) arg;
                logBuff.append(Arrays.toString(array));
            } else {
                logBuff.append(String.valueOf(arg));
            }
        }
        return logBuff.toString();
    }


}
