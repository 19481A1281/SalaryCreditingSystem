package com.banking.SalaryCreditingSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.logging.Logger;

@SpringBootApplication
@EnableScheduling
public class SalaryCreditingSystemApplication {
	private final Logger logger = Logger.getLogger(SalaryCreditingSystemApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(SalaryCreditingSystemApplication.class, args);
	}

}
