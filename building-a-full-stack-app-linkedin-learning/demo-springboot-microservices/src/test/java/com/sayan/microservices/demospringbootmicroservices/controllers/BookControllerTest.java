package com.sayan.microservices.demospringbootmicroservices.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.sayan.microservices.demospringbootmicroservices.dto.BookDto;
import com.sayan.microservices.demospringbootmicroservices.endpoints.BookController;
import com.sayan.microservices.demospringbootmicroservices.service.BookService;
import com.sayan.microservices.demospringbootmicroservices.utils.BookApplicationConstants;
import com.sayan.microservices.demospringbootmicroservices.utils.implementations.TestDataForBookController;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest extends AbstractControllerTest{

	@Value("${demospringbootmicroservices.importFile}")
	private String importFile;
	
	@Autowired
    private MockMvc mvc;
	
	@MockBean
	private BookService bookService;
	
	public BookControllerTest() {
		createTestData = new TestDataForBookController();
	}
	
	@Test
	public void showAllBooks_test() throws Exception {
		//Create 
		List<BookDto> books = new ArrayList<>();
		this.create(importFile, books);
		//End of Create
		
		when(bookService.getAllBooks()).thenReturn(books);		
		mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/"+BookApplicationConstants.BOOK_V1).accept(MediaType.APPLICATION_JSON)).
								andExpect(status().isOk()).
								andExpect(content().contentType(MediaType.APPLICATION_JSON)).
								andExpect(jsonPath("$._embedded.bookDtoes",hasSize(books.size())));
		
		
		verify(bookService, times(1)).getAllBooks();
		//verifyNoMoreInteractions(bookService);
	}

}
