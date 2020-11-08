package com.sayan.microservices.demospringbootmicroservices.utils.implementations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sayan.microservices.demospringbootmicroservices.dto.BookDto;
import com.sayan.microservices.demospringbootmicroservices.entity.BookTable;
import com.sayan.microservices.demospringbootmicroservices.utils.interfaces.CreateTestData;

@Component
public class TestDataForBookController implements CreateTestData {

	@Override
	public void generate(String arg1, List<BookDto> arg2) throws JsonParseException, JsonMappingException, FileNotFoundException, IOException {
		final ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY).readValue(new FileInputStream(arg1), new TypeReference<List<BookTable>>(){}).
					forEach(book->arg2.add(new BookDto(book.getId(), book.getBookName(), book.getIsbn(), book.getDescription())));
	}
}
