package com.sayan.microservices.demospringbootmicroservices.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.sayan.microservices.demospringbootmicroservices.entity.BookTable;

public interface BookRepository extends CrudRepository<BookTable, Long> {
	
	Optional<BookTable> findByBookName(String bookName);
	<S extends BookTable>S save(S bookTable);
	List<BookTable> findAll();
	
}
