package com.mgiandia.library.representation;

import java.util.List;

import com.mgiandia.library.domain.Item;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi",
		injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class ItemMapper {
	
	
	@Mapping(target = "title", source = "book.title")
	@Mapping(target = "bookId", source = "book.id")
	public abstract ItemRepresentation toRepresentation(Item item);
	
	public abstract List<ItemRepresentation> toRepresentationList(List<Item> item);
	
	
}
