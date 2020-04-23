package com.seven.agent.bean;

import com.seven.agent.utils.Metric;

import java.lang.management.*;

public class JvmMemoryInfoDO extends BaseDO{
    private long maxMemory;
    private long usedMemory;
    private long committedMemory;

    private long nonMaxMemory;
    private long nonUsedMemory;
    private long nonCommittedMemory;

    private long edenMaxMemory;
    private long edenUsedMemory;
    private long edenCommittedMemory;

    private long oldGenMaxMemory;
    private long oldGenUsedMemory;
    private long oldGenCommittedMemory;

    private long survivorSpaceMaxMemory;
    private long survivorSpaceUsedMemory;
    private long survivorSpaceCommittedMemory;

    private long permGenMaxMemory;
    private long permGenUsedMemory;
    private long permGenCommittedMemory;

    private long threadCount;

    private long loadedClassCount;
    private long totalLoadedClassCount;
    private long unLoadClassCount;

    public JvmMemoryInfoDO() {
        init();
    }

    public long getMaxMemory() {
        return maxMemory;
    }

    public void setMaxMemory(long maxMemory) {
        this.maxMemory = maxMemory;
    }

    public long getUsedMemory() {
        return usedMemory;
    }

    public void setUsedMemory(long usedMemory) {
        this.usedMemory = usedMemory;
    }

    public long getCommittedMemory() {
        return committedMemory;
    }

    public void setCommittedMemory(long committedMemory) {
        this.committedMemory = committedMemory;
    }

    public long getNonMaxMemory() {
        return nonMaxMemory;
    }

    public void setNonMaxMemory(long nonMaxMemory) {
        this.nonMaxMemory = nonMaxMemory;
    }

    public long getNonUsedMemory() {
        return nonUsedMemory;
    }

    public void setNonUsedMemory(long nonUsedMemory) {
        this.nonUsedMemory = nonUsedMemory;
    }

    public long getNonCommittedMemory() {
        return nonCommittedMemory;
    }

    public void setNonCommittedMemory(long nonCommittedMemory) {
        this.nonCommittedMemory = nonCommittedMemory;
    }

    public long getEdenMaxMemory() {
        return edenMaxMemory;
    }

    public void setEdenMaxMemory(long edenMaxMemory) {
        this.edenMaxMemory = edenMaxMemory;
    }

    public long getEdenUsedMemory() {
        return edenUsedMemory;
    }

    public void setEdenUsedMemory(long edenUsedMemory) {
        this.edenUsedMemory = edenUsedMemory;
    }

    public long getEdenCommittedMemory() {
        return edenCommittedMemory;
    }

    public void setEdenCommittedMemory(long edenCommittedMemory) {
        this.edenCommittedMemory = edenCommittedMemory;
    }

    public long getOldGenMaxMemory() {
        return oldGenMaxMemory;
    }

    public void setOldGenMaxMemory(long oldGenMaxMemory) {
        this.oldGenMaxMemory = oldGenMaxMemory;
    }

    public long getOldGenUsedMemory() {
        return oldGenUsedMemory;
    }

    public void setOldGenUsedMemory(long oldGenUsedMemory) {
        this.oldGenUsedMemory = oldGenUsedMemory;
    }

    public long getOldGenCommittedMemory() {
        return oldGenCommittedMemory;
    }

    public void setOldGenCommittedMemory(long oldGenCommittedMemory) {
        this.oldGenCommittedMemory = oldGenCommittedMemory;
    }

    public long getSurvivorSpaceMaxMemory() {
        return survivorSpaceMaxMemory;
    }

    public void setSurvivorSpaceMaxMemory(long survivorSpaceMaxMemory) {
        this.survivorSpaceMaxMemory = survivorSpaceMaxMemory;
    }

    public long getSurvivorSpaceUsedMemory() {
        return survivorSpaceUsedMemory;
    }

    public void setSurvivorSpaceUsedMemory(long survivorSpaceUsedMemory) {
        this.survivorSpaceUsedMemory = survivorSpaceUsedMemory;
    }

    public long getSurvivorSpaceCommittedMemory() {
        return survivorSpaceCommittedMemory;
    }

    public void setSurvivorSpaceCommittedMemory(long survivorSpaceCommittedMemory) {
        this.survivorSpaceCommittedMemory = survivorSpaceCommittedMemory;
    }

