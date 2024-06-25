package com.example.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.kafka.event.ConsumerStoppedEvent;
import org.springframework.kafka.listener.ListenerContainerRegistry;
import org.springframework.stereotype.Component;

@Component
public class ConsumerStoppedEventListener implements ApplicationListener<ConsumerStoppedEvent> {

    @Autowired
    ListenerContainerRegistry listenerContainerRegistry;

    @Autowired
    ConfigurableApplicationContext applicationContext;


    @Override
    public void onApplicationEvent(ConsumerStoppedEvent event) {


        System.out.println("Enter Consumer stopped Event Listener"+Thread.currentThread().getName()
            + ":" + listenerContainerRegistry.getListenerContainers().stream().
                filter(messageListenerContainer -> messageListenerContainer.isRunning()).count() +
                ":" + listenerContainerRegistry.getListenerContainers().size());
        if(listenerContainerRegistry.getListenerContainers().stream().
                filter(messageListenerContainer -> messageListenerContainer.isRunning()).count() == 0)
            applicationContext.close();
        System.out.println("Exit Consumer stopped Event Listener");
    }
}
