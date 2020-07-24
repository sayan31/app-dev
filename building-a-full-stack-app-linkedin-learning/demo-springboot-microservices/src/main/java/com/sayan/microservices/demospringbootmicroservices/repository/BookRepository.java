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
 * This repository holds all query methods used to query BookTable entities
 */
@Repository
@Transactional(readOnly = true)
public interface BookRepository extends CrudRepository<BookTable, Long> {
	
	Optional<BookTable> findByBookName(String bookName);
	
	@Query(value="SELECT b FROM BookTable b JOIN FETCH b.authors")
	List<BookTable> findAll();
	
	@Transactional
	<S extends BookTable>S save(S bookTable);
	
}
