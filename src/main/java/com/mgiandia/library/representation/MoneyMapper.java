package com.mgiandia.library.representation;

import com.mgiandia.library.util.Money;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi",
injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class MoneyMapper {
	public abstract MonetaryAmount toRepresentation(Money money);
	
	public abstract Money toModel(MonetaryAmount value);
	
}
