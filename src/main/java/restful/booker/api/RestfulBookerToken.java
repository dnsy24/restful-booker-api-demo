package restful.booker.api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RestfulBookerToken {

    private static String getToken(String username, String password) {
        RestfulBookerCredentials restfulBookerCredentials = new RestfulBookerCredentials(username, password);
        Response response =
                given()
                        .basePath("/auth")
                        .body(restfulBookerCredentials)
                        .when()
                        .post();

        return response.jsonPath().get("token").toString();
    }
}
