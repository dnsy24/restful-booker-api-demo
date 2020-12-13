package restful.booker.api;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RestfulBookerIdResponse {
    private String bookingid;

    public RestfulBookerIdResponse() {
    }

    public RestfulBookerIdResponse(String bookingid) {
        this.bookingid = bookingid;
    }
}
