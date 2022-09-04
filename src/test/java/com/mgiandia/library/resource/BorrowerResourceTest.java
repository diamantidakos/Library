package com.mgiandia.library.resource;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import java.util.List;

import com.mgiandia.library.Fixture;
import com.mgiandia.library.Fixture.BorrowerCategories;
import com.mgiandia.library.IntegrationBase;
import com.mgiandia.library.representation.BorrowerCategoryRepresentation;
import com.mgiandia.library.representation.BorrowerRepresentation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;

@QuarkusTest
public class BorrowerResourceTest extends IntegrationBase {

	@Test
	@TestTransaction
	public void find() {
		BorrowerRepresentation a = when().get(Fixture.API_ROOT + LibraryUri.BORROWERS + "/" + Fixture.Borrowers.DIAMANTIDIS_ID)
			.then()
			.statusCode(200)
			.extract().as(BorrowerRepresentation.class); 
		Assertions.assertEquals(Fixture.Borrowers.DIAMANTIDIS_ID, a.borrowerNo);
	}
	
	@Test
	@TestTransaction
	public void findNonExisting() {
		when().get(Fixture.API_ROOT + LibraryUri.BORROWERS + "/" + 4711)
			.then()
			.statusCode(404);
	}
	
	@Test
	@TestTransaction
	public void list() {
		List<BorrowerRepresentation> borrowers = when().get(Fixture.API_ROOT + LibraryUri.BORROWERS)
			.then()
			.statusCode(200)
			.extract().as(new TypeRef<List<BorrowerRepresentation>>() {});
		
		Assertions.assertEquals(Fixture.Borrowers.COUNT, borrowers.size());
	}
	
	@Test
	@TestTransaction
	public void create() {
		BorrowerRepresentation representation = new BorrowerRepresentation();
		
		representation.borrowerNo = 166;
		representation.firstName = "giannis";
		representation.lastName = "ioannou";
		
		BorrowerCategoryRepresentation category = new BorrowerCategoryRepresentation();
		category.id = BorrowerCategories.BORROWER_CATEGORY_PROF_ID;

		representation.category = category;
		
		BorrowerRepresentation created = given()
			.contentType(ContentType.JSON)
			.body(representation)
			.when().post(Fixture.API_ROOT + LibraryUri.BORROWERS)
			.then().statusCode(201).header("Location", Fixture.API_ROOT + LibraryUri.BORROWERS + "/" + 166)
			.extract().as(BorrowerRepresentation.class);
		
	
		Assertions.assertEquals(166, created.borrowerNo);
		Assertions.assertNotNull(created.category);
	}
	
	
	@Test
	@TestTransaction
	public void update() {
		BorrowerRepresentation borrower = when().get(Fixture.API_ROOT + LibraryUri.BORROWERS + "/" + Fixture.Borrowers.DIAMANTIDIS_ID)
		.then()
		.statusCode(200)
		.extract().as(BorrowerRepresentation.class); 
		
		borrower.firstName = "NIKOLAS";
		
		given()
			.contentType(ContentType.JSON)
			.body(borrower)
			.when().post(Fixture.API_ROOT + LibraryUri.BORROWERS + "/" + Fixture.Borrowers.DIAMANTIDIS_ID)
			.then().statusCode(204);
		
	
		BorrowerRepresentation updated = when().get(Fixture.API_ROOT + LibraryUri.BORROWERS + "/" + Fixture.Borrowers.DIAMANTIDIS_ID)
				.then()
				.statusCode(200)
				.extract().as(BorrowerRepresentation.class); 
		
		Assertions.assertEquals("NIKOLAS", updated.firstName);
	}
	
}
