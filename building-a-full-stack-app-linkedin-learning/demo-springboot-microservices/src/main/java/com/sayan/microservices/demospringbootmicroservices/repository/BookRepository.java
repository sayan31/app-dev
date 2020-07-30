package com.sayan.microservices.demospringbootmicroservices.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sayan.microservices.demospringbootmicroservices.entity.BookTable;

/**
 * @author S
 * This repository holds all query methods used to query {@link BookTable} entities
 */
@Repository
@Transactional(readOnly = true)
public interface BookRepository extends CrudRepository<BookTable, Long> {
	
	/**
	 * Look up a book by its name
	 * 
	 * @param bookName
	 * @return an Optional containing a {@link BookTable} object
	 */
	Optional<BookTable> findByBookName(String bookName);
	
	//@Query(value="SELECT b FROM BookTable b JOIN FETCH b.authors")
	/**
	 * Lookup all books
	 * @return a list of {@link BookTable} objects
	 */
	@Query("SELECT b.id AS bookId, b.bookName AS name, b.isbn AS isbn "			
			+"FROM BookTable b")
	List<Object[]> findAllBooks();
	
	/**
	 * Find a Book by its Id attribute
	 * 
	 * @param bookId
	 * @return the single {@link BookTable} object
	 */
	@Query("SELECT b FROM BookTable b WHERE b.id = ?1")
	Optional<BookTable> findByBookId(Long bookId);
	
	/**
	 * Adds a {@link BookTable} instance to the database
	 */
	@Transactional
	<S extends BookTable>S save(S bookTable);		
}
