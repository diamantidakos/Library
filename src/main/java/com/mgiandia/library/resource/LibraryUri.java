package com.mgiandia.library.resource;

public class LibraryUri {

	public static final String BOOKS = "books";
	
	public static final String BOOK_SEARCH = "books/search";
	
	
	public static String bookIdUri(String id){
		return BOOKS + "/" + id;
	}
	
	public static String bookSearchUri(String title){
		return BOOK_SEARCH + "?title=" + title;
	}
	
}
