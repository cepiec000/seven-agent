package com.seven.agent.bean;

import com.seven.agent.utils.StringUtils;

import java.lang.management.ManagementFactory;
import java.util.List;

public class JvmInfoDO extends BaseDO{
    private String javaVersion;
    private String jreHome;
    private String javaVmVersion;
    private String javaVmName;
    private String javaClassPath;
    private String javaLibraryPath;
    private String javaVmArguments;

    public JvmInfoDO() {
        init();
    }

    public String getJavaVersion() {
        return javaVersion;
    }

    public void setJavaVersion(String javaVersion) {
        this.javaVersion = javaVersion;
    }

    public String getJreHome() {
        return jreHome;
    }

    public void setJreHome(String jreHome) {
        this.jreHome = jreHome;
    }

    public String getJavaVmVersion() {
        return javaVmVersion;
    }

    public void setJavaVmVersion(String javaVmVersion) {
        this.javaVmVersion = javaVmVersion;
    }

    public String getJavaVmName() {
        return javaVmName;
    }

    public void setJavaVmName(String javaVmName) {
        this.javaVmName = javaVmName;
    }

    public String getJavaClassPath() {
        return javaClassPath;
    }

    public void setJavaClassPath(String javaClassPath) {
        this.javaClassPath = javaClassPath;
    }

    public String getJavaLibraryPath() {
        return javaLibraryPath;
    }

    public void setJavaLibraryPath(String javaLibraryPath) {
        this.javaLibraryPath = javaLibraryPath;
    }

    public String getJavaVmArguments() {
        return javaVmArguments;
    }

    public void setJavaVmArguments(String javaVmArguments) {
        this.javaVmArguments = javaVmArguments;
    }

    public void init(){
        javaVersion = System.getProperty("java.version");
        jreHome = System.getProperty("java.home");
        javaVmVersion = System.getProperty("java.vm.version");
        javaVmName = System.getProperty("java.vm.name");
        javaClassPath = System.getProperty("java.class.path");
        javaLibraryPath = System.getProperty("java.library.path");
        List<String> inputArguments = ManagementFactory.getRuntimeMXBean().getInputArguments();
        javaVmArguments = StringUtils.listToString(inputArguments);
    }

}
