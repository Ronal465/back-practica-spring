package com.back.practique.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
@ComponentScan({
		ApplicationConfigurationServiceApplication.MAIN_PACKAGE })
public class ApplicationConfigurationServiceApplication {

	public static final String MAIN_PACKAGE = "com.back.practique.springboot";
	public static final String REPO_PACKAGE = "com.back.practique.springboot.repositories";

	public static void main(String[] args) {
		SpringApplication.run(ApplicationConfigurationServiceApplication.class, args);
	}

}
