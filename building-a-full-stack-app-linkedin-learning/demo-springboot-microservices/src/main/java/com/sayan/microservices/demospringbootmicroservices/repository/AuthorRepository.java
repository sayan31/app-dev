package com.sayan.microservices.demospringbootmicroservices.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.sayan.microservices.demospringbootmicroservices.entity.AuthorTable;

@Transactional(readOnly = true)
public interface AuthorRepository extends CrudRepository<AuthorTable, Long> {
	
	//<S extends AuthorTable>S save(S authorTable);
	Optional<List<AuthorTable>> findByAuthorLastNameIgnoreCaseContainingAndAuthorFirstNameIgnoreCaseContaining(String authorLastName,String authorFirstName);
}
