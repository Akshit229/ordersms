package com.quicken.ordersms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class OrdersmsApplication {
	public static void main(String[] args) {
		SpringApplication.run(OrdersmsApplication.class, args);
	}
}
