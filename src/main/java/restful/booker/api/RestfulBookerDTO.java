package restful.booker.api;

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
public class RestfulBookerDTO {

    @JsonProperty("firstname")
    private final String firstName;

    @JsonProperty("lastname")
    private final String lastName;

    @JsonProperty("totalprice")
    private final int totalPrice;

    @JsonProperty("depositpaid")
    private final boolean depositPaid;

    @JsonProperty("bookingdates")
    private final BookingDates bookingDates;

    @JsonProperty("additionalneeds")
    private final String additionalNeeds;

    public RestfulBookerDTO() {
        this.firstName = creatFirstName();
        this.lastName = createLastName();
        this.totalPrice = createTotalPrice();
        this.depositPaid = isDepositPaid();
        this.bookingDates = new BookingDates();
        this.additionalNeeds = createAdditionalNeeds();
    }

    private String creatFirstName() {
        return new Faker().name().firstName();
    }

    private String createLastName() {
        return new Faker().name().lastName();
    }

    private int createTotalPrice() {
        return new Faker().number().numberBetween(111, 999);
    }

    private boolean isDepositPaid() {
        return Math.random() < 0.5 ? Boolean.FALSE : Boolean.TRUE;
    }

    private String createAdditionalNeeds() {
        return Math.random() < 0.5 ? "Breakfast" : "Nothing";
    }

}

@Getter
@Setter
@ToString
class BookingDates {
    @JsonProperty("checkin")
    private final String checkIn;

    @JsonProperty("checkout")
    private final String checkOut;

    public BookingDates() {
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
    "bookingid": 1,
    "booking": {
        "firstname": "Jim",
        "lastname": "Brown",
        "totalprice": 111,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2018-01-01",
            "checkout": "2019-01-01"
        },
        "additionalneeds": "Breakfast"
    }
}
* */
