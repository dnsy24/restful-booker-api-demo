package restful.booker.cucumber.step.definitions;

import io.cucumber.java.en.When;
import restful.booker.cucumber.RestfulBookerResponseHandler;

import java.util.List;
import java.util.Map;

import static restful.booker.cucumber.RestfulBookerCucumberApi.createBooking;
import static restful.booker.cucumber.RestfulBookerCucumberApi.createBookingWithDto;

public class ModalSteps extends RestfulBookerResponseHandler {

    @When("User upload random data on given end-point")
    public void userUploadRandomDataOnGivenEndPoint() {
        response =
                createBookingWithDto()
                        .prettyPeek();

    }

    @When("User makes api call to create booking")
    public void userMakesApiCallToCreateBooking(List<Map<String, Object>> bookingData) {
        response =
                createBooking(bookingData)
                .prettyPeek();
    }
}
