package responses;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by sandeep.roy on 20/03/18.
 */
@Getter
@Setter
public class ErrorResponse {
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }
}
