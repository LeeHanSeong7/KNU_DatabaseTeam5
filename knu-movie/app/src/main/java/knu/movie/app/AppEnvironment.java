package knu.movie.app;

import knu.movie.app.config.AppConfig;
import knu.movie.app.config.DBConfig;
import knu.movie.app.injected.DIContainer;
import knu.movie.app.injected.DIContainer.Services;
import knu.movie.app.pd.interfaces.AuthenticationService;
import knu.movie.app.pd.interfaces.RatingService;
import knu.movie.app.pd.services.DefaultAuthenticationService;
import knu.movie.app.pd.services.DefaultMovieService;
import knu.movie.app.pd.services.DefaultRatingService;

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
        AuthenticationService authService = new DefaultAuthenticationService();
        RatingService ratingService = new DefaultRatingService(appConfig, authService);
        return new DIContainer.Services(authService, new DefaultMovieService(appConfig, authService, ratingService), ratingService);
    }
}