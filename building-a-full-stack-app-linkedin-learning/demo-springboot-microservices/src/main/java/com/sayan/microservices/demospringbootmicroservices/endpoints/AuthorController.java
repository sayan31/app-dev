package com.sayan.microservices.demospringbootmicroservices.endpoints;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sayan.microservices.demospringbootmicroservices.dto.AuthorDto;
import com.sayan.microservices.demospringbootmicroservices.service.AuthorService;
import com.sayan.microservices.demospringbootmicroservices.utils.ApplicationConstants;

@RestController
@RequestMapping(ApplicationConstants.AUTHOR_V1)
public class AuthorController {
	
	@Autowired
	private AuthorService authorService;

	public AuthorController(AuthorService authorService) {
		this.authorService = authorService;
	}
		
	@GetMapping("/{bookId}")
	public CollectionModel<AuthorDto> fetchAuthorWithBooks(@PathVariable("bookId") Long bookId){
		List<AuthorDto> authorWithBooks = authorService.findAuthorsByBookId(bookId);
		Link link = linkTo(methodOn(AuthorController.class).fetchAuthorWithBooks(bookId)).withSelfRel();
		return CollectionModel.of(authorWithBooks,link);
	}
	
}
