package com.sayan.microservices.demospringbootmicroservices.utils.dtotransformers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.sayan.microservices.demospringbootmicroservices.dto.GetAllBooksWithAuthorsDto;
import com.sayan.microservices.demospringbootmicroservices.endpoints.AuthorController;
import com.sayan.microservices.demospringbootmicroservices.entity.BookTable;

@Component
public class BookDtoTransformer {
	public List<GetAllBooksWithAuthorsDto> transform(List<Object[]> resultSet) {
		final Map<Long, GetAllBooksWithAuthorsDto> booksDtoMap = new HashMap<>();

		for (Object[] o : resultSet) {
			Long bookId = ((Number) o[0]).longValue();

			GetAllBooksWithAuthorsDto bookDto = booksDtoMap.get(bookId);
			if (null == bookDto) {
				bookDto = new GetAllBooksWithAuthorsDto();
				bookDto.setBookId(((Number) o[0]).longValue());
				bookDto.setName((String) o[1]);
				bookDto.setIsbn(((Number) o[2]).longValue());
			}

			bookDto.add(linkTo(methodOn(AuthorController.class).fetchAuthorWithBooks(bookId)).withRel("authors"));

			booksDtoMap.putIfAbsent(bookDto.getBookId(), bookDto);
		}
		return new ArrayList<>(booksDtoMap.values());
	}

	public List<GetAllBooksWithAuthorsDto> transform(Set<BookTable> resultSet) {
		final Map<Long, GetAllBooksWithAuthorsDto> booksDtoMap = new HashMap<>();

		for (BookTable book : resultSet) {
			Long bookId = book.getId();
			GetAllBooksWithAuthorsDto bookDto = booksDtoMap.get(bookId);
			if (null == bookDto) {
				bookDto = new GetAllBooksWithAuthorsDto();
				bookDto.setBookId(bookId);
				bookDto.setName(book.getBookName());
				bookDto.setIsbn(book.getIsbn());
			}
			bookDto.add(linkTo(methodOn(AuthorController.class).fetchAuthorWithBooks(bookId)).withRel("authors"));

			booksDtoMap.putIfAbsent(bookDto.getBookId(), bookDto);
		}
		return new ArrayList<>(booksDtoMap.values());
	}
}
