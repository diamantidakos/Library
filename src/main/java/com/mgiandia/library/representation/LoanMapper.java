package com.mgiandia.library.representation;

import com.mgiandia.library.domain.Loan;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi",
		injectionStrategy = InjectionStrategy.CONSTRUCTOR,
		uses = {BorrowerMapper.class, ItemMapper.class})
public abstract class LoanMapper {
	
	
	public abstract LoanRepresentation toRepresentation(Loan loan);
	
	
	
	
}
