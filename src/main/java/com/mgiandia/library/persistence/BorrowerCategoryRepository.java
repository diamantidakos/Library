package com.mgiandia.library.persistence;


import javax.enterprise.context.RequestScoped;

import com.mgiandia.library.domain.BorrowerCategory;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@RequestScoped
public class BorrowerCategoryRepository  implements PanacheRepositoryBase<BorrowerCategory, Integer> {

	
}
