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

import com.sayan.microservices.demospringbootmicroservices.dto.GetAllBooksWithAuthorsDto;
import com.sayan.microservices.demospringbootmicroservices.service.BookService;
import com.sayan.microservices.demospringbootmicroservices.utils.BookApplicationConstants;

@RestController
@RequestMapping(BookApplicationConstants.BOOK_V1)
public class BookController {
	
	@Autowired
	private BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@GetMapping
	public CollectionModel<GetAllBooksWithAuthorsDto> showAllBooks(){
		List<GetAllBooksWithAuthorsDto> listOfBooks = bookService.getAllBooks();
		Link link = linkTo(methodOn(BookController.class).showAllBooks()).withSelfRel();
		return CollectionModel.of(listOfBooks,link);
	}
	
	@GetMapping("/{authorId}")
	public CollectionModel<GetAllBooksWithAuthorsDto> showBook(@PathVariable("authorId") Long authorId){
		List<GetAllBooksWithAuthorsDto> listOfBooksForAuthor = bookService.getBooksByAuthor(authorId);
		Link link = linkTo(methodOn(BookController.class).showBook(authorId)).withSelfRel();
		return CollectionModel.of(listOfBooksForAuthor,link);
	}
}
