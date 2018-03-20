package dataaccess;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by sandeep.roy on 11/03/18.
 */
@Entity
@Table(name = "patient_info")
@Getter
@Setter
public class Patients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int age;
    @Column(name = "contact_number")
    private Long contactNumber;
    private String gender;
    private Integer records;

}
