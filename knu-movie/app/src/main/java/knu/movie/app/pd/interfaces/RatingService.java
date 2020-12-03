package knu.movie.app.pd.interfaces;

import java.sql.Connection;

import knu.movie.app.pd.utils.Result;

public interface RatingService {
    Result getMyRatingList(String id, String password);
    Result getUserRatingListWith(String movieName, String email, Double maxStars, Double minStars);
    void setConnection(Connection connection);
}
