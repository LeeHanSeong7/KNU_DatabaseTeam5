package pd.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import config.AppConfig;
import pd.interfaces.RatingService;
import pd.model.RatingVOList;
import pd.utils.Result;
import pd.utils.Error;

public class DefaultRatingService implements RatingService {
    private AppConfig appConfig;
    private Connection connection;

    public DefaultRatingService(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @Override
    public Result getMyRatingList() {
        return null;
    }

    // MOVIE_Title_id   VARCHAR2 (20 CHAR)  NOT NULL , 
    // ACCOUNT_Email_id VARCHAR2 (50 CHAR)  NOT NULL , 
    // Stars          

    @Override
    public Result getUserRatingListWith(String movieName, String email, Double maxStars, Double minStars) {
        String region = appConfig.REGION;
    
        String sql = "SELECT v.MOVIE_TITLE_ID, v.title, r.account_email_id, r.stars FROM rating r join version v on v.movie_title_id=r.movie_title_id WHERE v.region='"+region+"' " + 
                    (movieName==null? "":("AND title='" + movieName + "' ")) + 
                    (email==null? "":("AND ACCOUNT_Email_id='" + email + "' ")) + 
                    (maxStars==null? "":("AND Stars <= " + maxStars + " ")) + 
                    (minStars==null? "":("AND Stars >= " + minStars + " ")) +
                    "ORDER BY v.title,  v.MOVIE_TITLE_ID";
        System.out.println(sql);
        try {
            PreparedStatement ppst = connection.prepareStatement(sql);
            ResultSet rs = ppst.executeQuery();
            if (rs.isBeforeFirst()) {
                List<RatingVOList> lists = RatingVOList.getRatingListsFromResultSet(rs);
                return Result.withValue(lists);
            } else {
                return Result.withError(RatingError.noResultWithGivenCondition);
            }
        } catch (Exception e) {
            return Result.withError(RatingError.connectionError);
        }
        
    }

    private String getMovieID(String movieName) {
        return "";
    }

    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;
    }


    private enum RatingError implements Error {
        movieNotFound(1, "Movie is not in DB. "),
        noResultWithGivenCondition(2, "Can't find Results with Given Condition."),
        connectionError(1004, "Connection Error. ");

        private int code;
        private String description;

        @Override
        public String getDescription() {
            return this.description;
        }

        @Override
        public int getCode() {
            return this.code;
        }

        private RatingError(int code, String description) {
            this.code = code;
            this.description = description;
        }

    }

    
    
}
