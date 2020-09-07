package com.sayan.microservices.demospringbootmicroservices.utils.interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sayan.microservices.demospringbootmicroservices.dto.BookDto;

public interface CreateTestData {
	public void generate(String arg1, List<BookDto> arg2) throws JsonParseException, JsonMappingException, FileNotFoundException, IOException;
}
