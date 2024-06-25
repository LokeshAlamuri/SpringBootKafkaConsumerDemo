package com.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.task.ThreadPoolTaskSchedulerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.listener.CommonContainerStoppingErrorHandler;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.ListenerContainerPauseService;
import org.springframework.kafka.listener.ListenerContainerRegistry;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public TaskScheduler taskScheduler() {
		ThreadPoolTaskSchedulerBuilder threadPoolTaskSchedulerBuilder =
				new ThreadPoolTaskSchedulerBuilder().poolSize(2);

		ThreadPoolTaskScheduler threadPoolTaskScheduler = threadPoolTaskSchedulerBuilder.build();
		return threadPoolTaskScheduler;
	}




	@Bean
	public ListenerContainerPauseService listenerContainerPauseService(ListenerContainerRegistry registry,
																	   TaskScheduler scheduler){
		ListenerContainerPauseService listenerContainerPauseService =
				new ListenerContainerPauseService(registry, scheduler);
		return listenerContainerPauseService;
	}

	@Bean
	public CommonErrorHandler defaultErrorHandler(ListenerContainerPauseService listenerContainerPauseService) {
		CommonContainerStoppingErrorHandler commonContainerStoppingErrorHandler =
				new CommonContainerStoppingErrorHandler();
		return commonContainerStoppingErrorHandler;
	}

}
