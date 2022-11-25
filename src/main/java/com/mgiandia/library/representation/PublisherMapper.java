package com.mgiandia.library.representation;

import com.mgiandia.library.domain.Publisher;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi",
injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class PublisherMapper {
	public abstract PublisherRepresentation toRepresentation(Publisher publisher);
	
}
