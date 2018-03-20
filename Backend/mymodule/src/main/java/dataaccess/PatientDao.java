package dataaccess;

import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.math.BigDecimal;

/**
 * Created by sandeep.roy on 19/03/18.
 */
@RequiredArgsConstructor(onConstructor = @_(@Inject))
public class PatientDao {
private final EntityManagerFactory emf;


    public EntityManager getEntityManager(){
        return this.emf.createEntityManager();
    }
    public Patients findById(Long id){
        return getEntityManager().getReference(Patients.class, id);
    }

    public Long getMaxId(){
        String query="SELECT SUM(records) from patient_info";
        BigDecimal res= (BigDecimal) getEntityManager().createNativeQuery(query).getSingleResult();
        return res.longValue()+1;
    }

    public void updateRecord(Long id){
        Patients patients=findById(id);
        EntityManager entityManager=getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.getReference(Patients.class,id).setRecords(patients.getRecords()+1);
        entityManager.getTransaction().commit();

    }
}
