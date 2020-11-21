package pd.interfaces;

import java.sql.Connection;
import pd.utils.Result;

public interface RatingService {
    Result getMyRatingList();
    Result getUserRatingListWith(String movieName, String email, Double maxStars, Double minStars);
    void setConnection(Connection connection);
}
