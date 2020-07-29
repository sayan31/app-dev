package com.sayan.microservices.demospringbootmicroservices.endpoints;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sayan.microservices.demospringbootmicroservices.dto.GetAllBooksWithAuthorsDto;
import com.sayan.microservices.demospringbootmicroservices.service.BookService;
import com.sayan.microservices.demospringbootmicroservices.utils.BookApplicationConstants;

@RestController
@RequestMapping(BookApplicationConstants.BOOK_V1)
public class BookController {
	
	@Autowired
	private BookService bookService;
	/*
	 * @Autowired private BookAssembler bookAssembler;
	 */

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@GetMapping
	public List<GetAllBooksWithAuthorsDto> showAllBooks(){
		List<GetAllBooksWithAuthorsDto> listOfBooks = bookService.getAllBooks();
		for(final GetAllBooksWithAuthorsDto bookWithAuthor: listOfBooks) {
			int index=0;
			bookWithAuthor.add(linkTo(methodOn(AuthorController.class).fetchAuthorWithBooks(bookWithAuthor.getAuthors().get(index).getAuthorId())).withRel("authors"));
			index++;
		}
		//Link link = linkTo(methodOn(BookController.class).showAllBooks()).withSelfRel();
		return listOfBooks;
	}
}
