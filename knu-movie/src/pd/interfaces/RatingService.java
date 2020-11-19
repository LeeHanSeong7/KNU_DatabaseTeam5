package pd.interfaces;

import pd.utils.Result;

public interface RatingService {
    Result getMyRatingList();
    Result getAllUsersRatingList();
    Result getAverageRating();
}
