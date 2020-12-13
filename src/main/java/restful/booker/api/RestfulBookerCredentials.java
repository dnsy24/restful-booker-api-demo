package restful.booker.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestfulBookerCredentials {
    private final String username;
    private final String password;

    public RestfulBookerCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "RestfulBookerDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
