package com.poc.DashboardAnalytics;

/**
 * Created by asingh on 1/14/19.
 */
public class Metrics {

    private final long id;
    private final String content;

    public Metrics(long id,String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

}
