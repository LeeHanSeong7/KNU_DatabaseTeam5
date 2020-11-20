package pd.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import pd.interfaces.MovieService;
import pd.utils.Result;
import pd.utils.Error;

public class DefaultMovieService implements MovieService{
    private Connection connection;

    public DefaultMovieService() {
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    //영상물에 보여줘야 하는 데이터를 나름대로 생각해서 출려함. 그런데 version이 너무 많아서 일단 KR로 고정하였음
    @Override
    public Result viewWholeVideo(){
        String sql = "SELECT V.title, V.region, M.runtime, M.start_year, M.total_rating, M.num_votes " +
        "FROM MOVIE M join VERSION V on M.TITLE_ID = V.MOVIE_Title_id " +
        "WHERE V.Region = 'KR'";
        try {
            PreparedStatement ppst = connection.prepareStatement(sql);
            ResultSet rs = ppst.executeQuery();
            
            while(rs.next()) {
				String title = rs.getString(1);
				String region = rs.getString(2);
                String runtime = rs.getString(3);
                String startYear = rs.getString(4);
                int total  = rs.getInt(5);
                String numVotes = rs.getString(6);
                int num = rs.getInt(6);

                String avgRating;
                if(num == 0){
                    avgRating = "0";
                }else{
                    double avg = ((double)total/num);
                    avgRating = String.valueOf(avg);
                }
	
				System.out.println("title: " + title
                                +  ", region: " + region
                                +  ", runtime: " + runtime
                                +  ", startYear: " + startYear
                                +  ", avgRating: " + avgRating
								+  ", numVotes: " + numVotes);		
			}
            rs.close();
            return Result.success;
        } catch (Exception e)  {
            e.printStackTrace();
        } 
        return Result.withError(MovieError.unknown);
    }
    
    @Override
    public Result searchMoiveByCondition(String movieName, Date Maxyear, Date Minyear, double Maxaver, double Minaver, String genre, String actor, String type){
        return Result.success;
    }

    public enum MovieError implements Error {
        
        unknown(400, "Unknown Error!");

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

        private MovieError(int code, String description) {
            this.code = code;
            this.description = description;
        }

    }
}
