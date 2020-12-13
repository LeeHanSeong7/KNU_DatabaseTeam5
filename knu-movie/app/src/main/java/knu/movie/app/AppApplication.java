package knu.movie.app;

import java.util.HashMap;
import java.util.List;

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
import knu.movie.app.pd.model.MyRatingVO;
import knu.movie.app.pd.model.RatingVOList;
import knu.movie.app.pd.utils.Result;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001", "http://localhost:3002" })
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
		@RequestParam(value="id") String id,
		@RequestParam(value="password") String password
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
	public ResponseEntity<String> logout(
		@RequestParam(value="id") String id,
		@RequestParam(value="password") String password
	) {
		Result result = services.authenticationService.logout(id, password);
		if (result == Result.success) return new ResponseEntity<String>(result.toString(), HttpStatus.OK);
		else return new ResponseEntity<String>(result.getError().getDescription(), HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/signup")
	public ResponseEntity<String> signUp(
		@RequestBody AccountDTO newbee
	) {
		Result result = services.authenticationService.signUp(newbee.getEmail_id(), newbee.getPassword(), newbee);
		if (result == Result.success) return new ResponseEntity<String>(result.toString(), HttpStatus.OK);
		else return new ResponseEntity<String>(result.getError().getDescription(), HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/search-movie")
	public ResponseEntity<HashMap<String, MovieDTO>> uSearchMovie(
		@RequestParam(value="id") String id,
		@RequestParam(value="password") String password,
		@RequestBody MovieSearchConditionDTO condition
	) {
		Result result = services.movieService.searchMoiveByCondition(id, password, condition);
		if (result == Result.success) return new ResponseEntity<HashMap<String, MovieDTO>>((HashMap<String, MovieDTO>)result.getValue(), HttpStatus.OK);
		else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/recommand-movie")
	public ResponseEntity<HashMap<String, MovieDTO>> recommandMovie(
		@RequestParam(value="id") String id,
		@RequestParam(value="password") String password
	) {
		Result result = services.movieService.recommandMovie(id, password);
		if (result == Result.success) return new ResponseEntity<HashMap<String, MovieDTO>>((HashMap<String, MovieDTO>)result.getValue(), HttpStatus.OK);
		else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/user/my-ratings")
	public ResponseEntity<List<MyRatingVO>> myRatings(
		@RequestParam(value="id") String id,
		@RequestParam(value="password") String password
	) {
		Result result = services.ratingService.getMyRatingList(id, password);
		if (result == Result.success) return new ResponseEntity<List<MyRatingVO>>((List<MyRatingVO>)result.getValue(), HttpStatus.OK);
		else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/user/account/my-info")
	public ResponseEntity<AccountDTO> myInfo(
		@RequestParam(value="id") String id,
		@RequestParam(value="password") String password
	) {
		AccountDTO user = services.authenticationService.getloggedInAccountInfo(id, password);
		if (user != null) return new ResponseEntity<AccountDTO>(user, HttpStatus.OK);
		else return new ResponseEntity<AccountDTO>(user, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/user/account/change-password")
	public ResponseEntity<String> changePassword(
		@RequestParam(value="id") String id,
		@RequestParam(value="password") String password,
		@RequestParam(value="changed") String changed
	) {
		Result result = services.authenticationService.changePassword(id, password, changed);
		if (result == Result.success) return new ResponseEntity<String>(result.toString(), HttpStatus.OK);
		else return new ResponseEntity<String>(result.getError().getDescription(), HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/user/account/modify-info")
	public ResponseEntity<String> modifyInfo(
		@RequestParam(value="id") String id,
		@RequestParam(value="password") String password,
		@RequestBody AccountDTO changed
	) {
		Result result = services.authenticationService.changeAccountInfo(id, password, changed);
		if (result == Result.success) return new ResponseEntity<String>(result.toString(), HttpStatus.OK);
		else return new ResponseEntity<String>(result.getError().getDescription(), HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/user/account/delete")
	public ResponseEntity<String> deleteAccount(
		@RequestParam(value="id") String id,
		@RequestParam(value="password") String password,
		@RequestParam(value="re-password") String rePassword
	) {
		Result result = services.authenticationService.deleteAccount(id, password, rePassword);
		if (result == Result.success) return new ResponseEntity<String>(result.toString(), HttpStatus.OK);
		else return new ResponseEntity<String>(result.getError().getDescription(), HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/admin/check-ratings")
	public ResponseEntity<List<RatingVOList>> checkRating(
		@RequestParam(value="id") String id,
		@RequestParam(value="password") String password,
		@RequestParam(value="movie-name", required=false) String movieName,
		@RequestParam(value="email", required=false) String email,
		@RequestParam(value="max-stars", required=false) Double maxStars,
		@RequestParam(value="min-stars", required=false) Double minStars
	) {
		Result result = services.ratingService.getUserRatingListWith(movieName, email, maxStars, minStars);
		if (result == Result.success) return new ResponseEntity<List<RatingVOList>>((List<RatingVOList>)result.getValue(), HttpStatus.OK);
		else return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/admin/upload-movie")
	public ResponseEntity<String> uploadMovie(
		@RequestParam(value="id") String id,
		@RequestParam(value="password") String password,
		@RequestBody MovieDTO movie
	) {
		Result result = services.movieService.movieUpload(movie);
		if (result == Result.success) return new ResponseEntity<String>(result.toString(), HttpStatus.OK);
		else return new ResponseEntity<String>(result.getError().getDescription(), HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/admin/delete-movie")
	public ResponseEntity<String> deleteMovie(
		@RequestParam(value="id") String id,
		@RequestParam(value="password") String password,
		@RequestParam(value="title_id") String titleId
	) {
		Result result = services.movieService.movieDelete(titleId);
		if (result == Result.success) return new ResponseEntity<String>(result.toString(), HttpStatus.OK);
		else return new ResponseEntity<String>(result.getError().getDescription(), HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/admin/update-movie")
	public ResponseEntity<String> updateMovie(
		@RequestParam(value="id") String id,
		@RequestParam(value="password") String password,
		@RequestBody List<MovieDTO> list
	) {
		Result result = services.movieService.updateMovie(list.get(0), list.get(1));
		if (result == Result.success) return new ResponseEntity<String>(result.toString(), HttpStatus.OK);
		else return new ResponseEntity<String>(result.getError().getDescription(), HttpStatus.BAD_REQUEST);
	}


	@PostMapping("/user/rate-movie")
	public ResponseEntity<String> rateMovie(
		@RequestParam(value="id") String id,
		@RequestParam(value="password") String password,
		@RequestParam(value="stars") Double stars,
		@RequestBody MovieSearchConditionDTO condition
	) {
		Result result = services.movieService.rateMovie(id, password, condition, stars);
		if (result == Result.success) return new ResponseEntity<String>(result.toString(), HttpStatus.OK);
		else return new ResponseEntity<String>(result.getError().getDescription(), HttpStatus.BAD_REQUEST);
	}

}
