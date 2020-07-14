package com.sayan.microservices.demospringbootmicroservices.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.sayan.microservices.demospringbootmicroservices.entity.AuthorTable;

public interface AuthorRepository extends CrudRepository<AuthorTable, Long> {
	
	<S extends AuthorTable>S save(S authorTable);
	Optional<AuthorTable> findByAuthorLastNameIgnoreCaseContainingAndAuthorFirstNameIgnoreCaseContaining(String authorLastName,String authorFirstName);
}
