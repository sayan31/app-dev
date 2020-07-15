package com.sayan.microservices.demospringbootmicroservices.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sayan.microservices.demospringbootmicroservices.entity.AuthorTable;
import com.sayan.microservices.demospringbootmicroservices.entity.BookTable;
import com.sayan.microservices.demospringbootmicroservices.repository.AuthorRepository;
import com.sayan.microservices.demospringbootmicroservices.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
		
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	private Set<AuthorTable> setOfAuthorsToInsert = new HashSet<>();
	
	private BookTable returned;
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
		Optional<BookTable> returnedBook = bookRepository.findByBookName(book.getBookName());
		/*
		 * book.getAuthor().forEach(author->authorService.addAuthor(author)); return
		 * returned;
		 */
		for(AuthorTable author:book.getAuthor()) {
			Optional<List<AuthorTable>> returnedAuthors = authorRepository.findByAuthorLastNameIgnoreCaseContainingAndAuthorFirstNameIgnoreCaseContaining(author.getAuthorLastName(),author.getAuthorFirstName());
			if(!returnedAuthors.isPresent()) {
				setOfAuthorsToInsert.add(author);
			}
		}
		for(AuthorTable authorToInsert:setOfAuthorsToInsert) {
			book.setAuthor(authorToInsert);
		}
		//setOfAuthorsToInsert.forEach(author->book.setAuthor(author));
		if(!returnedBook.isPresent()) {
			returned = bookRepository.save(book);
		}
		return returned;
	}

}
