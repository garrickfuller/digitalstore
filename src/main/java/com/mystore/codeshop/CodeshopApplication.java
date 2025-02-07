package com.mystore.codeshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = "com.mystore.codeshop")
public class CodeshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeshopApplication.class, args);
	}

}
