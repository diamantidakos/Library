package com.mgiandia.library.resource;

import static io.restassured.RestAssured.when;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.mgiandia.library.Fixture;
import com.mgiandia.library.IntegrationBase;
import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.domain.Loan;
import com.mgiandia.library.representation.ItemRepresentation;
import com.mgiandia.library.representation.LoanRepresentation;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
@QuarkusTest
public class LoanResourceTest extends IntegrationBase {

	@Test
	public void loanItem() {
		
		String uri = Fixture.API_ROOT + LibraryUri.ITEMS + "/" + Fixture.UML_DISTILLED_ID1 +
				"/loan/" + Fixture.DIAMANTIDIS_ID;
		
		LoanRepresentation a = when().post(uri)
				.then()
				.statusCode(201)
				.extract().as(LoanRepresentation.class); 
		
		Integer id = a.id;
		
		
		when().get(Fixture.API_ROOT + LibraryUri.LOANS+ "/" + id)
			.then().statusCode(200);
		
		
		LoanRepresentation active = when()
				.get(Fixture.API_ROOT + LibraryUri.LOANS+ "/item/" + Fixture.UML_DISTILLED_ID1)
				.then()
				.statusCode(200)
				.extract().as(LoanRepresentation.class);
		
		when()
				.post(Fixture.API_ROOT + LibraryUri.LOANS+ "/" + id + "/returnItem") 
				.then()
				.statusCode(204);
		
	}
}
