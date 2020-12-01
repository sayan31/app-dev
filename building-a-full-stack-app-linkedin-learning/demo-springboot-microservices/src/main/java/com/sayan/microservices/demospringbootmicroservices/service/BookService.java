package com.sayan.microservices.demospringbootmicroservices.service;

import java.util.*;

import com.sayan.microservices.demospringbootmicroservices.dto.AuthorDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayan.microservices.demospringbootmicroservices.dto.BookDto;
import com.sayan.microservices.demospringbootmicroservices.entity.Author;
import com.sayan.microservices.demospringbootmicroservices.entity.Book;
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

	private static final ModelMapper modelMapper = new ModelMapper();
		
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
	public void addBook(BookDto book) {
		List<Author> removedAuthor=new ArrayList<>();
		 
		Optional<Book> returnedBook = bookRepository.findByBookName(book.getBookName());
		Set<Author> authorsForBook = new HashSet<>();
		for(AuthorDto authorDto:book.getAuthors()){
			authorsForBook.add(modelMapper.map(authorDto, Author.class));
		}

		if (!authorsForBook.isEmpty()) {
			for(Author author:authorsForBook) {
				List<Author> returnedAuthors = authorRepository.findByAuthorLastNameIgnoreCaseContainingAndAuthorFirstNameIgnoreCaseContaining(author.getAuthorLastName(),author.getAuthorFirstName());
				if(!returnedAuthors.isEmpty()) {
					removedAuthor.add(returnedAuthors.get(0));
					authorsForBook.remove(author);
				}
			}
		}

		if(!returnedBook.isPresent()) {
			//Case: Book not present in DB, author present/not present in DB
			Book savedBook = bookRepository.save(modelMapper.map(book, Book.class));
			authorRepository.saveAll(authorsForBook);
			Set<Author> authors = savedBook.getAuthor();
			authors.addAll(removedAuthor);
			authors.addAll(authorsForBook);
		}else {
			//Case: Author not present in DB, book present in DB
			authorRepository.saveAll(authorsForBook);
		}				
	}
	
	/**
	 * Get all books currently present in the DB
	 *  
	 * @return list of {@link BookDto} objects
	 */
	public List<BookDto> getAllBooks(){
		List<Object[]> books = bookRepository.findAllBooks();
		List<BookDto> booksDto = bookDtoTransformer.transform(books);
		return booksDto;
	}
	
	/**
	 * Get all books written by a particular author
	 * 
	 * @param authorId
	 * @return list of {@link BookDto} objects
	 */
	public List<BookDto> getBooksByAuthor(Long authorId){
		Set<Book> books = authorRepository.findByAuthorId(authorId);
		List<BookDto> booksDto = bookDtoTransformer.transform(books);
		return booksDto;
	}
	
	public BookDto getBookById(Long bookId) {
		Book book = bookRepository.findById(bookId).get();
		Set<Book> books = new HashSet<>();
		books.add(book);
		List<BookDto> bookDto = bookDtoTransformer.transform(books);
		return bookDto.get(0);
	}
	
	/**
	 * Update one or more attributes of a book
	 * 
	 * @param bookId - book ID of the book to update
	 * @param updateMap - a {@link Map} of fields to be updated and their respective values 
	 * 
	 * @return a single {@link BookDto} object
	 */
	@Transactional
	public BookDto updateBookById(Long bookId, Map<String,Object> updateMap) {
		Book book = bookRepository.findById(bookId).get();
		for(Map.Entry<String, Object> entry: updateMap.entrySet()) {
			switch(entry.getKey()) {
				case "name":
					book.setBookName(entry.getValue().toString());
					break;
				case "isbn":
					book.setIsbn((Long)entry.getValue());
					break;
				case "description":
					book.setDescription(entry.getValue().toString());
					break;
				case "authors":
					List<AuthorDto> authorsForBook = (List<AuthorDto>)entry.getValue();
					//Set<AuthorDto> authorsForBook=new HashSet<>(authorsFromUpdateMap);
					for(AuthorDto authorDto:authorsForBook){
						List<Author> returnedAuthors =  authorRepository.findByAuthorLastNameIgnoreCaseContainingAndAuthorFirstNameIgnoreCaseContaining(authorDto.getLastName(),authorDto.getFirstName());
						if (returnedAuthors.isEmpty()){
							Author author = authorRepository.save(modelMapper.map(authorDto, Author.class));
							book.addAuthor(author);
						}else{
							for (Author author:returnedAuthors){
								if (authorDto.getLastName()!=null) {
									author.setAuthorLastName(authorDto.getLastName());
								}
								if (authorDto.getFirstName()!=null) {
									author.setAuthorFirstName(authorDto.getFirstName());
								}
								if (authorDto.getAbout()!=null) {
									author.setAuthorAbout(authorDto.getAbout());
								}
							}
						}
					}
					break;
			}
		}
		Set<Book> books = new HashSet<>();
		books.add(book);
		List<BookDto> bookDto = bookDtoTransformer.transform(books);
		
		return bookDto.get(0);
	}
}
