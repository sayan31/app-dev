package com.sayan.microservices.demospringbootmicroservices.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sayan.microservices.demospringbootmicroservices.entity.Author;
import com.sayan.microservices.demospringbootmicroservices.entity.Book;


/**
 * @author S
 * This repository holds all query methods used for AuthorTable entities
 */
@Repository
@Transactional(readOnly = true)
public interface AuthorRepository extends CrudRepository<Author, Long> {
	
	/**
	 * Lookup author by last name and first name.
	 * 
	 * @param authorLastName
	 * @param authorFirstName
	 * @return an Optional containing a list of all found AuthorTable entities
	 */
	List<Author> findByAuthorLastNameIgnoreCaseContainingAndAuthorFirstNameIgnoreCaseContaining(String authorLastName, String authorFirstName);
	
	/**
	 * Get the set of books for a particular author
	 * 
	 * @param authorId
	 * @return {@link Set} of {@link Book} objects.
	 */
	@Query("SELECT a.books FROM Author a WHERE a.id=?1")
	Set<Book> findByAuthorId(Long authorId);
}
