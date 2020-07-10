package com.sayan.microservices.demospringbootmicroservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sayan.microservices.demospringbootmicroservices.entity.AuthorTable;
import com.sayan.microservices.demospringbootmicroservices.repository.AuthorRepository;

@Service
public class AuthorService {
	
	private AuthorRepository authorRepository;
	
	@Autowired
	public AuthorService(AuthorRepository authorRepository) {
		this.authorRepository= authorRepository;
	}
	
	/**
	 * Add an author to the database
	 * 
	 * @param bookName: Name of the book
	 * @param description: Brief description
	 * @param isbn: ISBN number of the book
	 *  
	 * @return new or existing book
	 */
	public AuthorTable addAuthor(AuthorTable author) {
		return authorRepository.findById(author.getId()).orElse(authorRepository.save(author));
	}

}
