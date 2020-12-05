package knu.movie.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import knu.movie.app.config.DBConfig;
import knu.movie.app.injected.DIContainer;
import knu.movie.app.pd.model.AccountDTO;
import knu.movie.app.pd.model.MovieDTO;
import knu.movie.app.pd.model.MovieSearchConditionDTO;
import knu.movie.app.pd.utils.Result;

@CrossOrigin(origins = "http://localhost:8080")
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
	
	//@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping("/login")
	public String loginGet(
		@RequestParam(value="id", defaultValue = "") String id,
		@RequestParam(value="password", defaultValue = "") String password
	) {
		Result result = services.authenticationService.login(id, password);
		if (result == Result.failure) return result.getError().getDescription();
		else {
			AccountDTO accountInfo = services.authenticationService.getloggedInAccountInfo(id, password);
			if (accountInfo.getIsAdmin()) return "admin";
			else return "user";
		}
	}

	@GetMapping("/logout")
	public ResponseEntity<Result> logout(
		@RequestParam(value="id", defaultValue = "") String id,
		@RequestParam(value="password", defaultValue = "") String password
	) {
		Result result = services.authenticationService.logout(id, password);
		if (result == Result.success) return new ResponseEntity<Result>(result, HttpStatus.OK);
		else return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/signup")
	public ResponseEntity<Result> signUp(
		@RequestBody AccountDTO newbee
	) {
		Result result = services.authenticationService.signUp(newbee.getEmail_id(), newbee.getPassword(), newbee);
		if (result == Result.success) return new ResponseEntity<Result>(result, HttpStatus.OK);
		else return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/user/search")
	public ResponseEntity<Result> uSearch(
		@RequestParam(value="id", defaultValue = "") String id,
		@RequestParam(value="password", defaultValue = "") String password,
		@RequestBody MovieSearchConditionDTO condition
	) {
		Result result = services.movieService.searchMoiveByCondition(id, password, condition);
		if (result == Result.success) return new ResponseEntity<Result>(result, HttpStatus.OK);
		else return new ResponseEntity<Result>(result, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/user/my-ratings")
	public ResponseEntity<Result> myRatings(
		@RequestParam(value="id", defaultValue = "") String id,
		@RequestParam(value="password", defaultValue = "") String password
	) {
		Result result = services.ratingService.getMyRatingList(id, password);
		if (result == Result.success) return new ResponseEntity<Result>(result, HttpStatus.OK);
		else return new ResponseEntity<Result>(result, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/user/account/my-info")
	public ResponseEntity<AccountDTO> myInfo(
		@RequestParam(value="id", defaultValue = "") String id,
		@RequestParam(value="password", defaultValue = "") String password
	) {
		AccountDTO user = services.authenticationService.getloggedInAccountInfo(id, password);
		if (user != null) return new ResponseEntity<AccountDTO>(user, HttpStatus.OK);
		else return new ResponseEntity<AccountDTO>(user, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/user/account/change-password")
	public ResponseEntity<Result> changePassword(
		@RequestParam(value="id", defaultValue = "") String id,
		@RequestParam(value="password", defaultValue = "") String password,
		@RequestParam(value="changed", defaultValue = "") String changed
	) {
		Result result = services.authenticationService.changePassword(id, password, changed);
		if (result == Result.success) return new ResponseEntity<Result>(result, HttpStatus.OK);
		else return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/user/account/modify-info")
	public ResponseEntity<Result> modifyInfo(
		@RequestParam(value="id", defaultValue = "") String id,
		@RequestParam(value="password", defaultValue = "") String password,
		@RequestBody AccountDTO changed
	) {
		Result result = services.authenticationService.changeAccountInfo(id, password, changed);
		if (result == Result.success) return new ResponseEntity<Result>(result, HttpStatus.OK);
		else return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/user/account/delete")
	public ResponseEntity<Result> deleteAccount(
		@RequestParam(value="id") String id,
		@RequestParam(value="password") String password,
		@RequestParam(value="re-password") String rePassword
	) {
		Result result = services.authenticationService.deleteAccount(id, password, rePassword);
		if (result == Result.success) return new ResponseEntity<Result>(result, HttpStatus.OK);
		else return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/admin/check-ratings")
	public ResponseEntity<Result> checkRating(
		@RequestParam(value="id") String id,
		@RequestParam(value="password") String password,
		@RequestParam(value="movie-name", required=false) String movieName,
		@RequestParam(value="email", required=false) String email,
		@RequestParam(value="max-stars", required=false) Double maxStars,
		@RequestParam(value="min-stars", required=false) Double minStars
	) {
		Result result = services.ratingService.getUserRatingListWith(movieName, email, maxStars, minStars);
		if (result == Result.success) return new ResponseEntity<Result>(result, HttpStatus.OK);
		else return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/admin/search-movie")
	public ResponseEntity<Result> modifyInfo(
		@RequestParam(value="id", defaultValue = "") String id,
		@RequestParam(value="password", defaultValue = "") String password,
		@RequestBody MovieSearchConditionDTO condition
	) {
		Result result = services.movieService.searchMoiveByCondition(id, password, condition);
		if (result == Result.success) return new ResponseEntity<Result>(result, HttpStatus.OK);
		else return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/admin/upload-movie")
	public ResponseEntity<Result> uploadMovie(
		@RequestParam(value="id") String id,
		@RequestParam(value="password") String password,
		@RequestBody MovieDTO movie
	) {
		Result result = services.movieService.movieUpload(movie);
		if (result == Result.success) return new ResponseEntity<Result>(result, HttpStatus.OK);
		else return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
	}

}
