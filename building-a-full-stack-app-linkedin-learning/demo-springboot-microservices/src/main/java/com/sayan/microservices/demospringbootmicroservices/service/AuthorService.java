package com.sayan.microservices.demospringbootmicroservices.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayan.microservices.demospringbootmicroservices.dto.GetAuthorWithBooksDto;
import com.sayan.microservices.demospringbootmicroservices.entity.AuthorTable;
import com.sayan.microservices.demospringbootmicroservices.repository.AuthorRepository;

@Service
@Transactional
public class AuthorService {
	
	@Autowired
	private AuthorRepository authorRepository;

	public AuthorService(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}
	
	
	public GetAuthorWithBooksDto findAuthorWithBooksById(Long id){
		Object[] authorWithBooks = authorRepository.findByAuthorId(id);
		return null;
	}
}	