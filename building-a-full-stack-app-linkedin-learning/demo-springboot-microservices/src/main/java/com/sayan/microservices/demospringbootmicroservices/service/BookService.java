package com.sayan.microservices.demospringbootmicroservices.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayan.microservices.demospringbootmicroservices.entity.AuthorTable;
import com.sayan.microservices.demospringbootmicroservices.entity.BookTable;
import com.sayan.microservices.demospringbootmicroservices.repository.AuthorRepository;
import com.sayan.microservices.demospringbootmicroservices.repository.BookRepository;

/**
 * @author S
 *
 */
@Service
public class BookService {
	
	/**
	 * 
	 */
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
		
	public BookService(BookRepository bookRepository,AuthorRepository authorRepository) {
		this.bookRepository = bookRepository;
		this.authorRepository = authorRepository;
	}
	
	/**
	 * Add a book to the database.
	 * -------------------------------
	 * A book can have one or more authors associated with it.
	 * 
	 * In case of one or multiple authors of a book who are already in the database, 
	 * they are removed before saving the unique authors for the book.
	 * 
	 * @param book -  instance of BookTable entity that has to be entered into database.
	 * @return returned - the instance that was saved to the database.
	 */
	
	@Transactional
	public void addBook(BookTable book) {		
		Optional<BookTable> returnedBook = bookRepository.findByBookName(book.getBookName());
		for(AuthorTable author:book.getAuthor()) {
			Optional<List<AuthorTable>> returnedAuthors = authorRepository.findByAuthorLastNameIgnoreCaseContainingAndAuthorFirstNameIgnoreCaseContaining(author.getAuthorLastName(),author.getAuthorFirstName());
			if(returnedAuthors.isPresent()) {
				book.getAuthor().remove(author);
			}
		}
		if(!returnedBook.isPresent()) {
			bookRepository.save(book);			
		}
	}
	
	/**
	 * @return all the books currently present in the database
	 */
	@Transactional
	public List<BookTable> getAllBooks(){
		return bookRepository.findAll();
	}

}
