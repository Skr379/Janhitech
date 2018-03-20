import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;
import io.dropwizard.db.DataSourceFactory;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by sandeep.roy on 11/03/18.
 */
@Getter
@Setter
public class Config extends Configuration {

    private DataSourceFactory databaseConfiguration= new DataSourceFactory();
    private JerseyClientConfiguration clientConfiguration= new JerseyClientConfiguration();

}
