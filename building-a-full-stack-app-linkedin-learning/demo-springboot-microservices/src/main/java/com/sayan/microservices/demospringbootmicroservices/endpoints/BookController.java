package com.sayan.microservices.demospringbootmicroservices.endpoints;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.sayan.microservices.demospringbootmicroservices.dto.BookDto;
import com.sayan.microservices.demospringbootmicroservices.exceptions.BooksNotFoundException;
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
		List<BookDto> listOfBooks = bookService.getAllBooks();
		if(listOfBooks.isEmpty()) {
			throw new BooksNotFoundException("No Books in DB");
		}
		Link link = linkTo(methodOn(BookController.class).showAllBooks()).withSelfRel();
		return ResponseEntity.ok(CollectionModel.of(listOfBooks,link));
	}
	
	@GetMapping("/author/{authorId}")
	public ResponseEntity<?> showBook(@PathVariable("authorId") Long authorId) throws BooksNotFoundException{
		List<BookDto> listOfBooksForAuthor = bookService.getBooksByAuthor(authorId);
		if(listOfBooksForAuthor.isEmpty()) {
			throw new BooksNotFoundException("Books Not Found for author with id: "+authorId);
		}
		Link link = linkTo(methodOn(BookController.class).showBook(authorId)).withSelfRel();
		return ResponseEntity.ok(CollectionModel.of(listOfBooksForAuthor,link));
	}
	
	@GetMapping("/{bookId}")
	public ResponseEntity<?> getBook(@PathVariable("bookId") Long bookId) throws BooksNotFoundException{
		BookDto book = bookService.getBookById(bookId);
		if(null==book) {
			throw new BooksNotFoundException("No book found with id: "+bookId);
		}
		Link link = linkTo(methodOn(BookController.class).getBook(bookId)).withSelfRel();
		return ResponseEntity.ok(EntityModel.of(book,link));
	}
	
	@PatchMapping("/{bookId}")
	public ResponseEntity<?> updateBook(@PathVariable("bookId") Long bookId, @RequestBody BookDto updateBookDto) {
		BookDto book =null;
		Map<String,Object> itemsToUpdate = new HashMap<>();
		if (updateBookDto.getDescription()!=null) {
			itemsToUpdate.put("description", updateBookDto.getDescription());
		}
		if (updateBookDto.getIsbn()!= null) {
			itemsToUpdate.put("isbn",updateBookDto.getIsbn());
		}
		if (updateBookDto.getName()!= null) {
			itemsToUpdate.put("name",updateBookDto.getName());
		}
		
		book = bookService.updateBookById(bookId, itemsToUpdate);
		
		return ResponseEntity.ok(EntityModel.of(book));
	}
}
