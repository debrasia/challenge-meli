package com.api.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.api.controller", "com.api.service"})
public class ChallengeMeliApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeMeliApplication.class, args);
	}

}
