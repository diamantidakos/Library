package com.mgiandia.library.persistence;


import java.util.List;

import javax.enterprise.context.RequestScoped;

import com.mgiandia.library.domain.Book;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;

@RequestScoped
public class BookRepository  implements PanacheRepositoryBase<Book, Integer> {

	public List<Book> search(String title) {
		if (title == null) {
			return listAll();
		}
		
		return find("select book from Book book where book.title like :bookTitle" ,
				Parameters.with("bookTitle", title + "%").map())
				.list();
	}
}
