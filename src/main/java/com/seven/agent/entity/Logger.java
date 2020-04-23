package com.seven.agent.entity;

import java.util.Date;

/**
 * @Description: TODO
 * @Author chendongdong
 * @Date 2019/10/10 14:15
 * @Version V1.0
 **/
public class Logger {
    private String app;
    private String logClass;
    private String logLevel;
    private String classFull;
    private String logInfo;
    private Date date;

    public Logger(String app, String logClass, String logLevel, String classFull, String logInfo) {
        this.app = app;
        this.logClass = logClass;
        this.logLevel = logLevel;
        this.classFull = classFull;
        this.logInfo = logInfo;
        this.date=new Date();
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getLogClass() {
        return logClass;
    }

    public void setLogClass(String logClass) {
        this.logClass = logClass;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public String getClassFull() {
        return classFull;
    }

    public void setClassFull(String classFull) {
        this.classFull = classFull;
    }

    public String getLogInfo() {
        return logInfo;
    }

    public void setLogInfo(String logInfo) {
        this.logInfo = logInfo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "[日志收集]{" +
                "app='" + app + '\'' +
                ", logClass='" + logClass + '\'' +
                ", logLevel='" + logLevel + '\'' +
                ", classFull='" + classFull + '\'' +
                ", logInfo='" + logInfo + '\'' +
                '}';
    }
}
