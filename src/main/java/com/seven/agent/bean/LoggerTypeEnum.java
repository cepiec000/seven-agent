package com.seven.agent.bean;

/**
 * OFF、FATAL、ERROR、WARN、INFO、DEBUG、TRACE、 ALL
 * Created by chendongdong on 2019/5/7.
 */
public enum LoggerTypeEnum {
    LOGGER(0, "LOGGER"),
    METHOD (1, "METHOD"),
    SQL (2, "SQL");

    private final Integer code;
    private final String name;

    LoggerTypeEnum(final Integer code, final String name) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Integer getCode() {
        return this.code;
    }

    public static LoggerTypeEnum valueOf(Integer code) {
        if (code == null) {
            return null;
        }
        for (LoggerTypeEnum loggerTypeEnum : values()) {
            if (loggerTypeEnum.getCode() == code) {
                return loggerTypeEnum;
            }
        }
        return null;
    }
}
