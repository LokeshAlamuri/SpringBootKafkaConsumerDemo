package com.example.springboot;

import org.springframework.beans.factory.InitializingBean;

public class BusyWorkerTask implements Runnable {
    @Override
    public void run() {
        int i = 10;
        while (1 < i) {
            // do nothing
            if(Thread.currentThread().isInterrupted())
                break;
        }
    }
}
