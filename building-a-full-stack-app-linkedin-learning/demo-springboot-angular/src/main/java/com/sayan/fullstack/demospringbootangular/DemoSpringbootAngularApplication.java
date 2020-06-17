package com.sayan.fullstack.demospringbootangular;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class DemoSpringbootAngularApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringbootAngularApplication.class, args);
	}

}
