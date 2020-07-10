package com.sayan.microservices.demospringbootmicroservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sayan.microservices.demospringbootmicroservices.entity.BookTable;
import com.sayan.microservices.demospringbootmicroservices.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorService authorService;
		
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
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
	public BookTable addBook(BookTable book) {
		BookTable returned = bookRepository.findByBookName(book.getBookName()).orElse(bookRepository.save(book));
		book.getAuthor().forEach(author->authorService.addAuthor(author));
		return returned;
	}

}
