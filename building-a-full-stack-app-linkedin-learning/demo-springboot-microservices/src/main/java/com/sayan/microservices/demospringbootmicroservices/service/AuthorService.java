package com.sayan.microservices.demospringbootmicroservices.service;

import org.springframework.stereotype.Service;

@Service
public class AuthorService {
	
	//private AuthorRepository authorRepository;
	
	/*
	 * @Autowired public AuthorService(AuthorRepository authorRepository) {
	 * this.authorRepository= authorRepository; }
	 */
	
	/**
	 * Add an author to the database
	 * 
	 * @param bookName: Name of the book
	 * @param description: Brief description
	 * @param isbn: ISBN number of the book
	 *  
	 * @return new or existing book
	 */
	/*
	 * public AuthorTable addAuthor(AuthorTable author) { return authorRepository.
	 * findByAuthorLastNameIgnoreCaseContainingAndAuthorFirstNameIgnoreCaseContaining
	 * (author.getAuthorLastName(),author.getAuthorFirstName()).orElse(
	 * authorRepository.save(author)); }
	 * 
	 * public AuthorTable addAuthor(String authorLastName,String
	 * authorFirstName,String authorAbout) { return authorRepository.
	 * findByAuthorLastNameIgnoreCaseContainingAndAuthorFirstNameIgnoreCaseContaining
	 * (authorLastName,authorFirstName).orElse(authorRepository.save(new
	 * AuthorTable(authorLastName,authorFirstName,authorAbout))); }
	 */
}	