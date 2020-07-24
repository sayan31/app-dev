package com.sayan.microservices.demospringbootmicroservices.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sayan.microservices.demospringbootmicroservices.entity.AuthorTable;


/**
 * @author S
 * This repository holds all query methods used for AuthorTable entities
 */
@Repository
@Transactional(readOnly = true)
public interface AuthorRepository extends CrudRepository<AuthorTable, Long> {
	
	Optional<List<AuthorTable>> findByAuthorLastNameIgnoreCaseContainingAndAuthorFirstNameIgnoreCaseContaining(String authorLastName,String authorFirstName);
}
