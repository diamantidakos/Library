package com.mgiandia.library.representation;

import java.util.List;

import com.mgiandia.library.domain.Book;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jakarta",
injectionStrategy = InjectionStrategy.CONSTRUCTOR,
		uses = {PublisherMapper.class, AuthorMapper.class, IsbnMapper.class})
public abstract class BookMapper {
	public abstract BookRepresentation toRepresentation(Book book);
	
	public abstract List<BookRepresentation> toRepresentationList(List<Book> book);
}
