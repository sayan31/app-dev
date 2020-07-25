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
	
	/**
	 * Lookup all books along with their authors
	 * @return a list of {@link BookTable} objects
	 */
	@Query(value="SELECT b FROM BookTable b JOIN FETCH b.authors")
	List<BookTable> findAll();
	
	@Query("SELECT b.id AS bookId, b.bookName AS name, b.isbn AS isbn, "
			+"a.id AS authorId, a.authorLastName AS lastName, a.authorFirstName AS firstName "
			+"FROM BookTable b INNER JOIN b.authors a")
	List<Object[]> findAllDto();
	
	/**
	 * Adds a {@link BookTable} instance to the database
	 */
	@Transactional
	<S extends BookTable>S save(S bookTable);	
}
