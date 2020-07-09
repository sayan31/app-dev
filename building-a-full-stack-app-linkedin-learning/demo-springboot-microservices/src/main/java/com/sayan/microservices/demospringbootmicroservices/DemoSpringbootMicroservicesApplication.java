package com.sayan.microservices.demospringbootmicroservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sayan.microservices.demospringbootmicroservices.service.BookService;

@SpringBootApplication
public class DemoSpringbootMicroservicesApplication implements CommandLineRunner{
	@Autowired
	private BookService bookService;

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringbootMicroservicesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		createAllBooksAndAuthors();		
	}

	private void createAllBooksAndAuthors() {
		bookService.addBook("The Predator's Ball", "", 9780140120905L, "Bruck", "Connie", "");
		bookService.addBook("Annapurna: The First Conquest of an 8000-Metre Peak", "", 99541467L, "Bruck", "Connie", "");
	}

}
