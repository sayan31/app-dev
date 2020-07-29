package com.sayan.microservices.demospringbootmicroservices.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
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
	
	/**
	 * Lookup author by last name and first name.
	 * 
	 * @param authorLastName
	 * @param authorFirstName
	 * @return an Optional containing a list of all found AuthorTable entities
	 */
	Optional<List<AuthorTable>> findByAuthorLastNameIgnoreCaseContainingAndAuthorFirstNameIgnoreCaseContaining(String authorLastName,String authorFirstName);
	
	@Query("SELECT a.authorLastName AS lastName,a.authorFirstName AS firstName "
			+"FROM AuthorTable a INNER JOIN a.books")
	Object[] findByAuthorId(Long id);
}
