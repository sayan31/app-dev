package com.sayan.microservices.demospringbootmicroservices.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sayan.microservices.demospringbootmicroservices.entity.AuthorTable;
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
	
	/**
	 * Lookup all books
	 * 
	 * @return a list of {@link BookTable} objects
	 */
	@Query("SELECT b.id AS bookId, b.bookName AS name, b.isbn AS isbn "			
			+"FROM BookTable b")
	List<Object[]> findAllBooks();
	
	/**
	 * Get the set of authors for a particular book
	 * 
	 * @param bookId
	 * @return {@link Set} of {@link AuthorTable} object
	 */
	@Query("SELECT b.authors FROM BookTable b WHERE b.id = ?1")
	Set<AuthorTable> findByBookId(Long bookId);
	
	/**
	 * Adds a {@link BookTable} instance to the database
	 */
	@Transactional
	<S extends BookTable>S save(S bookTable);		
}
