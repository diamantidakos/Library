package com.mgiandia.library.representation;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.quarkus.runtime.annotations.RegisterForReflection;
@RegisterForReflection
public class LoanRepresentation {
		public Integer id;
	    public String loanDate;
	    public String returnDate;
	    public BorrowerRepresentation borrower;
	    public ItemRepresentation item;
}
