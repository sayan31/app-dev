package com.sayan.microservices.demospringbootmicroservices.utils.dtotransformers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.sayan.microservices.demospringbootmicroservices.dto.BookDto;
import com.sayan.microservices.demospringbootmicroservices.endpoints.AuthorController;
import com.sayan.microservices.demospringbootmicroservices.endpoints.BookController;
import com.sayan.microservices.demospringbootmicroservices.entity.BookTable;

@Component
public class BookDtoTransformer {
	public List<BookDto> transform(List<Object[]> resultSet) {
		final Map<Long, BookDto> booksDtoMap = new HashMap<>();

		for (Object[] o : resultSet) {
			Long bookId = ((Number) o[0]).longValue();

			BookDto bookDto = booksDtoMap.get(bookId);
			if (null == bookDto) {
				bookDto = new BookDto();
				bookDto.setBookId(((Number) o[0]).longValue());
				bookDto.setName((String) o[1]);
				bookDto.setIsbn(((Number) o[2]).longValue());
				bookDto.setDescription((String) o[3]);
			}			
			bookDto.add(linkTo(methodOn(AuthorController.class).fetchAuthorWithBooks(bookId)).withRel("authors"));
			bookDto.add(linkTo(methodOn(BookController.class).updateBook(bookId,bookDto)).withRel("update"));
			bookDto.add(linkTo(methodOn(BookController.class).getBook(bookId)).withRel("self"));
			
			booksDtoMap.putIfAbsent(bookDto.getBookId(), bookDto);
		}
		return new ArrayList<>(booksDtoMap.values());
	}

	public List<BookDto> transform(Set<BookTable> resultSet) {
		final Map<Long, BookDto> booksDtoMap = new HashMap<>();

		for (BookTable book : resultSet) {
			Long bookId = book.getId();
			BookDto bookDto = booksDtoMap.get(bookId);
			if (null == bookDto) {
				bookDto = new BookDto();
				bookDto.setBookId(bookId);
				bookDto.setName(book.getBookName());
				bookDto.setIsbn(book.getIsbn());
				//if(book.getDescription()!=null) {
				bookDto.setDescription(book.getDescription());
				//}
			}
			bookDto.add(linkTo(methodOn(AuthorController.class).fetchAuthorWithBooks(bookId)).withRel("authors"));
			bookDto.add(linkTo(methodOn(BookController.class).updateBook(bookId,bookDto)).withRel("update"));
			
			booksDtoMap.putIfAbsent(bookDto.getBookId(), bookDto);
		}
		return new ArrayList<>(booksDtoMap.values());
	}
}
