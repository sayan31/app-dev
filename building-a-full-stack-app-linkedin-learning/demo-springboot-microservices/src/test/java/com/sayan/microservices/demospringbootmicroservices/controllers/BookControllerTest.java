package com.sayan.microservices.demospringbootmicroservices.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sayan.microservices.demospringbootmicroservices.dto.BookDto;
import com.sayan.microservices.demospringbootmicroservices.endpoints.BookController;
import com.sayan.microservices.demospringbootmicroservices.entity.BookTable;
import com.sayan.microservices.demospringbootmicroservices.service.BookService;
import com.sayan.microservices.demospringbootmicroservices.utils.BookApplicationConstants;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {
	
	@Value("${demospringbootmicroservices.importFile}")
	private String importFile;
	
	@Autowired
    private MockMvc mvc;
	
	@MockBean
	private BookService bookService;
	
	@Test
	public void showAllBooks_test() throws Exception {
		//Create 
		List<BookDto> books = createTestData(importFile);
		//End of Create
		
		when(bookService.getAllBooks()).thenReturn(books);		
		mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/"+BookApplicationConstants.BOOK_V1).accept(MediaType.APPLICATION_JSON)).
								andExpect(status().isOk()).
								andExpect(content().contentType(MediaType.APPLICATION_JSON)).
								andExpect(jsonPath("$._embedded.bookDtoes",hasSize(books.size())));
		
		
		verify(bookService, times(1)).getAllBooks();
		verifyNoMoreInteractions(bookService);
	}
	
	public List<BookDto> createTestData(String fileToImport) throws IOException{
		final ObjectMapper objectMapper = new ObjectMapper();
		List<BookDto> bookDtoList= new ArrayList<>();
		objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY).readValue(new FileInputStream(fileToImport), new TypeReference<List<BookTable>>(){}).
					forEach(book->bookDtoList.add(new BookDto(book.getId(), book.getBookName(), book.getIsbn(), book.getDescription())));
		
		return bookDtoList;
	}
}
