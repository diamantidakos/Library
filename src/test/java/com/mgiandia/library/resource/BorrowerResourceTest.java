package com.mgiandia.library.resource;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;


import com.mgiandia.library.Fixture;
import com.mgiandia.library.IntegrationBase;
import com.mgiandia.library.representation.BorrowerCategoryRepresentation;
import com.mgiandia.library.representation.BorrowerRepresentation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
public class BorrowerResourceTest extends IntegrationBase {

	@Test
	@TestTransaction
	public void find() {
		BorrowerRepresentation a = when().get(Fixture.API_ROOT + LibraryUri.BORROWERS + "/" + Fixture.DIAMANTIDIS_ID)
			.then()
			.statusCode(200)
			.extract().as(BorrowerRepresentation.class); 
		Assertions.assertEquals(Fixture.DIAMANTIDIS_ID, a.borrowerNo);
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
	public void create() {
		BorrowerRepresentation representation = new BorrowerRepresentation();
		
		representation.borrowerNo = 166;
		representation.firstName = "giannis";
		representation.lastName = "ioannou";
		
		BorrowerCategoryRepresentation category = new BorrowerCategoryRepresentation();
		category.id = Fixture.BORROWER_CATEGORY_PROF_ID;

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
		BorrowerRepresentation a = when().get(Fixture.API_ROOT + LibraryUri.BORROWERS + "/" + Fixture.DIAMANTIDIS_ID)
		.then()
		.statusCode(200)
		.extract().as(BorrowerRepresentation.class); 
		
		a.firstName = "NIKOLAS";
		
		given()
			.contentType(ContentType.JSON)
			.body(a)
			.when().post(Fixture.API_ROOT + LibraryUri.BORROWERS + "/" + Fixture.DIAMANTIDIS_ID)
			.then().statusCode(204);
		
	
		BorrowerRepresentation updated = when().get(Fixture.API_ROOT + LibraryUri.BORROWERS + "/" + Fixture.DIAMANTIDIS_ID)
				.then()
				.statusCode(200)
				.extract().as(BorrowerRepresentation.class); 
		
		Assertions.assertEquals("NIKOLAS", updated.firstName);
	}
	
}
