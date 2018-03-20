import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import lombok.AllArgsConstructor;
import org.hibernate.jpa.HibernatePersistenceProvider;

import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceProvider;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by sandeep.roy on 11/03/18.
 */
@AllArgsConstructor
public class InitProvider extends AbstractModule {
    private final Config config;
    @Override
    protected void configure() {

    }

    @Provides
    @Singleton
    public EntityManagerFactory getEMF(){
        PersistenceProvider persistenceProvider=new HibernatePersistenceProvider();
        EntityManagerFactory emf=persistenceProvider.createEntityManagerFactory("test", null);
        return emf;
    }

    @Provides
    @Singleton
    public Statement getStatement() throws SQLException {
        try {
            Class.forName("org.apache.hive.jdbc.HiveDriver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(1);
        }
        Connection con = DriverManager.getConnection("jdbc:hive2://localhost:10000/default", "hive", "hive");
        Statement stmt = con.createStatement();
        return stmt;
    }
}
