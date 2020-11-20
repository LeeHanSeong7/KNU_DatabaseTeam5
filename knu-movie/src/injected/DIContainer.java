package injected;

import pd.interfaces.AuthenticationService;
import pd.interfaces.MovieService;
import pd.stub.StubAuthenticationService;
import pd.stub.StubMovieService;

public class DIContainer {
    public static final DIContainer test = new DIContainer(Services.stub);
    public Services services;
    public DIContainer(Services services) {
        this.services = services;
    }

    public static class Services {
        public AuthenticationService authenticationService;
        public MovieService movieService;

        public Services(AuthenticationService authenticationService, MovieService movieService) {
            this.authenticationService = authenticationService;
            this.movieService = movieService;
        }

        public static final Services stub = new Services(new StubAuthenticationService(), new StubMovieService());
    }
}
