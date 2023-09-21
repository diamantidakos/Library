package com.mgiandia.library.persistence;


import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.NoResultException;

import com.mgiandia.library.domain.Borrower;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;

@RequestScoped
public class BorrowerRepository  implements PanacheRepositoryBase<Borrower, Integer> {

	public Borrower findWithDetails(Integer id) {
		PanacheQuery<Borrower> query = find("select b from Borrower b left join fetch b.category where b.borrowerNo = :id", Parameters.with("id", id).map());
		try {
			return query.singleResult();	
		} catch(NoResultException ex) {
			return null;
		}
		
	}
}
