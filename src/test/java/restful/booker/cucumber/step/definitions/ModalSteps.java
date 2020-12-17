package restful.booker.cucumber.step.definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import restful.booker.cucumber.RestfulBookerResponseHandler;
import restful.booker.training.Training;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static restful.booker.cucumber.RestfulBookerCucumberApi.*;

public class ModalSteps extends RestfulBookerResponseHandler {

    @When("User upload random data on given end-point")
    public void userUploadRandomDataOnGivenEndPoint() {
        response =
                createBookingWithDto()
                        .prettyPeek();

    }


    @When("User creates request body with parameters to send with POST method")
    public void userCreatesRequestBodyWithParametersToSendWithPOSTMethod(List<Map<String, Object>> newRequest) {
        Map<String, Object> reqMap = newRequest.get(0);
        requestMap = new LinkedHashMap<>(reqMap);
    }


    @And("User adds to the body booking dates with parameters")
    public void userAddsToTheApiCallBookingDatesWithParameters(Map<String, Object> bookingDates) {
        for (Map.Entry<String, Object> entry : requestMap.entrySet()) {
            if (entry.getKey().equals("bookingdates")) {
                requestMap.put("bookingdates", bookingDates);
            }
        }
    }


    @And("User makes api call with POST method to create booking")
    public void userMakesApiCallWithPostMethodToCreateBooking() {
        response =
                createBookingWithMap(requestMap)
                .prettyPeek();
    }

}
