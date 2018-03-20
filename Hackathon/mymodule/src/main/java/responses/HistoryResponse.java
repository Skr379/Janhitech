package responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import dataaccess.History;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sandeep.roy on 11/03/18.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HistoryResponse {
    @JsonProperty("histories")
    private List<PatientDiagnosisData> histories;

    public HistoryResponse(List<History> histories) {
        this.histories= new ArrayList();
        for(History history:histories){
            PatientDiagnosisData patientDiagnosisData = new PatientDiagnosisData();
            patientDiagnosisData.setId(history.getId());
            patientDiagnosisData.setAge(history.getAge());
            patientDiagnosisData.setDiagnosis(history.getDiagnosis());
            patientDiagnosisData.setPatientId(history.getPatientId());
            patientDiagnosisData.setPrescriptions(history.getPrescriptions());
            patientDiagnosisData.setSymptoms(history.getSymptoms().replace("{","").replace("}","").replace("\'",""));
            patientDiagnosisData.setTreatedAt(history.getTreatedAt());
            patientDiagnosisData.setTreatedBy(history.getTreatedBy());
            patientDiagnosisData.setTreatmentDate(history.getTreatmentDate());
            this.histories.add(patientDiagnosisData);
        }
    }
}
