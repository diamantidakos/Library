package com.mgiandia.library.representation;

import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.domain.BorrowerCategory;
import com.mgiandia.library.persistence.BorrowerCategoryRepository;

import org.mapstruct.AfterMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

import jakarta.inject.Inject;

import com.mgiandia.library.contacts.EmailAddress;

@Mapper(componentModel = "jakarta",
		injectionStrategy = InjectionStrategy.CONSTRUCTOR,
		uses = {BorrowerCategoryMapper.class})
public abstract class BorrowerMapper {
	@Inject 
	BorrowerCategoryRepository borrowerCategoryRepository;
	
	@Mapping(target = "email", source = "email.address")
	public abstract BorrowerRepresentation toRepresentation(Borrower borrower);
	
	@Mapping(target = "address", ignore = true)
	@Mapping(target = "telephone", ignore = true)
	@Mapping(target = "loans", ignore = true)
	public abstract Borrower toModel(BorrowerRepresentation representation);

	@Mapping(target = "email", ignore = true)
	public abstract EmailAddress toEmailAddress(String string);
	
	
	public abstract List<BorrowerRepresentation> toRepresentationList(List<Borrower> borrowers);
	
	@AfterMapping
	protected void connectToCategory(BorrowerRepresentation representation,
			@MappingTarget Borrower borrower) {
		
		if (representation.category != null && representation.category.id != null) {
			BorrowerCategory category = borrowerCategoryRepository.findById(representation.category.id);
			if (category == null) {
				throw new RuntimeException();
			}
			borrower.setCategory(category);
		}
	}
}
