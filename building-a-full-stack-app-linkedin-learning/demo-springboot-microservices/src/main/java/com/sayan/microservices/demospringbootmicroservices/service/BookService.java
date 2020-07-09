package com.sayan.microservices.demospringbootmicroservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sayan.microservices.demospringbootmicroservices.entity.AuthorTable;
import com.sayan.microservices.demospringbootmicroservices.entity.BookTable;
import com.sayan.microservices.demospringbootmicroservices.repository.AuthorRepository;
import com.sayan.microservices.demospringbootmicroservices.repository.BookRepository;

@Service
public class BookService {
	
	private BookRepository bookRepository;
	private AuthorRepository authorRepository;
	
	@Autowired
	public BookService(BookRepository bookRepository,AuthorRepository authorRepository) {
		this.bookRepository = bookRepository;
		this.authorRepository= authorRepository;
	}
	
	/**
	 * Add a book to the database
	 * 
	 * @param bookName: Name of the book
	 * @param description: Brief description
	 * @param isbn: ISBN number of the book
	 *  
	 * @return new or existing book
	 */
	public BookTable addBook(String bookName,String description,Long isbn,String authorLastName, String authorFirstName, String authorAbout) {
		BookTable bookTable = new BookTable(bookName,description,isbn);
		BookTable returnedFromSave= bookRepository.findByName(bookName).orElse(bookRepository.save(bookTable));
		authorRepository.save(new AuthorTable(authorLastName,authorFirstName,authorAbout));
		return returnedFromSave;
	}

}
