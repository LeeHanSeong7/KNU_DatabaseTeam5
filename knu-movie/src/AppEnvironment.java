import injected.DIContainer;
import injected.DIContainer.Services;
import pd.services.DefaultAuthenticationService;

public class AppEnvironment {
    DIContainer container;

    private AppEnvironment(DIContainer container) {
        this.container = container;
    }

    static AppEnvironment bootstrap() {
        Services services = configuredServicies();
        DIContainer diContainer = new DIContainer(services);
        return new AppEnvironment(diContainer);
    }

    private static Services configuredServicies() {
        return new DIContainer.Services(new DefaultAuthenticationService());
    }
}