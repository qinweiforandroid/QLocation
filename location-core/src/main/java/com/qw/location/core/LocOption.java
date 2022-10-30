package com.qw.location.core;

/**
 * create by qinwei at 2022/10/30 13:59
 */
public class LocOption {
    /**
     * 单次定位
     */
    private boolean once;
    /**
     * 返回精度最高
     */
    private boolean onceLocationLatest;

    /**
     * 定位间隔时间
     */
    private long interval;

    /**
     * 是否需要解析地址
     */
    private boolean needAddress;

    public boolean isOnce() {
        return once;
    }

    public LocOption setOnce(boolean once) {
        this.once = once;
        return this;
    }

    public boolean isOnceLocationLatest() {
        return onceLocationLatest;
    }

    public LocOption setOnceLocationLatest(boolean onceLocationLatest) {
        this.onceLocationLatest = onceLocationLatest;
        return this;
    }

    public long getInterval() {
        return interval;
    }

    public LocOption setInterval(long interval) {
        this.interval = interval;
        return this;
    }

    public boolean isNeedAddress() {
        return needAddress;
    }

    public LocOption setNeedAddress(boolean needAddress) {
        this.needAddress = needAddress;
        return this;
    }

    public static LocOption genOnceOption() {
        return new LocOption()
                .setOnce(true)
                .setOnceLocationLatest(true)
                .setNeedAddress(true)
                .setInterval(2000);
    }
}