package com.banking.online.banking.operations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.banking.online.banking.operations.service"})
public class OnlineBankingOperationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineBankingOperationsApplication.class, args);
	}

}
