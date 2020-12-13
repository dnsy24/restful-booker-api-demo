package restful.booker.cucumber.step.definitions;

import io.cucumber.java.en.Then;
import restful.booker.cucumber.RestfulBookerResponseHandler;

import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.*;

public class AssertionSteps extends RestfulBookerResponseHandler {

    @Then("Server should handle it and return success status")
    public void serverShouldHandleItAndReturnSuccessStatus() {
        assertThat(response.statusCode(), is(200));
        System.out.println("-->> id: " + response.jsonPath().getString("bookingid"));

    }

    @Then("User verifies following data in Json body")
    public void userVerifiesFollowingDataInJsonBody(Map<String, Object> received) {


    }
}
