package com.example.springboot;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class WorkerBean implements AutoCloseable {
    ExecutorService executor = Executors.newFixedThreadPool(1);
    @PostConstruct
    public void init() throws Exception {
        executor.execute(new BusyWorkerTask());

    }


    @Override
    public void close() throws Exception {
        executor.shutdownNow();
    }
}
