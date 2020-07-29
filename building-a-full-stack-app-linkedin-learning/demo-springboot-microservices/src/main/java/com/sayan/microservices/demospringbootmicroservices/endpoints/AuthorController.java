package com.sayan.microservices.demospringbootmicroservices.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sayan.microservices.demospringbootmicroservices.dto.GetAuthorWithBooksDto;
import com.sayan.microservices.demospringbootmicroservices.service.AuthorService;
import com.sayan.microservices.demospringbootmicroservices.utils.BookApplicationConstants;

@RestController
@RequestMapping(BookApplicationConstants.AUTHOR_V1)
public class AuthorController {
	
	@Autowired
	private AuthorService authorService;

	public AuthorController(AuthorService authorService) {
		this.authorService = authorService;
	}
		
	@GetMapping("/{id}")
	public GetAuthorWithBooksDto fetchAuthorWithBooks(@PathVariable("id") Long authorId){
		GetAuthorWithBooksDto authorWithBooks = authorService.findAuthorWithBooksById(authorId);
		return authorWithBooks;
	}
	
}