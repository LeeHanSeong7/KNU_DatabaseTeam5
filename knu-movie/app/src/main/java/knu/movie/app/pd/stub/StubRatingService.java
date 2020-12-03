package knu.movie.app.pd.stub;

import java.sql.Connection;
import java.util.ArrayList;

import knu.movie.app.pd.interfaces.RatingService;
import knu.movie.app.pd.utils.Result;

public class StubRatingService implements RatingService {

    @Override
    public Result getUserRatingListWith(String movieName, String email, Double maxStars, Double minStars) {
        // TODO Auto-generated method stub
        return Result.withValue((Object)(new ArrayList<>()));
    }

    @Override
    public void setConnection(Connection connection) {
        // TODO Auto-generated method stub

    }

    @Override
    public Result getMyRatingList(String id, String password) {
        // TODO Auto-generated method stub
        return null;
    }

    
}
