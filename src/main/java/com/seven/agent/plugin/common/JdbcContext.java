package com.seven.agent.plugin.common;


import com.seven.agent.bean.Spanx;

public class JdbcContext {
    private static final ThreadLocal<Spanx> localJdbcSpan = new ThreadLocal<Spanx>();

    public static void remove() {
        localJdbcSpan.remove();
    }

    public static Spanx getJdbcSpan() {
        return localJdbcSpan.get();
    }

    public static void setJdbcSpan(Spanx span) {
        localJdbcSpan.set(span);
    }
}
