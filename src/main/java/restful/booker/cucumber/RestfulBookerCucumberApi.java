package restful.booker.cucumber;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import restful.booker.api.RestfulBookerDTO;
import restful.booker.utilities.owner.ConfigurationProvider;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestfulBookerCucumberApi {

    static final String URI = ConfigurationProvider.getInstance().getConfiguration().uriName();

    public static Response createBookingWithDto() {
        RestfulBookerDTO booking = new RestfulBookerDTO();

        return given()
                .baseUri(URI)
                .basePath("/booking")
                .contentType(ContentType.JSON)
                .log().all()
                .body(booking)
                .post();
    }

    public static Response createBooking(List<Map<String, Object>> data) {

        return given()
                .baseUri(URI)
                .basePath("/booking")
                .contentType(ContentType.JSON)
                .log().all()
                .body(data)
                .post();
    }
}

