package com.dev3.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.dev3.app")
public class WeChatAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeChatAppApplication.class, args);
	}
}
