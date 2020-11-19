import config.DBConfig;
import injected.DIContainer;
import injected.DIContainer.Services;
import pd.services.DefaultAuthenticationService;

public class AppEnvironment {
    DIContainer container;
    DBConfig config;

    private AppEnvironment(DIContainer container, DBConfig config) {
        this.container = container;
        this.config = config;
    }

    static AppEnvironment bootstrap() {
        Services services = configuredServicies();
        DIContainer diContainer = new DIContainer(services);
        DBConfig dbConfig = new DBConfig();
        return new AppEnvironment(diContainer, dbConfig);
    }

    private static Services configuredServicies() {
        return new DIContainer.Services(new DefaultAuthenticationService());
    }
}