package com.mgiandia.library.representation;

import java.util.ArrayList;
import java.util.List;

import com.mgiandia.library.domain.Publisher;

import io.quarkus.runtime.annotations.RegisterForReflection;
@RegisterForReflection
public class BookRepresentation {
	public Integer id;
	public String isbn;
	public String title;
	public String publication;
	public int publicationYear;
	public PublisherRepresentation publisher;
	public List<AuthorRepresentation> authors = new ArrayList<AuthorRepresentation>();
}
