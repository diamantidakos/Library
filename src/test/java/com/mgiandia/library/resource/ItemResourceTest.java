package com.mgiandia.library.resource;

import static io.restassured.RestAssured.when;

import com.mgiandia.library.Fixture;
import com.mgiandia.library.Fixture.Items;
import com.mgiandia.library.IntegrationBase;
import com.mgiandia.library.representation.ItemRepresentation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ItemResourceTest extends IntegrationBase {

	@Test
	public void find() {
		ItemRepresentation a = when().get(Fixture.API_ROOT + LibraryUri.ITEMS + "/" + Items.UML_DISTILLED_ID1)
			.then()
			.statusCode(200)
			.extract().as(ItemRepresentation.class); 
		Assertions.assertEquals(Items.UML_DISTILLED_ID1, a.itemNumber);
	}
	
	

}
