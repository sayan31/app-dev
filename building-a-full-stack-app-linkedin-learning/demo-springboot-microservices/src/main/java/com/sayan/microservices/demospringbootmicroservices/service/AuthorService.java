package com.sayan.microservices.demospringbootmicroservices.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayan.microservices.demospringbootmicroservices.dto.GetAuthorWithBooksDto;
import com.sayan.microservices.demospringbootmicroservices.entity.AuthorTable;
import com.sayan.microservices.demospringbootmicroservices.repository.AuthorRepository;
import com.sayan.microservices.demospringbootmicroservices.repository.BookRepository;
import com.sayan.microservices.demospringbootmicroservices.utils.dtotransformers.AuthorDtoTransformer;

@Service
@Transactional
public class AuthorService {
	
	/*@Autowired
	private AuthorRepository authorRepository;*/
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private AuthorDtoTransformer authorDtoTransformer;

	public AuthorService(AuthorRepository authorRepository,BookRepository bookRepository) {
		//this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
	}
		
	public List<GetAuthorWithBooksDto> findAuthorsByBookId(Long bookId){
		Set<AuthorTable> authors = bookRepository.findByBookId(bookId).get().getAuthor();
		List<GetAuthorWithBooksDto> authorDto=authorDtoTransformer.transform(authors);
		return authorDto;
	}
}	