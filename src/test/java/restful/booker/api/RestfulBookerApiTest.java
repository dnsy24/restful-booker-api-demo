package restful.booker.api;

import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import restful.booker.utilities.testdata.TestDataImpl;
import restful.booker.utilities.testdata.td.TestData;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.*;


@Slf4j
public class RestfulBookerApiTest extends RestfulBookerApiBaseTest {

    TestData data = new TestDataImpl();

    private static String getTokenFromRestfulBooker(String username, String password) {
        RestfulBookerCredentials restfulBookerCredentials = new RestfulBookerCredentials(username, password);
        Response response =
                given()
                        .basePath("/auth")
                        .body(restfulBookerCredentials)
                        .when()
                        .post();
        System.out.println(response.getStatusCode());
        return response.jsonPath().get("token").toString();
    }


    @DisplayName("Simple Get Booking ID Request")
    @Test
    void simpleGetBookingIdRequestTest() {
        response = get("/booking");
        List<RestfulBookerIdResponse> list = response.jsonPath().getList("", RestfulBookerIdResponse.class);
        log.info(">>> First Booking ID: " + list.get(0).getBookingid());
//        System.out.println(">>> First Booking ID: " + list.get(0).getBookingid());
    }


    @DisplayName("Get Booking IDs")
    @Test
    void getBookingIdsTest() {
        response =
                given()
                        .basePath("/booking")
                        .when()
                        .get()
                        .prettyPeek();
        assertThat(response.getStatusCode(), is(200));
        log.info("booking id: " + response.jsonPath().getString("bookingid"));
//        System.out.println("booking id: " + response.jsonPath().getString("bookingid"));
    }


    @DisplayName("Get Booking By Id")
    @Test
    void getBooking() {
        response =
                given()
                        .basePath("/booking")
                        .pathParam("id", 7)
                        .when()
                        .get("/{id}")
                        .prettyPeek();
        assertThat(response.getStatusCode(), is(200));

        Map<String, String> responseMap = response.jsonPath().getMap("");
//        System.out.println("response map: " + responseMap);
        log.info("response map: " + responseMap);

        System.out.println("firstname in map: " + responseMap.get("firstname"));
        System.out.println("firstname: " + response.jsonPath().getString("firstname"));

        RestfulBookerBookingResponse bookingResponse = response.as(RestfulBookerBookingResponse.class);
        System.out.println("Booking Response: " + bookingResponse);
    }


    @DisplayName("Create Booking With Json File")
    @Test
    void createBookingWithJsonTest() {
        File data = new File("src/test/resources/restful.booker/restful-booker.json");
        response =
                given()
                        .basePath("/booking")
                        .body(data)
                        .when()
                        .post()
                        .prettyPeek();

        String bookingId = response.jsonPath().getString("bookingid");
        System.out.println("Booking ID: " + bookingId);

        String firstname = response.jsonPath().getString("booking.firstname");
        System.out.println("First name: " + firstname);
        System.out.println(response.jsonPath().getString("booking.bookingdates.checkin"));

        Map<String, String> resultMap = response.jsonPath().getMap("");
//        System.out.println(resultMap);
        log.info("Result map: " + resultMap);
    }

    @DisplayName("Create Booking With JTwig Template")
    @Test
    void createBookingWithTemplateEngineTest() {
        String firstName = data.getFirstName();
        String lastName = data.getLastName();
        String additionalNeeds = data.getAdditionalNeeds();

        JtwigTemplate template = JtwigTemplate.classpathTemplate("restful.booker/restful-booker-template.json");
        JtwigModel model =
                JtwigModel.newModel()
                        .with("firstname", firstName)
                        .with("lastname", lastName)
                        .with("additionalneeds", additionalNeeds);

//        response =
                given()
                        .basePath("/booking")
                        .body(template.render(model))
                        .post()
                        .then()
                        .statusCode(200)
                        .body("booking.firstname", equalTo(firstName))
                        .body("booking.lastname", equalTo(lastName))
                        .body("booking.additionalneeds", equalTo(additionalNeeds));

        /*assertThat(response.getStatusCode(), is(200));
        assertThat(response.jsonPath().getString("booking.firstname"), is(firstName));
        assertThat(response.jsonPath().getString("booking.lastname"), is(lastName));
        System.out.println(">>> First name: " + response.jsonPath().getString("booking.firstname"));*/

    }


