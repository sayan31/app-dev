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
		
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	/**
	 * The set of authors to insert into the database with a book
	 */
	private Set<AuthorTable> setOfAuthorsToInsert = new HashSet<>();
	
	private BookTable returned;
	
	/**
	 * Add a book to the database.
	 * -------------------------------
	 * A book can have one or more authors associated with it.
	 * 
	 * If a book has only one author and that author is already present in the database,
	 * the author is not added again.
	 * 
	 * In case of multiple authors of a book who are already in the database, they are removed
	 * before saving the unique authors for the book.
	 * 
	 * @param book -  instance of BookTable entity that has to be entered into database.
	 * @return returned - the instance that was saved to the database
	 */
	public BookTable addBook(BookTable book) {		
		Optional<BookTable> returnedBook = bookRepository.findByBookName(book.getBookName());
		setOfAuthorsToInsert.clear();
		for(AuthorTable author:book.getAuthor()) {
			Optional<List<AuthorTable>> returnedAuthors = authorRepository.findByAuthorLastNameIgnoreCaseContainingAndAuthorFirstNameIgnoreCaseContaining(author.getAuthorLastName(),author.getAuthorFirstName());
			if(!returnedAuthors.isPresent()) {
				setOfAuthorsToInsert.add(author);				
			}else {
				book.getAuthor().remove(author);
			}
		}
		
		if (!setOfAuthorsToInsert.isEmpty()) {
			for (AuthorTable authorToInsert : setOfAuthorsToInsert) {
				book.addAuthor(authorToInsert);
			} 
		}
		if(!returnedBook.isPresent()) {
			returned = bookRepository.save(book);
		}
		return returned;
	}
	
	/**
	 * @return all the books currently present in the database
	 */
	public List<BookTable> getAllBooks(){
		return bookRepository.findAll();
	}

}
