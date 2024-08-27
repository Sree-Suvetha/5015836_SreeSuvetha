package com.example.BookStoreAPI.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.stereotype.Component;

@Component
public class CustomMetrics implements MeterBinder {

    @Override
    public void bindTo(MeterRegistry registry) {
        // Register custom metrics here
        registry.gauge("custom.metric", 1);  // Example metric
    }
}
