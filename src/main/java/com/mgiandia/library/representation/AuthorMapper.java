package com.mgiandia.library.representation;

import com.mgiandia.library.domain.Author;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jakarta",
injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class AuthorMapper {
	public abstract AuthorRepresentation toRepresentation(Author author);
	
}