    public long getPermGenMaxMemory() {
        return permGenMaxMemory;
    }

    public void setPermGenMaxMemory(long permGenMaxMemory) {
        this.permGenMaxMemory = permGenMaxMemory;
    }

    public long getPermGenUsedMemory() {
        return permGenUsedMemory;
    }

    public void setPermGenUsedMemory(long permGenUsedMemory) {
        this.permGenUsedMemory = permGenUsedMemory;
    }

    public long getPermGenCommittedMemory() {
        return permGenCommittedMemory;
    }

    public void setPermGenCommittedMemory(long permGenCommittedMemory) {
        this.permGenCommittedMemory = permGenCommittedMemory;
    }

    public long getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(long threadCount) {
        this.threadCount = threadCount;
    }

    public long getLoadedClassCount() {
        return loadedClassCount;
    }

    public void setLoadedClassCount(long loadedClassCount) {
        this.loadedClassCount = loadedClassCount;
    }

    public long getTotalLoadedClassCount() {
        return totalLoadedClassCount;
    }

    public void setTotalLoadedClassCount(long totalLoadedClassCount) {
        this.totalLoadedClassCount = totalLoadedClassCount;
    }

    public long getUnLoadClassCount() {
        return unLoadClassCount;
    }

    public void setUnLoadClassCount(long unLoadClassCount) {
        this.unLoadClassCount = unLoadClassCount;
    }

    public void init() {
        MemoryMXBean memory = ManagementFactory.getMemoryMXBean();
        final MemoryUsage headMemory = memory.getHeapMemoryUsage();
        this.maxMemory = headMemory.getMax();
        this.usedMemory = headMemory.getUsed();
        this.committedMemory = headMemory.getCommitted();
        final ThreadMXBean threadInfo = ManagementFactory.getThreadMXBean();
        this.threadCount = threadInfo.getThreadCount();
        MemoryUsage nonHeadMemory = memory.getNonHeapMemoryUsage();
        this.nonMaxMemory = nonHeadMemory.getMax();
        this.nonUsedMemory = nonHeadMemory.getUsed();
        this.nonCommittedMemory = nonHeadMemory.getCommitted();

        MemoryPoolMXBean edenSpaceMemoryPool = Metric.getEdenSpacePool();
        if (edenSpaceMemoryPool != null) {
            final MemoryUsage usage = edenSpaceMemoryPool.getUsage();
            this.edenMaxMemory = usage.getMax();
            this.edenUsedMemory = usage.getUsed();
            this.edenCommittedMemory = usage.getCommitted();
        }

        MemoryPoolMXBean survivorSpaceMemoryPool = Metric.getSurvivorSpaceMemoryPool();
        if (survivorSpaceMemoryPool != null) {
            final MemoryUsage usage = survivorSpaceMemoryPool.getUsage();
            this.survivorSpaceMaxMemory = usage.getMax();
            this.survivorSpaceUsedMemory = usage.getUsed();
            this.survivorSpaceCommittedMemory = usage.getCommitted();
        }

        MemoryPoolMXBean oldGenMemoryPool = Metric.getOldGenMemoryPool();
        if (oldGenMemoryPool != null) {
            final MemoryUsage usage = oldGenMemoryPool.getUsage();
            this.oldGenMaxMemory = usage.getMax();
            this.oldGenUsedMemory = usage.getUsed();
            this.oldGenCommittedMemory = usage.getCommitted();
        }

        MemoryPoolMXBean permGenMemoryPool = Metric.getPermGenMemoryPool();
        if (permGenMemoryPool != null) {
            final MemoryUsage usage = permGenMemoryPool.getUsage();
            this.permGenMaxMemory = usage.getMax();
            this.permGenUsedMemory = usage.getUsed();
            this.permGenCommittedMemory = usage.getCommitted();
        }
        ClassLoadingMXBean classLoadingMXBean = ManagementFactory.getClassLoadingMXBean();
        this.loadedClassCount = classLoadingMXBean.getLoadedClassCount();
        this.totalLoadedClassCount = classLoadingMXBean.getTotalLoadedClassCount();
        this.unLoadClassCount = classLoadingMXBean.getUnloadedClassCount();
    }
}
