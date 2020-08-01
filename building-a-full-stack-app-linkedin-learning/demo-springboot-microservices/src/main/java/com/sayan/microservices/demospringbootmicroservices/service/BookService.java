package com.sayan.microservices.demospringbootmicroservices.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayan.microservices.demospringbootmicroservices.dto.GetAllBooksWithAuthorsDto;
import com.sayan.microservices.demospringbootmicroservices.entity.AuthorTable;
import com.sayan.microservices.demospringbootmicroservices.entity.BookTable;
import com.sayan.microservices.demospringbootmicroservices.repository.AuthorRepository;
import com.sayan.microservices.demospringbootmicroservices.repository.BookRepository;
import com.sayan.microservices.demospringbootmicroservices.utils.dtotransformers.BookDtoTransformer;

/**
 * @author S
 * Service class to handle business logic related to BookTable entities
 */
@Service
@Transactional(readOnly = true)
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private BookDtoTransformer bookDtoTransformer;
		
	public BookService(BookRepository bookRepository,AuthorRepository authorRepository,BookDtoTransformer bookDtoTransformer) {
		this.bookRepository = bookRepository;
		this.authorRepository = authorRepository;
		this.bookDtoTransformer = bookDtoTransformer;
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
	 * @return the instance that was saved to the database.
	 */
	
	@Transactional
	public void addBook(BookTable book) {		
		AuthorTable removedAuthor=null; 
		 
		Optional<BookTable> returnedBook = bookRepository.findByBookName(book.getBookName());
		
		for(AuthorTable author:book.getAuthor()) {
			Optional<List<AuthorTable>> returnedAuthors = authorRepository.findByAuthorLastNameIgnoreCaseContainingAndAuthorFirstNameIgnoreCaseContaining(author.getAuthorLastName(),author.getAuthorFirstName());
			if(returnedAuthors.isPresent()) {
				removedAuthor = returnedAuthors.get().get(0);
				book.getAuthor().remove(author);
			}
		}
		
		if(!returnedBook.isPresent()) {
			//Case: Book not present in DB, author present/not present in DB
			bookRepository.save(book).getAuthor().add(removedAuthor);			
		}else {
			//Case: Author not present in DB, book present in DB
			book.getAuthor().forEach(author->returnedBook.get().getAuthor().add(author));
		}				
	}
	
	/**
	 * Get all books currently present in the DB
	 *  
	 * @return list of {@link GetAllBooksWithAuthorsDto} objects
	 */
	public List<GetAllBooksWithAuthorsDto> getAllBooks(){
		List<Object[]> books = bookRepository.findAllBooks();
		List<GetAllBooksWithAuthorsDto> booksDto = bookDtoTransformer.transform(books);
		return booksDto;
	}
	
	/**
	 * Get all books written by a particular author
	 * 
	 * @param authorId
	 * @return list of {@link GetAllBooksWithAuthorsDto} objects
	 */
	public List<GetAllBooksWithAuthorsDto> getBooksByAuthor(Long authorId){
		Set<BookTable> books = authorRepository.findByAuthorId(authorId);
		List<GetAllBooksWithAuthorsDto> booksDto = bookDtoTransformer.transform(books);
		return booksDto;
	}
}
