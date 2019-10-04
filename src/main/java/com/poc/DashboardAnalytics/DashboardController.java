package com.poc.DashboardAnalytics;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by asingh on 1/14/19.
 */
@RestController
public class DashboardController {
private static final String template = "Hello ,%sa!";
    private final AtomicLong counter = new AtomicLong();
    @RequestMapping("/dashboard")
    public Metrics dashboard(@RequestParam(value = "name",defaultValue = "World") String name){
        return new Metrics(counter.incrementAndGet(),String.format(template,name));
    }
}
