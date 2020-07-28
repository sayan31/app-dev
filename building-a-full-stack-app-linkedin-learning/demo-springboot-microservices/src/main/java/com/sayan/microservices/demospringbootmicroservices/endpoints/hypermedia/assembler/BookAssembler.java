package com.sayan.microservices.demospringbootmicroservices.endpoints.hypermedia.assembler;

import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.sayan.microservices.demospringbootmicroservices.dto.GetAllBooksWithAuthorsDto;
import com.sayan.microservices.demospringbootmicroservices.endpoints.AuthorController;
import com.sayan.microservices.demospringbootmicroservices.endpoints.BookController;
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
		
		Link authorLink = linkTo(AuthorController.class).slash(book.getAuthor().forEach(author->author.getId()));
		/*linkTo(methodOn().fetchAuthorWithBooks(book.getAuthor().forEach(author->author.getId())));*/
		return null;
	}
}
