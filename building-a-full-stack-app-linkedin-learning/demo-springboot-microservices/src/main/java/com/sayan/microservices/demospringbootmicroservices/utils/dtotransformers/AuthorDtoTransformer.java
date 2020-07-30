package com.sayan.microservices.demospringbootmicroservices.utils.dtotransformers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.sayan.microservices.demospringbootmicroservices.dto.GetAllBooksWithAuthorsDto;
import com.sayan.microservices.demospringbootmicroservices.dto.GetAuthorWithBooksDto;
import com.sayan.microservices.demospringbootmicroservices.entity.AuthorTable;

@Component
public class AuthorDtoTransformer {
	
	public List<GetAuthorWithBooksDto> transform(List<Object[]> result) {
		
		final Map<Long, GetAuthorWithBooksDto> authorsDtoMap = new HashMap<>();
		for (Object[] o : result) {
			Long authorId = ((Number)o[0]).longValue();
			
			GetAuthorWithBooksDto authorDto = authorsDtoMap.get(authorId);
			if(null==authorDto) {
				authorDto = new GetAuthorWithBooksDto();
				authorDto.setAuthorId(((Number)o[0]).longValue());
				authorDto.setLastName((String)o[1]);
				authorDto.setFirstName((String)o[2]);
			}
			
			authorsDtoMap.putIfAbsent(authorDto.getAuthorId(), authorDto);
		}		
		/*GetAuthorWithBooksDto authorDto=null;
		for (AuthorTable author: result) {
			authorDto = new GetAuthorWithBooksDto();
			authorDto.setAuthorId(author.getId());
			authorDto.setLastName(author.getAuthorLastName());
			authorDto.setFirstName(author.getAuthorFirstName());
		}*/
		return new ArrayList<>(authorsDtoMap.values());
	}
}
