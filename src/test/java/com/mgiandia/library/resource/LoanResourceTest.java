package com.mgiandia.library.resource;

import static io.restassured.RestAssured.when;

import com.mgiandia.library.Fixture;
import com.mgiandia.library.Fixture.Items;
import com.mgiandia.library.IntegrationBase;
import com.mgiandia.library.domain.ItemState;
import com.mgiandia.library.representation.ItemRepresentation;
import com.mgiandia.library.representation.LoanRepresentation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
@QuarkusTest
public class LoanResourceTest extends IntegrationBase {

	@Test
	public void loanItem() {
		
		String uri = Fixture.API_ROOT + LibraryUri.ITEMS + "/" + Items.UML_DISTILLED_ID1 +
				"/loan/" + Fixture.Borrowers.DIAMANTIDIS_ID;
		
		LoanRepresentation loan = when().post(uri)
				.then()
				.statusCode(201)
				.extract().as(LoanRepresentation.class); 
		
		Integer id = loan.id;
		
		
		when().get(Fixture.API_ROOT + LibraryUri.LOANS+ "/" + id)
			.then().statusCode(200);
		
		
		LoanRepresentation active = when()
				.get(Fixture.API_ROOT + LibraryUri.LOANS+ "/item/" + Items.UML_DISTILLED_ID1)
				.then()
				.statusCode(200)
				.extract().as(LoanRepresentation.class);
		
		Assertions.assertNotNull(active.loanDate);

		ItemRepresentation item = when().get(Fixture.API_ROOT + LibraryUri.ITEMS+ "/" + Items.UML_DISTILLED_ID1)
				.then().statusCode(200).extract().as(ItemRepresentation.class);
	
		Assertions.assertEquals(ItemState.LOANED, item.state);
		
		when()
				.post(Fixture.API_ROOT + LibraryUri.LOANS+ "/" + id + "/returnItem") 
				.then()
				.statusCode(204);
		
	}
}
