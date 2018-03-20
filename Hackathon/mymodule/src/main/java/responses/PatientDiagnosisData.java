package responses;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by sandeep.roy on 11/03/18.
 */
@Getter
@Setter
public class PatientDiagnosisData {
    private Long id;
    Long patientId;
    int age;
    String symptoms;
    String diagnosis;
    String prescriptions;
    String treatedAt;
    String treatedBy;
    String treatmentDate;
}
