package com.sayan.microservices.demospringbootmicroservices.endpoints;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sayan.microservices.demospringbootmicroservices.dto.GetAllBooksWithAuthorsDto;
import com.sayan.microservices.demospringbootmicroservices.dto.UpdateBookDescriptionDto;
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
	public ResponseEntity<?> showAllBooks(){
		List<GetAllBooksWithAuthorsDto> listOfBooks = bookService.getAllBooks();
		Link link = linkTo(methodOn(BookController.class).showAllBooks()).withSelfRel();
		return ResponseEntity.ok(CollectionModel.of(listOfBooks,link));
	}
	
	@GetMapping("/{authorId}")
	public ResponseEntity<?> showBook(@PathVariable("authorId") Long authorId){
		List<GetAllBooksWithAuthorsDto> listOfBooksForAuthor = bookService.getBooksByAuthor(authorId);
		Link link = linkTo(methodOn(BookController.class).showBook(authorId)).withSelfRel();
		return ResponseEntity.ok(CollectionModel.of(listOfBooksForAuthor,link));
	}
	
	@PatchMapping("/{bookId}")
	public ResponseEntity<?> updateBookDescription(@PathVariable("bookId") Long bookId, @RequestBody UpdateBookDescriptionDto updateBookDescriptionDto) {
		GetAllBooksWithAuthorsDto book =null;
		if (updateBookDescriptionDto.getDescription()!=null) {
			book = bookService.updateBookDescriptionById(bookId,updateBookDescriptionDto.getDescription());
		}
		return ResponseEntity.ok(EntityModel.of(book));
	}
}
