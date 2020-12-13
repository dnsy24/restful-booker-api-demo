package restful.booker.cucumber;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Faker;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;


@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestfulBookerRequest {

    @JsonProperty("firstname")
    private final String firstName;

    @JsonProperty("lastname")
    private final String lastName;

    @JsonProperty("totalprice")
    private final int totalPrice;

    @JsonProperty("depositpaid")
    private final boolean depositPaid;

    @JsonProperty("bookingdates")
    private final Dates bookingDates;

    @JsonProperty("additionalneeds")
    private final String additionalNeeds;

    public RestfulBookerRequest(String firstName, String lastName, int totalPrice, boolean depositPaid, String additionalNeeds) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalPrice = totalPrice;
        this.depositPaid = depositPaid;
        this.bookingDates = new Dates();
        this.additionalNeeds = additionalNeeds;
    }
}

@Getter
@Setter
@ToString
class Dates {
    @JsonProperty("checkin")
    private final String checkIn;

    @JsonProperty("checkout")
    private final String checkOut;

    public Dates() {
        this.checkIn = createCheckInDate();
        this.checkOut = createCheckOutDate();
    }

    private String createCheckInDate() {
        return LocalDate.now().minusDays(new Faker().number().numberBetween(1, 15)).toString();
    }

    private String createCheckOutDate() {
        return LocalDate.now().plusDays(new Faker().number().numberBetween(1, 15)).toString();
    }
}


/**
 * {
 * "bookingid": 1,
 * "booking": {
 * "firstname": "Jim",
 * "lastname": "Brown",
 * "totalprice": 111,
 * "depositpaid": true,
 * "bookingdates": {
 * "checkin": "2018-01-01",
 * "checkout": "2019-01-01"
 * },
 * "additionalneeds": "Breakfast"
 * }
 * }
 */


