package com.mgiandia.library.persistence;


import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.NoResultException;

import com.mgiandia.library.domain.Loan;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;

@RequestScoped
public class LoanRepository  implements PanacheRepositoryBase<Loan, Integer> {
	public List<Loan> activeLoans() {
		return (List<Loan>) find("select l from Loan l where l.returnDate is null").list();
	}
	
	public Loan activeForItem(Integer itemId) {
		PanacheQuery<Loan> query = find("select l from Loan l where l.item.id=:itemId and l.returnDate is null",
				Parameters.with("itemId", itemId));
		try {
			return 	 query.singleResult();
		} catch (NoResultException e) {
			return null;
		} 
	}
	
}
