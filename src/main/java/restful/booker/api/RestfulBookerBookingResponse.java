package restful.booker.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * firstname=Susan, lastname=Wilson, totalprice=107, depositpaid=true, bookingdates={checkin=2017-04-16, checkout=2020-04-15*/
@Setter
@Getter
@ToString
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestfulBookerBookingResponse {

    private String firstname;

    private String lastname;

    private int totalprice;

    private Boolean depositpaid;

    private BookingDates bookingdates;

    public RestfulBookerBookingResponse() {
    }

    @Getter
    @Setter
    @ToString
    static class BookingDates {
        private String checkin;
        private String checkout;

    }
}
