package com.mgiandia.library.representation;


import com.mgiandia.library.domain.BorrowerCategory;

import org.mapstruct.Mapper;
import org.mapstruct.InjectionStrategy;

@Mapper(componentModel = "cdi",
		injectionStrategy = InjectionStrategy.CONSTRUCTOR,
		uses= {MoneyMapper.class})
public abstract class BorrowerCategoryMapper { 
	
	abstract BorrowerCategoryRepresentation  toRepresentation(BorrowerCategory category);
	abstract BorrowerCategory toModel(BorrowerCategoryRepresentation representation);
	
}
