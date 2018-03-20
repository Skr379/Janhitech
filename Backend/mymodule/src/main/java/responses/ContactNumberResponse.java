package responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import dataaccess.Patients;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by sandeep.roy on 19/03/18.
 */
@Getter
@Setter
public class ContactNumberResponse {

    @JsonProperty("contact_number")
    public Long contactNumber;

    public ContactNumberResponse(Patients patients) {
        this.contactNumber = patients.getContactNumber();
    }
}
