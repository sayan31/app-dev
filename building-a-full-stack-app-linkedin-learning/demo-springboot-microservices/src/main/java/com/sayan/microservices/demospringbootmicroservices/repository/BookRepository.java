package com.sayan.microservices.demospringbootmicroservices.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.sayan.microservices.demospringbootmicroservices.entity.BookTable;

public interface BookRepository extends CrudRepository<BookTable, Long> {
	
	Optional<BookTable> findByName(String bookName);
	
	//This enables this method to save items of the type BookTable 
	//as generic type S extends it.
	<S extends BookTable>S save(S bookTable);
	
	
}
