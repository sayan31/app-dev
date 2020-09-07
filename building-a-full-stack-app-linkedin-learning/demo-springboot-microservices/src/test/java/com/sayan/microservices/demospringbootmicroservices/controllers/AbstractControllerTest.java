package com.sayan.microservices.demospringbootmicroservices.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sayan.microservices.demospringbootmicroservices.dto.BookDto;
import com.sayan.microservices.demospringbootmicroservices.utils.interfaces.CreateTestData;

public abstract class AbstractControllerTest {
	
	CreateTestData createTestData;
	
	public AbstractControllerTest() {}
	
	public void create(String arg1, List<BookDto> arg2) throws JsonParseException, JsonMappingException, FileNotFoundException, IOException {
		createTestData.generate(arg1, arg2);
	}
}
