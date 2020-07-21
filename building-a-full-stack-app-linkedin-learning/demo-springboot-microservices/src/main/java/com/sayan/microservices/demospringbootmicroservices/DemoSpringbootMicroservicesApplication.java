package com.sayan.microservices.demospringbootmicroservices;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sayan.microservices.demospringbootmicroservices.entity.BookTable;
import com.sayan.microservices.demospringbootmicroservices.service.BookService;

@SpringBootApplication
public class DemoSpringbootMicroservicesApplication implements CommandLineRunner {

	@Value("${demospringbootmicroservices.importFile}")
	private String importFile;
	@Autowired
	private BookService bookService;
	/*
	 * @Autowired private AuthorService authorService;
	 */

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringbootMicroservicesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		createAllBooksAndAuthors(importFile);
		//displayAllBooks();
	}

	/*
	 * private void displayAllBooks() { bookService.getAllBooks(); }
	 */

	private void createAllBooksAndAuthors(String fileToImport) throws IOException {
		ObjectMapperHelper.readJsonWithObjectMapper(fileToImport).forEach(importedBook->bookService.addBook(importedBook));
	}
	
	private static class ObjectMapperHelper{
		public static List<BookTable> readJsonWithObjectMapper(String fileToImport) throws IOException{
			ObjectMapper objectMapper = new ObjectMapper();
			List<BookTable> books=objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY).readValue(new FileInputStream(fileToImport), new TypeReference<List<BookTable>>(){});
			return books;
		}
	}	
}