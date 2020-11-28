package knu.movie.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import knu.movie.app.config.DBConfig;
import knu.movie.app.injected.DIContainer;
import knu.movie.app.pd.model.AccountDTO;
import knu.movie.app.pd.utils.Result;


@SpringBootApplication
@RestController
public class AppApplication {
	static DIContainer container;
	static DIContainer.Services services;
	static DBConfig dbconfig;
	public static void main(String[] args) {
		AppEnvironment appEnvironment = AppEnvironment.bootstrap();
		container = appEnvironment.container;
		dbconfig = appEnvironment.DBconfig;
		services = container.services;
		services.authenticationService.setConnection(dbconfig.connection);
		services.movieService.setConnection(dbconfig.connection);
		services.ratingService.setConnection(dbconfig.connection);
		SpringApplication.run(AppApplication.class, args);
	}

	@GetMapping("/login")
	public String login(
		@RequestParam(value="id", defaultValue = "") String id,
		@RequestParam(value="password", defaultValue = "") String password
	) {
		Result result = services.authenticationService.login(id, password);
		if (result == Result.failure) return result.getError().getDescription();
		else {
			AccountDTO accountInfo = services.authenticationService.getloggedInAccountInfo();
			if (accountInfo.getIsAdmin()) return "admin";
			else return "user";
		}
	}

	//@PostMapping("/")

}
