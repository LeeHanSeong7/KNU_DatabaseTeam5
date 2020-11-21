import config.AppConfig;
import config.DBConfig;
import injected.DIContainer;
import injected.DIContainer.Services;
import pd.services.DefaultAuthenticationService;
import pd.services.DefaultMovieService;
import pd.services.DefaultRatingService;

public class AppEnvironment {
    DIContainer container;
    DBConfig DBconfig;
    AppConfig appConfig;
    
    private AppEnvironment(DIContainer container, DBConfig dbConfig, AppConfig appConfig) {
        this.container = container;
        this.DBconfig = dbConfig;
        this.appConfig = appConfig;
    }

    static AppEnvironment bootstrap() {
        DBConfig dbConfig = new DBConfig();
        AppConfig appConfig = new AppConfig();
        Services services = configuredServicies(appConfig);
        DIContainer diContainer = new DIContainer(services);
        return new AppEnvironment(diContainer, dbConfig, appConfig);
    }

    private static Services configuredServicies(AppConfig appConfig) {
        return new DIContainer.Services(new DefaultAuthenticationService(), new DefaultMovieService(), new DefaultRatingService(appConfig));
    }
}