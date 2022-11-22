package com.mgiandia.library.resource;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.given;
import java.util.List;

import javax.annotation.PreDestroy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mgiandia.library.Fixture;
import com.mgiandia.library.IntegrationBase;
import com.mgiandia.library.domain.Book;
import com.mgiandia.library.representation.BookRepresentation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
@QuarkusTest
public class BookResourceTest  extends IntegrationBase {

	@Test
	@TestTransaction
	public void find() {
		BookRepresentation a = when().get(Fixture.API_ROOT + LibraryUri.BOOKS + "/" + Fixture.Books.UML_USER_GUIDE_ID)
				.then()
				.statusCode(200)
				.extract().as(BookRepresentation.class); 
		Assertions.assertEquals(Fixture.Books.UML_USER_GUIDE_ID, a.id);
	}

	@Test
	@TestTransaction
	public void search() throws JsonMappingException, JsonProcessingException {
		
		
		List<BookRepresentation> books = given().queryParam("title", "UML").when().get(Fixture.API_ROOT + LibraryUri.BOOKS)
				.then()
				.statusCode(200)
				.extract().as(new TypeRef<List<BookRepresentation>>() {}) ;
		
		Assertions.assertEquals(1, books.size());
		
	}

	
}
