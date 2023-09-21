package com.mgiandia.library.representation;

import com.mgiandia.library.util.Money;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "jakarta",
injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class MoneyMapper {
	public abstract MonetaryAmount toRepresentation(Money money);
	
	@Mapping(target = "divide", ignore = true)
	@Mapping(target = "minus", ignore = true)
	@Mapping(target = "plus", ignore = true)
	@Mapping(target = "times", ignore = true)
	public abstract Money toModel(MonetaryAmount value);
	
}
