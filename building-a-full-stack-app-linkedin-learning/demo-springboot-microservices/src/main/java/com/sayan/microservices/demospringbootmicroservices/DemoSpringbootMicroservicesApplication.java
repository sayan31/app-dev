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
import com.sayan.microservices.demospringbootmicroservices.service.AuthorService;
import com.sayan.microservices.demospringbootmicroservices.service.BookService;

@SpringBootApplication
public class DemoSpringbootMicroservicesApplication implements CommandLineRunner{
	
	@Value("${demospringbootmicroservices.importFile}")
	private String importFile;
	@Autowired
	private BookService bookService;
	@Autowired
	private AuthorService authorService;

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringbootMicroservicesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		createAllAuthors(importFile);		
	}

	private void createAllAuthors(String fileToImport) throws IOException{		
		AuthorFromFile.read(fileToImport).forEach(importedAuthor->authorService.addAuthor(importedAuthor.getAuthorLastName(),importedAuthor.getAuthorFirstName(),importedAuthor.getAuthorAbout()));
	}
	
	private static class AuthorFromFile{
		private String authorLastName,authorFirstName,authorAbout;
		
		static List<AuthorFromFile> read(String fileToImport) throws IOException {
            return new ObjectMapper().setVisibility(PropertyAccessor.FIELD, Visibility.ANY).
                    readValue(new FileInputStream(fileToImport), new TypeReference<List<AuthorFromFile>>() {});
        }
		
		protected AuthorFromFile() {}

		public String getAuthorLastName() {
			return authorLastName;
		}		

		public String getAuthorFirstName() {
			return authorFirstName;
		}		

		public String getAuthorAbout() {
			return authorAbout;
		}			
	}
}
