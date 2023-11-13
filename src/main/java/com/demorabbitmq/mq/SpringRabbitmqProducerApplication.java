package com.demorabbitmq.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringRabbitmqProducerApplication {
	public static void main(String[] args)
	{
		System.out.println("Hi");
		SpringApplication.run(SpringRabbitmqProducerApplication.class, args);
	}

}
