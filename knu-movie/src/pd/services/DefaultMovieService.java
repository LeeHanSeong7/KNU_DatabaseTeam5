package pd.services;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import config.AppConfig;
import pd.interfaces.AuthenticationService;
import pd.interfaces.MovieService;
import pd.model.MovieDTO;
import pd.model.MovieSearchConditionDTO;
import pd.utils.Result;
import pd.utils.Error;

public class DefaultMovieService implements MovieService{
    private Connection connection;
    private ArrayList<MovieDTO> movieDTOList = new ArrayList<MovieDTO>();
    String region;
    private AuthenticationService authService;

    public DefaultMovieService(AppConfig appConfig, AuthenticationService authenticationService) {
        this.region = appConfig.REGION;
        this.authService = authenticationService;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setRegion(){
        String resource = "app.properties";
        Properties properties = new Properties();

        try {
            InputStream is = getClass().getResourceAsStream(resource);
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.region = properties.getProperty("region");
    }
    //영상물에 보여줘야 하는 데이터를 나름대로 생각해서 출려함. 그런데 version이 너무 많아서 일단 KR로 고정하였음
    @Override
    public Result viewWholeVideo(){
        String sql = "SELECT M.TITLE_ID, V.title, V.region, M.runtime, M.start_year, M.total_rating, M.num_votes, G.GENRE_Genre_id, M.type "+
        "FROM (MOVIE M join VERSION V on M.TITLE_ID = V.MOVIE_Title_id) join MOVIE_HAS_GENRE G on M.TITLE_ID = G.MOVIE_Title_id " +
        "WHERE V.Region = 'KR'";

        try {
            PreparedStatement ppst = connection.prepareStatement(sql);
            ResultSet rs = ppst.executeQuery();
            ArrayList<String> genreList = new ArrayList<String>();

            while(rs.next()) {
                String title_id = rs.getString(1);
				String title = rs.getString(2);
				String region = rs.getString(3);
                String runtime = rs.getString(4);
                String startYear = rs.getString(5);
                int total  = rs.getInt(6);
                String numVotes = rs.getString(7);
                int num = rs.getInt(7);
                String genre = rs.getString(8);
                String type = rs.getString(9);
                double avg = 0;
                String avgRating = "0";
                MovieDTO movieDTO;
                ArrayList<String> actorList = new ArrayList<String>();
                genreList.add(genre);

                if(num == 0){
                    avgRating = "0";
                }else{
                    avg = ((double)total/num);
                    avgRating = String.valueOf(avg);
                }

                if(movieDTOList.size() == 0){
                    movieDTO = new MovieDTO(title_id, title, region, runtime, startYear, total, numVotes, num, avg, genreList, type ,actorList);
                    movieDTOList.add(movieDTO);
                    System.out.println("moviDTO " + movieDTO);
                }else if(movieDTOList.size() > 0){
                    // 앞의 movieDTO의 title_id와 같으면 genre 리스트에 추가하여 set
                    if(movieDTOList.get(movieDTOList.size() - 1).getTitleId().equals(title_id) == true){
                        movieDTOList.remove(movieDTOList.size() - 1);
                        movieDTO = new MovieDTO(title_id, title, region, runtime, startYear, total, numVotes, num, avg, genreList, type ,actorList);
                        movieDTOList.add(movieDTO);
                    }
                    else{
                        //다르면 genreList 초기화 하고 다시 추가하여 ArrayList에 추가
                        genreList = new ArrayList<String>();
                        genreList.add(genre);
                        movieDTO = new MovieDTO(title_id, title, region, runtime, startYear, total, numVotes, num, avg, genreList, type ,actorList);
                        movieDTOList.add(movieDTO);
                    }
                }
                // System.out.println(movieDTOList.get(movieDTOList.size() - 1).getGenreList());
                //System.out.println(genreList);
				// System.out.println("title: " + title
                //                 +  ", region: " + region
                //                 +  ", runtime: " + runtime
                //                 +  ", startYear: " + startYear
                //                 +  ", avgRating: " + avgRating
				// 				+  ", numVotes: " + numVotes);		
            }
           
            for(int i=0;i<movieDTOList.size();i++){
                MovieDTO item = movieDTOList.get(i);
                System.out.print("title: " + item.getTitle()
                                +  ", type: " + item.getType()
                                +  ", region: " + item.getRegion()
                                +  ", runtime: " + item.getRuntime()
                                +  ", startYear: " + item.getStartYear()
                                +  ", avgRating: " + String.valueOf(item.getAvg())
                                +  ", numVotes: " + item.getNumVotes());
                System.out.println(", genre: " + item.getGenreList());
            }

            rs.close();
            
            return Result.withValue(movieDTOList);
        } catch (Exception e)  {
            e.printStackTrace();
        } 
        return Result.withError(MovieError.unknown);
    }
    
    @Override
    public Result searchMoiveByCondition(MovieSearchConditionDTO condition){

        String sql = "with exceptRating AS( "+
            "SELECT * " +
            "FROM MOVIE " +
            "MINUS "+
            "(SELECT M.title_id, M.Type, M.runTime, M.start_year, M.total_rating, M.Num_votes " +
            "FROM rating r join Movie M on M.title_id = r.MOVIE_Title_id  " +
            "WHERE ACCOUNT_Email_id='"+authService.getloggedInAccountInfo().getEmail_id()+"' ) " +
            ")";

        SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd");

        if(condition.Minaver == 0){
            sql += ", rated AS( " +
                "select * " +
                "from exceptRating " +
                "where num_votes != 0 " +
                "), afterRate AS( " +
                "(SELECT M.title_id " +
                "FROM MOVIE M " +
                "where M.num_votes = 0) " + 
                "UNION " +
                "(select title_id " +
                "from rated " +
                "where rated.total_rating/rated.num_votes <= " + String.valueOf(condition.Maxaver) + ") " +
                ")" +
                "SELECT M.TITLE_ID, V.title, V.region, M.runtime, M.start_year, M.total_rating, M.num_votes, M.type, G.GENRE_Genre_id " +
                "FROM ((afterRate A join MOVIE M on M.TITLE_ID = A.TITLE_ID) join VERSION V on M.TITLE_ID = V.MOVIE_Title_id) join MOVIE_HAS_GENRE G on M.TITLE_ID = G.MOVIE_Title_id " +
                "WHERE V.Region = '"+this.region+"' " + "AND Start_year BETWEEN TO_DATE('"+format1.format(condition.Minyear)+"', 'YYYY-MM-DD') AND TO_DATE('"+format1.format(condition.Maxyear)+"', 'YYYY-MM-DD') ";
        }else {
            sql += ", rated AS( " +
                "select * " +
                "from exceptRating " +
                "where num_votes != 0 " +
                "), afterRate AS( " +
                "select title_id " +
                "from rated " +
                "where rated.total_rating/rated.num_votes >= "+String.valueOf(condition.Minaver)+" AND rated.total_rating/rated.num_votes <= "+String.valueOf(condition.Maxaver)+" " +
                ") " +
                "SELECT M.TITLE_ID, V.title, V.region, M.runtime, M.start_year, M.total_rating, M.num_votes, M.type, G.GENRE_Genre_id " +
                "FROM ((afterRate A join MOVIE M on M.TITLE_ID = A.TITLE_ID) join VERSION V on M.TITLE_ID = V.MOVIE_Title_id) join MOVIE_HAS_GENRE G on M.TITLE_ID = G.MOVIE_Title_id" +
                "WHERE V.Region = '"+this.region+"' " + "AND Start_year BETWEEN TO_DATE('"+format1.format(condition.Minyear)+"', 'YYYY-MM-DD') AND TO_DATE('"+format1.format(condition.Maxyear)+"', 'YYYY-MM-DD') ";
        }

        if(condition.Mintime != -1){
            sql += "AND M.runtime >= " + String.valueOf(condition.Mintime) + " ";
        }
        if(condition.Maxtime != -1){
            sql += "AND M.runtime <= " + String.valueOf(condition.Maxtime) + " ";
        }
        if(!condition.movieID.equals("")){
            sql += "AND M.TITLE_ID = " + condition.movieID + " ";
        }
        if(!condition.movieName.equals("")){
            sql += "AND LOWER(V.title) LIKE LOWER('%" + condition.movieName + "%') ";
        }
        if(!condition.type.equals("")){
            String[] type = condition.type.split(", ");
            sql += "AND (LOWER(M.type) LIKE LOWER('%" + type[0] + "%') ";
            for(int i=1;i<type.length;i++){
                sql += "OR LOWER(M.type) LIKE LOWER('%" + type[i] + "%') ";
            }
            sql += ") ";
        }
    
        try {
            PreparedStatement ppst = connection.prepareStatement(sql);
            ResultSet rs = ppst.executeQuery();
            movieDTOList = new ArrayList<MovieDTO>();
            ArrayList<String> genreList = new ArrayList<String>();

            while(rs.next()) {
                String title_id = rs.getString(1);
				String title = rs.getString(2);
				String region = rs.getString(3);
                String runtime = rs.getString(4);
                String startYear = rs.getString(5);
                int total  = rs.getInt(6);
                String numVotes = rs.getString(7);
                int num = rs.getInt(7);
                String type = rs.getString(8);
                String genre = rs.getString(9);
                double avg = 0;
                String avgRating = "0";
                MovieDTO movieDTO;
                ArrayList<String> actorList = new ArrayList<String>();
                genreList.add(genre);

                if(num == 0){
                    avgRating = "0";
                }else{
                    avg = ((double)total/num);
                    avgRating = String.valueOf(avg);
                }
                
                if(movieDTOList.size() == 0){
                    movieDTO = new MovieDTO(title_id, title, region, runtime, startYear, total, numVotes, num, avg, genreList, type ,actorList);
                    movieDTOList.add(movieDTO);
                }else if(movieDTOList.size() > 0){
                    // 앞의 movieDTO의 title_id와 같으면 genre 리스트에 추가하여 set
                    if(movieDTOList.get(movieDTOList.size() - 1).getTitleId().equals(title_id) == true){
                        movieDTOList.remove(movieDTOList.size() - 1);
                        movieDTO = new MovieDTO(title_id, title, region, runtime, startYear, total, numVotes, num, avg, genreList, type ,actorList);
                        movieDTOList.add(movieDTO);
                    }
                    else{
                        //다르면 genreList 초기화 하고 다시 추가하여 ArrayList에 추가
                        genreList = new ArrayList<String>();
                        genreList.add(genre);
                        movieDTO = new MovieDTO(title_id, title, region, runtime, startYear, total, numVotes, num, avg, genreList, type ,actorList);
                        movieDTOList.add(movieDTO);
                    }
                }
                
            }     
            rs.close();

            // 이부분 내가 withValue로 반환 했으니 주석처리하거나 지우고 ui에 갔다 놔도 됨. 여기 두려면 두고.
            for(int i=0;i<movieDTOList.size();i++){
                MovieDTO item = movieDTOList.get(i);
                System.out.print("title: " + item.getTitle()
                                +  ", type: " + item.getType()
                                +  ", region: " + item.getRegion()
                                +  ", runtime: " + item.getRuntime()
                                +  ", startYear: " + item.getStartYear()
                                +  ", avgRating: " + String.valueOf(item.getAvg())
                                +  ", numVotes: " + item.getNumVotes());
                System.out.println(", genre: " + item.getGenreList());
            }

            return Result.withValue(movieDTOList);
        } catch (Exception e)  {
            e.printStackTrace();
        } 

        return Result.withError(MovieError.unknown);
    }
    // rate를 여기에 두는게 맞는진 모르겠는데 그냥 여기 둠. 그리고 2. rate 할 때 제목 받은걸로
    // condition 만들어서 search 하고 그 중에 선택 해서 여기 condition에 title_id 채워서 입력 인자로 넣어줘
    public Result rateMovie(MovieSearchConditionDTO condition, double stars)
    {
        String sql = "INSERT INTO RATING VALUES( " +
        "'"+condition.movieID+"','"+authService.getloggedInAccountInfo().getEmail_id()+"',"+String.valueOf(stars)+")";

        try {
            PreparedStatement ppst = connection.prepareStatement(sql);
            int r = ppst.executeUpdate();
            if (r != 1) return Result.withError(MovieError.unknown);
            else {
                connection.commit();
                return Result.success;
            }

        } catch (Exception e)  {
            e.printStackTrace();
        } 
        return Result.withError(MovieError.unknown);
    }

    public Result movieUpload(String title_id, String type, double runtime, Date start_year)
    {
        String sql = "INSERT INTO MOVIE VALUES('"+title_id+"', '"+type+"', "+String.valueOf((int)runtime)+", TO_DATE('"+String.valueOf(start_year.getYear())+"', 'yyyy'), 0, 0)";

        try {
            PreparedStatement ppst = connection.prepareStatement(sql);
            int r = ppst.executeUpdate();
            if (r != 1) return Result.withError(MovieError.unknown);
            else {
                connection.commit();
                return Result.success;
            }

        } catch (Exception e)  {
            e.printStackTrace();
        } 
        return Result.withError(MovieError.unknown);
    }

    public Result movieDelete(String title_id){
        String sql = "DELETE FROM MOVIE WHERE title_id='" + title_id + "'";

        try {
            PreparedStatement ppst = connection.prepareStatement(sql);
            int r = ppst.executeUpdate();
            if (r != 1) return Result.withError(MovieError.unknown);
            else {
                connection.commit();
                return Result.success;
            }

        } catch (Exception e)  {
            e.printStackTrace();
        } 
        return Result.withError(MovieError.unknown);
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
