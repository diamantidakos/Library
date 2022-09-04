package com.mgiandia.library.representation;

import java.time.LocalDate;

import io.quarkus.runtime.annotations.RegisterForReflection;
@RegisterForReflection
public class LoanRepresentation {
		public Integer id;
	    public LocalDate loanDate;
	    public LocalDate returnDate;
	    public BorrowerRepresentation borrower;
	    public ItemRepresentation item;
}
