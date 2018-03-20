package responses;

import dataaccess.Patients;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by sandeep.roy on 11/03/18.
 */
@Getter
@Setter
public class PatientResponse {

    private long id;
    private String name;
    private int age;
    private Long contactNumber;
    private String gender;
    private Integer records;
    public PatientResponse(Patients patients) {
        this.id = patients.getId();
        this.name = patients.getName();
        this.age = patients.getAge();
        this.contactNumber = patients.getContactNumber();
        this.gender = patients.getGender();
        this.records=patients.getRecords();
    }
}
