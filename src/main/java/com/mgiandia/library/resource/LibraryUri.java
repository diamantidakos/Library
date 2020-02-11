package com.mgiandia.library.resource;

public class LibraryUri {

	/**
	 * /books
	 */
	public static final String BOOKS = "books";

	/**
	 * /loans
	 */
	public static final String LOANS = "loans";

	/**
	 * /items
	 */
	public static final String ITEMS = "items";

	public static final String BOOK_SEARCH = "books/search";

	/**
	 * /books/{id}, <br>
	 * e.g. /books/1
	 */
	public static String bookIdUri(String id) {
		return BOOKS + "/" + id;
	}

	/**
	 * /books?title={title}, <br>
	 * e.g. /books?title=UML
	 */
	public static String bookSearchUri(String title) {
		return BOOK_SEARCH + "?title=" + title;
	}
	
	public static String loanUri(String itemId){
		return LOANS + "/" + itemId;
	}

}
