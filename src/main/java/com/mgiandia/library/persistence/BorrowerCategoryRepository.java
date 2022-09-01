package com.mgiandia.library.persistence;


import javax.enterprise.context.RequestScoped;
import javax.persistence.NoResultException;

import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.domain.BorrowerCategory;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;

@RequestScoped
public class BorrowerCategoryRepository  implements PanacheRepositoryBase<BorrowerCategory, Integer> {

	
}
