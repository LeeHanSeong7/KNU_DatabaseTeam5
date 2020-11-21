package injected;

import pd.interfaces.AuthenticationService;
import pd.interfaces.MovieService;
import pd.interfaces.RatingService;
import pd.stub.StubAuthenticationService;
import pd.stub.StubMovieService;
import pd.stub.StubRatingService;

public class DIContainer {
    public static final DIContainer test = new DIContainer(Services.stub);
    public Services services;
    public DIContainer(Services services) {
        this.services = services;
    }

    public static class Services {
        public AuthenticationService authenticationService;
        public MovieService movieService;
        public RatingService ratingService;

        public Services(AuthenticationService authenticationService, MovieService movieService, RatingService ratingService) {
            this.authenticationService = authenticationService;
            this.movieService = movieService;
            this.ratingService = ratingService;
        }

        public static final Services stub = new Services(new StubAuthenticationService(), new StubMovieService(), new StubRatingService());
    }
}
