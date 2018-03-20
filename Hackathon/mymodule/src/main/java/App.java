import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceFilter;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import resources.FileResouce;
import resources.HistoryResource;
import resources.PatientResource;

/**
 * Created by sandeep.roy on 11/03/18.
 */
public class App extends Application<Config> {

    private Bootstrap<Config> bootstrap;
    public static void main(String[] args) throws Exception {
        new App().run(args);
    }


    @Override
    public void run(Config configuration, Environment environment) throws Exception {
        Injector injector= Guice.createInjector(new InitProvider(configuration));
    //    TestRsource rsource= new TestRsource();
        environment.servlets().addFilter("guice filter", GuiceFilter.class).addMappingForUrlPatterns(null, false, "/*");
        environment.jersey().register(injector.getInstance(PatientResource.class));
        environment.jersey().register(injector.getInstance(HistoryResource.class));
        environment.jersey().register(injector.getInstance(FileResouce.class));

    }
}
