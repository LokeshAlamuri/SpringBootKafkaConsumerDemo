package com.example.springboot;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MessageConsumer {

	AtomicInteger atomicInteger = new AtomicInteger();

	@KafkaListener(topics = "test0", groupId = "test", concurrency = "2")
	public void processMessage(ConsumerRecord<String, String> record) {
		System.out.println("MessageConsumer record key::" + record.key());
		System.out.println("MessageConsumer record partition::" + record.partition());
		System.out.println("MessageConsumer record offset::" + record.offset());

		if(atomicInteger.incrementAndGet() >=1)
			throw new TestFatalException();

	}
	

}
