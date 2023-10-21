package com.example.streamservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import reactor.tools.agent.ReactorDebugAgent;

@SpringBootApplication
@EnableDiscoveryClient
public class StreamServiceApplication {

	public static void main(String[] args) {
		ReactorDebugAgent.init();
		SpringApplication.run(StreamServiceApplication.class, args);
	}

}
