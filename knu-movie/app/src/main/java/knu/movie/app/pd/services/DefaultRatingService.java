package knu.movie.app.pd.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import knu.movie.app.config.AppConfig;
import knu.movie.app.pd.interfaces.AuthenticationService;
import knu.movie.app.pd.interfaces.RatingService;
import knu.movie.app.pd.model.MyRatingVO;
import knu.movie.app.pd.model.RatingVOList;
import knu.movie.app.pd.utils.Error;
import knu.movie.app.pd.utils.Result;

public class DefaultRatingService implements RatingService {
    private AppConfig appConfig;
    private Connection connection;
    private AuthenticationService authService;

    public DefaultRatingService(AppConfig appConfig, AuthenticationService authenticationService) {
        this.appConfig = appConfig;
        this.authService = authenticationService;
    }

    @Override
    public Result getMyRatingList(String id, String password) {
        String region = appConfig.REGION;
        String sql = "SELECT v.title, r.stars FROM rating r join version v on v.movie_title_id=r.movie_title_id WHERE v.region='"+region+"' " + 
                    "AND ACCOUNT_Email_id='" +
                     authService.getloggedInAccountInfo(id, password).getEmail_id() + "'" + 
                    "ORDER BY v.title,  v.MOVIE_TITLE_ID";
        //System.out.println(sql);
        try {
            PreparedStatement ppst = connection.prepareStatement(sql);
            ResultSet rs = ppst.executeQuery();
            if (rs.isBeforeFirst()) {
                List<MyRatingVO> list = MyRatingVO.getListFromResultSet(rs);
                return Result.withValue(list);
            } else {
                return Result.withError(RatingError.noResultWithGivenCondition);
            }
        } catch (Exception e) {
            return Result.withError(RatingError.connectionError);
        }
    }        

    @Override
    public Result getUserRatingListWith(String movieName, String email, Double maxStars, Double minStars) {
        String region = appConfig.REGION;
    
        String sql = "SELECT v.MOVIE_TITLE_ID, v.title, r.account_email_id, r.stars FROM rating r join version v on v.movie_title_id=r.movie_title_id WHERE v.region='"+region+"' " + 
                    (movieName==null? "":("AND title='" + movieName + "' ")) + 
                    (email==null? "":("AND ACCOUNT_Email_id='" + email + "' ")) + 
                    (maxStars==null? "":("AND Stars <= " + maxStars + " ")) + 
                    (minStars==null? "":("AND Stars >= " + minStars + " ")) +
                    "ORDER BY v.title,  v.MOVIE_TITLE_ID";
        //System.out.println(sql);
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

    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;
    }


    public enum RatingError implements Error {
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
