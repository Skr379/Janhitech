package dataaccess;

import com.google.inject.Inject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by sandeep.roy on 11/03/18.
 */
public class HistoryDao {
   // private static String driverName = "org.apache.hive.jdbc.HiveDriver";
    private static String tableName="patient_history";
    private final Statement stmt;
    private final PatientDao patientDao;

    @Inject
    public HistoryDao(Statement stmt, PatientDao patientDao) {
        this.stmt = stmt;
        this.patientDao = patientDao;
    }


    public List<History> getHistory (Long id,Integer page,Integer size) throws SQLException {

        String query= "select * from patient_history where patient_id="+id;

        stmt.execute(query);
        ResultSet res = stmt.executeQuery(query);
        List<History> historyList= new ArrayList<History>();
        while (res.next()) {
            History history= new History();
            history.setId(res.getLong(1));
            history.setPatientId(res.getLong(2));
            history.setAge(res.getInt(3));
            history.setSymptoms(res.getString(4));
            history.setDiagnosis(res.getString(5));
            history.setPrescriptions(res.getString(6));
            history.setTreatedAt(res.getString(7));
            history.setTreatedBy(res.getString(8));
            history.setTreatmentDate(res.getString(9));
            historyList.add(history);
        }
        Collections.sort(historyList, new Comparator<History>() {
            public int compare(History o1, History o2)  {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                Date d1 = null;
                Date d2 = null;
                try {
                    d1 = dateFormat.parse(o1.treatmentDate);
                    d2 = dateFormat.parse(o2.treatmentDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Timestamp t1 = new java.sql.Timestamp(d1.getTime());
                Timestamp t2 = new java.sql.Timestamp(d2.getTime());
                return t2.compareTo(t1);
            }
        });
    return historyList;
    }

    public void insert(History history) throws SQLException {

        Date date= new Date();
        String query="INSERT INTO patient_history(id, patient_id, age, symptoms, diagnosis, prescriptions, treated_at, treated_by, treatment_date, filepath) " +
                "VALUES("+patientDao.getMaxId()+", "+history.getPatientId()+", "+history.getAge()+",\'"+ history.getSymptoms()+"\',\'"+history.getDiagnosis()+"\', '',\'"+history.getTreatedAt()+"\', \'"+history.getTreatedBy()+"\', \'"+new Timestamp(date.getTime())+"\', NULL)";

        System.out.println(query);
        stmt.executeUpdate(query);

        patientDao.updateRecord(history.getPatientId());

    }

}
