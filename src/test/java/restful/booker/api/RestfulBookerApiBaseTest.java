package restful.booker.api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import restful.booker.utilities.config.ConfigurationReader;

import static io.restassured.RestAssured.reset;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class RestfulBookerApiBaseTest {

    protected RequestSpecification requestSpec;
    protected Response response;

    @BeforeEach
    void initEach() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(ConfigurationReader.getInstance().getProperty("restfulbooker.base_url"))
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
        RestAssured.requestSpecification = requestSpec;

    }

    @AfterEach
    void destroyEach() {
        reset();
    }
}
