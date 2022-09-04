package com.mgiandia.library.representation;

import com.mgiandia.library.domain.ISBN;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi",
injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public class IsbnMapper {
	public String toString(ISBN isbn) {
		return isbn == null ? null : isbn.getValue();
	}
}
