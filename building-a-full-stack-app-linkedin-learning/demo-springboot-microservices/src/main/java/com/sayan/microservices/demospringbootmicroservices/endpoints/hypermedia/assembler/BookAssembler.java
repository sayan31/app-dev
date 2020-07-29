package com.sayan.microservices.demospringbootmicroservices.endpoints.hypermedia.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.sayan.microservices.demospringbootmicroservices.dto.GetAllBooksWithAuthorsDto;
import com.sayan.microservices.demospringbootmicroservices.endpoints.AuthorController;
import com.sayan.microservices.demospringbootmicroservices.endpoints.BookController;
import com.sayan.microservices.demospringbootmicroservices.entity.AuthorTable;
import com.sayan.microservices.demospringbootmicroservices.entity.BookTable;

@Component
public class BookAssembler extends RepresentationModelAssemblerSupport<BookTable,GetAllBooksWithAuthorsDto>{
	
	private RepositoryEntityLinks entityLinks;
	
	public BookAssembler(RepositoryEntityLinks entityLinks) {
        super(BookController.class, GetAllBooksWithAuthorsDto.class);
        this.entityLinks = entityLinks;
    }

	@Override
	public GetAllBooksWithAuthorsDto toModel(BookTable book) {
		GetAllBooksWithAuthorsDto getAllBooksWithAuthors = new GetAllBooksWithAuthorsDto(book.getId(), book.getBookName(), book.getIsbn());
		
		for(AuthorTable author:book.getAuthor()) {
			WebMvcLinkBuilder authorLink = linkTo(methodOn(AuthorController.class).fetchAuthorWithBooks(author.getId()));
		}
		
		/*linkTo(methodOn().fetchAuthorWithBooks(book.getAuthor().forEach(author->author.getId())));*/
		return null;
	}
}
