package com.sayan.microservices.demospringbootmicroservices.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sayan.microservices.demospringbootmicroservices.entity.Author;
import com.sayan.microservices.demospringbootmicroservices.entity.Book;

/**
 * @author S
 * This repository holds all query methods used to query {@link Book} entities
 */
@Repository
@Transactional(readOnly = true)
public interface BookRepository extends CrudRepository<Book, Long> {
	
	/**
	 * Look up a book by its name
	 * 
	 * @param bookName
	 * @return an Optional containing a {@link Book} object
	 */
	Optional<Book> findByBookName(String bookName);
	
	/**
	 * Lookup all books
	 * 
	 * @return a list of {@link Book} objects
	 */
	@Query("SELECT b.id AS bookId, b.bookName AS name, b.isbn AS isbn, b.description AS description "			
			+"FROM Book b")
	List<Object[]> findAllBooks();
	
	/**
	 * Get the set of authors for a particular book
	 * 
	 * @param bookId
	 * @return {@link Set} of {@link Author} object
	 */
	@Query("SELECT b.authors FROM Book b WHERE b.id = ?1")
	Set<Author> findByBookId(Long bookId);
	
	/**
	 * Adds a {@link Book} instance to the database
	 */
	@Transactional
	<S extends Book>S save(S bookTable);
}
