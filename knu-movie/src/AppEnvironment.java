import config.AppConfig;
import config.DBConfig;
import injected.DIContainer;
import injected.DIContainer.Services;
import pd.services.DefaultAuthenticationService;
import pd.services.DefaultMovieService;

public class AppEnvironment {
    DIContainer container;
    DBConfig DBconfig;
    AppConfig appconfig;
    
    private AppEnvironment(DIContainer container, DBConfig dbconfig) {
        this.container = container;
        this.DBconfig = dbconfig;
    }

    static AppEnvironment bootstrap() {
        Services services = configuredServicies();
        DIContainer diContainer = new DIContainer(services);
        DBConfig dbConfig = new DBConfig();
        return new AppEnvironment(diContainer, dbConfig);
    }

    private static Services configuredServicies() {
        return new DIContainer.Services(new DefaultAuthenticationService(), new DefaultMovieService());
    }
}