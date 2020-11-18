package injected;

import pd.interfaces.AuthenticationService;
import pd.stub.StubAuthenticationService;

public class DIContainer {
    public static final DIContainer test = new DIContainer(Services.stub);
    public Services services;
    public DIContainer(Services services) {
        this.services = services;
    }

    public static class Services {
        public AuthenticationService authenticationService;

        public Services(AuthenticationService authenticationService) {
            this.authenticationService = authenticationService;
        }

        public static final Services stub = new Services(new StubAuthenticationService());
    }
}
