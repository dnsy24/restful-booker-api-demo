package restful.booker.cucumber;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import restful.booker.api.RestfulBookerDTO;
import restful.booker.utilities.owner.ConfigurationProvider;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestfulBookerCucumberApi {

    static final String URI = ConfigurationProvider.getInstance().getConfiguration().uriName();

    public static Response createBookingWithDto() {
        RestfulBookerDTO booking = new RestfulBookerDTO();
        return createResponse(booking);
    }

    public static Response createBookingWithMap(Map<String, Object> data) {
        return createResponse(data);
    }

    public static Response createBookingWithJsonObject(JSONObject object) {
        return createResponse(object);
    }


    private static Response createResponse(Object obj) {
        return given()
                .baseUri(URI)
                .basePath("/booking")
                .contentType(ContentType.JSON)
                .log().all()
                .body(obj)
                .post();
    }

}

