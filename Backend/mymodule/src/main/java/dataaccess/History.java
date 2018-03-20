package dataaccess;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by sandeep.roy on 11/03/18.
 */
@Entity
@Table(name = "patient_history")
@Getter
@Setter
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "patient_id")
    Long patientId;
    int age;
    String symptoms;
    String diagnosis;
    String prescriptions;
    @Column(name = "treated_at")
    String treatedAt;
    @Column(name = "treated_by")
    String treatedBy;
    @Column(name = "treatment_date")
    String treatmentDate;
}
