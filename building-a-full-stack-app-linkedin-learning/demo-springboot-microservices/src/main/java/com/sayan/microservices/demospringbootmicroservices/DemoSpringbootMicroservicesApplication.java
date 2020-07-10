package com.sayan.microservices.demospringbootmicroservices;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sayan.microservices.demospringbootmicroservices.entity.AuthorTable;
import com.sayan.microservices.demospringbootmicroservices.entity.BookTable;
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
		bookService.addBook(new BookTable("The Predator's Ball", "", 9780140120905L,new ArrayList<AuthorTable>()));
		bookService.addBook(new BookTable("Annapurna: The First Conquest of an 8000-Metre Peak", "", 99541467L,new ArrayList<AuthorTable>(Arrays.asList(new AuthorTable("Herzog", "Maurice", "Herzog was a French alpinist most famously associated with the conquest of Annapurna in June 1950."),new AuthorTable("Simpson", "Joe", "Joe Simpson is the author of the bestselling Touching the Void, as well as four subsequent non-fiction books published by The Mountaineers Books: This Game of Ghosts, Storms of Silence, Dark Shadows Falling, and The Beckoning Silence.")))));
	}
}
