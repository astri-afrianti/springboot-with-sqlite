package com.mitrais.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.mitrais.blog"})
public class MainApplicationClass {
	
	public static void main(String[] args) {
		SpringApplication.run(MainApplicationClass.class, args);
	}
}
