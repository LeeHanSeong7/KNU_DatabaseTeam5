package knu.movie.app.injected;

import knu.movie.app.pd.interfaces.AuthenticationService;
import knu.movie.app.pd.interfaces.MovieService;
import knu.movie.app.pd.interfaces.RatingService;
import knu.movie.app.pd.stub.StubAuthenticationService;
import knu.movie.app.pd.stub.StubMovieService;
import knu.movie.app.pd.stub.StubRatingService;

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
