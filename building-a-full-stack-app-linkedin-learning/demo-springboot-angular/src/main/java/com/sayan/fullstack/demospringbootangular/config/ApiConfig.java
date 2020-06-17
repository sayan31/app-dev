package com.sayan.fullstack.demospringbootangular.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Configuration
public class ApiConfig {
	
	/**
	 * ObjectMapper is used to map JSON data 
	 * from the request body to Java objects
	 * @return an instance of the ObjectMapper class
	 */
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
	
	/**
	 * ObjectWriter is used to create JSON strings
	 * in the response body, from Java objects.
	 * @param objectMapper
	 * @return
	 */
	@Bean
	public ObjectWriter objectWriter(ObjectMapper objectMapper) {
		return objectMapper.writerWithDefaultPrettyPrinter();
	}
}
