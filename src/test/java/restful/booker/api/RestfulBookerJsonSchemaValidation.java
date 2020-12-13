package restful.booker.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

public class RestfulBookerJsonSchemaValidation extends RestfulBookerApiBaseTest {

    /**
     * https://extendsclass.com/json-schema-validator.html
     */

    @DisplayName("Json Schema Validation")
    @Test
    void jsonSchemaValidationTest() {
        response = get("/booking");
        List<RestfulBookerIdResponse> list = response.jsonPath().getList("", RestfulBookerIdResponse.class);
        String bookingId = list.get(new Random().nextInt(list.size())).getBookingid();
        System.out.println(">>> Booking ID: " + bookingId);

        given()
                .basePath("/booking")
                .pathParam("id", bookingId)
                .when()
                .get("/{id}")
                .prettyPeek()
                .then()
                .body(matchesJsonSchemaInClasspath("booking-schema.json"))
                .body("totalprice", is(instanceOf(Integer.class)))
                .body("depositpaid", is(instanceOf(Boolean.class)))
                .statusCode(200);

    }
}