    /**
     * body parameter booker object is a serialized nested json file
     */

    @DisplayName("Create Booking With DTO")
    @RepeatedTest(1)
    void createBookingWithDto() {
        RestfulBookerDTO booking = new RestfulBookerDTO();

        response =
                given()
                        .basePath("/booking")
                        .body(booking)
                        .post()
                        .prettyPeek();
        assertThat(response.getStatusCode(), is(200));
        System.out.println("-->> id: " + response.jsonPath().getString("bookingid"));
        Map<String, Object> resultMap = response.jsonPath().getMap("");
        System.out.println("Result map: " + resultMap);
    }


    @DisplayName("Update Booking")
    @ParameterizedTest
    @CsvFileSource(resources = "/restful.booker/restful-booker.csv")
    void updateBookingById(String username, String password) {

        response = get("/booking");
        List<RestfulBookerIdResponse> list = response.jsonPath().getList("", RestfulBookerIdResponse.class);
        String bookingId = list.get(0).getBookingid();

        RestfulBookerDTO updatedBooking = new RestfulBookerDTO();
        String token = getTokenFromRestfulBooker(username, password);

        response =
                given()
                        .basePath("/booking")
//                        .auth()
//                        .preemptive()
//                        .basic("YWRtaW46cGFzc3dvcmQxMjM=", token)
                        .cookie("token", token)
                        .pathParam("id", bookingId)
                        .body(updatedBooking)
                        .when()
                        .put("/{id}")
                        .prettyPeek();

        assertThat(response.getStatusCode(), is(200));
    }

    /**
     * partial update using Json object or Map object
     */

    @DisplayName("Partial Update Booking")
    @ParameterizedTest
    @CsvFileSource(resources = "/restful.booker/restful-booker.csv")
    void patchUpdateBookingTest(String username, String password) {

        response = get("/booking");
        List<RestfulBookerIdResponse> list = response.jsonPath().getList("", RestfulBookerIdResponse.class);
        String bookingId = list.get(list.size() - 1).getBookingid();
        System.out.println(">>> Booking ID: " + bookingId);

        String tokenFromRestfulBooker = getTokenFromRestfulBooker(username, password);

//        with Json object

//        using raw JSONObject class object
//        JSONObject patchRequest = new JSONObject();
//        patchRequest.put("firstname", data.getFirstName());


//      with Map object

        Map<String, Object> patchData = new HashMap<>();
        patchData.put("firstname", data.getFirstName());
        patchData.put("totalprice", data.getRandomPrice());
        patchData.put("depositpaid", Boolean.FALSE);

        response =
                given()
                        .basePath("/booking")
                        .cookie("token", tokenFromRestfulBooker)
                        .pathParam("id", bookingId)
                        .body(patchData)
                        .when()
                        .patch("/{id}")
                        .prettyPeek();
        assertThat(response.getStatusCode(), is(200));

    }

    @DisplayName("Delete Random Booking")
    @ParameterizedTest
    @CsvFileSource(resources = "/restful.booker/restful-booker.csv")
    void deleteBookingByRandomId(String username, String password) {
        String tokenFromRestfulBooker = getTokenFromRestfulBooker(username, password);

        response = get("/booking");
        List<RestfulBookerIdResponse> list = response.jsonPath().getList("", RestfulBookerIdResponse.class);
        String bookingId = list.get(new Random().nextInt(list.size())).getBookingid();
        System.out.println(">>> Booking ID: " + bookingId);

        response =
                given()
                        .basePath("/booking")
                        .cookie("token", tokenFromRestfulBooker)
                        .pathParam("id", bookingId)
                        .when()
                        .delete("/{id}")
                        .prettyPeek();
        assertThat(response.getStatusCode(), is(201));

    }

}


