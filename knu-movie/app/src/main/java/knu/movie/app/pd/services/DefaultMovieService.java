package knu.movie.app.pd.services;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.Properties;
import java.util.Set;

import knu.movie.app.config.AppConfig;
import knu.movie.app.pd.interfaces.AuthenticationService;
import knu.movie.app.pd.interfaces.MovieService;
import knu.movie.app.pd.interfaces.RatingService;
import knu.movie.app.pd.model.MovieDTO;
import knu.movie.app.pd.model.MovieGenreDTO;
import knu.movie.app.pd.model.MovieSearchConditionDTO;
import knu.movie.app.pd.model.MyRatingVO;
import knu.movie.app.pd.services.DefaultRatingService.RatingError;
import knu.movie.app.pd.utils.DB;
import knu.movie.app.pd.utils.Result;
import knu.movie.app.pd.utils.Error;

public class DefaultMovieService implements MovieService{
    private Connection connection;
    private ArrayList<MovieDTO> movieDTOList = new ArrayList<MovieDTO>();
    String region;
    private AuthenticationService authService;
    private RatingService ratingService;
    private final Lock lock = new ReentrantLock();

    public DefaultMovieService(AppConfig appConfig, AuthenticationService authenticationService, RatingService ratingService) {
        this.region = appConfig.REGION;
        this.authService = authenticationService;
        this.ratingService = ratingService;
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
                    //System.out.println("moviDTO " + movieDTO);
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
           
            // for(int i=0;i<movieDTOList.size();i++){
            //     MovieDTO item = movieDTOList.get(i);
            //     System.out.print("title: " + item.getTitle()
            //                     +  ", type: " + item.getType()
            //                     +  ", region: " + item.getRegion()
            //                     +  ", runtime: " + item.getRuntime()
            //                     +  ", startYear: " + item.getStartYear()
            //                     +  ", avgRating: " + String.valueOf(item.getAvg())
            //                     +  ", numVotes: " + item.getNumVotes());
            //     System.out.println(", genre: " + item.getGenreList());
            // }

            rs.close();
            
            return Result.withValue(movieDTOList);
        } catch (Exception e)  {
            e.printStackTrace();
        } 
        return Result.withError(MovieError.unknown);
    }

    public Result searchMoiveByCondition(String id, String password, MovieSearchConditionDTO condition)
    {
        HashMap<String, MovieDTO> hashMovies = new HashMap<String, MovieDTO>();

        String sql = "with exceptRating AS( "+
            "SELECT * " +
            "FROM MOVIE " +
            "MINUS "+
            "(SELECT M.title_id, M.Type, M.runTime, M.start_year, M.total_rating, M.Num_votes " +
            "FROM rating r join Movie M on M.title_id = r.MOVIE_Title_id  " +
            "WHERE ACCOUNT_Email_id='"+authService.getloggedInAccountInfo(id, password).getEmail_id()+"' ) " +
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
                ") "; 
        }else {
            sql += ", rated AS( " +
                "select * " +
                "from exceptRating " +
                "where num_votes != 0 " +
                "), afterRate AS( " +
                "select title_id " +
                "from rated " +
                "where rated.total_rating/rated.num_votes >= "+String.valueOf(condition.Minaver)+" AND rated.total_rating/rated.num_votes <= "+String.valueOf(condition.Maxaver)+" " +
                ") ";
        }
        
        sql +=  ", condition AS( " +
        "SELECT M.TITLE_ID, M.runtime, M.start_year, M.total_rating, M.num_votes, M.type, V.title, V.region " +
        "FROM ((afterRate A join MOVIE M on M.TITLE_ID = A.TITLE_ID) join VERSION V on M.TITLE_ID = V.MOVIE_Title_id) " +
        "WHERE M.TITLE_ID IS NOT NULL ";
        
        if(condition.Minyear != null && condition.Maxyear != null){
            sql += "AND Start_year BETWEEN TO_DATE('"+format1.format(condition.Minyear)+"', 'YYYY-MM-DD') AND TO_DATE('"+format1.format(condition.Maxyear)+"', 'YYYY-MM-DD') ";
        }else if(condition.Minyear != null && condition.Maxyear == null){
            sql += "AND Start_year >= TO_DATE('"+format1.format(condition.Minyear)+"', 'YYYY-MM-DD') ";
        }else if(condition.Minyear == null && condition.Maxyear != null){
            sql += "AND Start_year <= TO_DATE('"+format1.format(condition.Maxyear)+"', 'YYYY-MM-DD') ";
        }

        if(condition.Mintime != -1){
            sql += "AND M.runtime >= " + String.valueOf(condition.Mintime) + " ";
        }
        if(condition.Maxtime != -1){
            sql += "AND M.runtime <= " + String.valueOf(condition.Maxtime) + " ";
        }
        if(!condition.movieID.equals("")){
            sql += "AND M.TITLE_ID = '" + condition.movieID + "' ";
        }
        if(!condition.type.equals("")){
            String[] type = condition.type.split(", ");
            sql += "AND (LOWER(M.type) LIKE LOWER('" + type[0] + "%') ";
            for(int i=1;i<type.length;i++){
                sql += "OR LOWER(M.type) LIKE LOWER('" + type[i] + "%') ";
            }
            sql += ") ";
        }
        if(condition.region != null){
            sql += "AND V.Region = '"+condition.region+"' ";
        }else{
            sql += "AND V.Region = '"+this.region+"' ";
        }
        
        if(!condition.movieName.equals("")){
            sql += "AND LOWER(V.title) LIKE LOWER('%" + condition.movieName + "%') ";
        }
        sql += ") ";
        String first_sql="";
        first_sql = sql +
        "SELECT TITLE_ID, runtime, start_year, total_rating, num_votes, type, title, region " +
        "FROM condition ";

        try {
            PreparedStatement ppst = connection.prepareStatement(first_sql);
            ResultSet rs = ppst.executeQuery();

            while(rs.next()) {
                String title_id = rs.getString(1);
				String title = rs.getString(7);
				String region = rs.getString(8);
                String runtime = rs.getString(2);
                String startYear = rs.getString(3);
                int total  = rs.getInt(4);
                String numVotes = rs.getString(5);
                int num = rs.getInt(5);
                String type = rs.getString(6);
                String genre = "";
                double avg = 0;
                String avgRating = "0";
                MovieDTO movieDTO;
                ArrayList<String> genreList = new ArrayList<String>();
                ArrayList<String> actorList = new ArrayList<String>();
                
                if(num == 0){
                    avgRating = "0";
                }else{
                    avg = ((double)total/num);
                    avgRating = String.valueOf(avg);
                }
                movieDTO = new MovieDTO(title_id, title, region, runtime, startYear, total, numVotes, num, avg, genreList, type ,actorList);
                hashMovies.put(title_id, movieDTO);
            }     
            rs.close();

        } catch (Exception e)  {
            e.printStackTrace();
            return Result.withError(MovieError.unknown);
        }
        String second_sql = "";
        second_sql = sql +
        "SELECT TITLE_ID, G.GENRE_Genre_id " +
        "FROM condition M join MOVIE_HAS_GENRE G on M.TITLE_ID = G.MOVIE_Title_id";

        try {
            //System.out.println(second_sql);
            PreparedStatement ppst = connection.prepareStatement(second_sql);
            ResultSet rs = ppst.executeQuery();

            while(rs.next()) {
                String title_id = rs.getString(1);
				String genre = rs.getString(2);
                MovieDTO movieDTO;
                movieDTO = hashMovies.get(title_id);
                movieDTO.getGenreList().add(genre);
            }
            rs.close();
        } catch (Exception e)  {
            e.printStackTrace();
            return Result.withError(MovieError.unknown);
        } 

        String third_sql = "";
        third_sql = sql +
        "SELECT TITLE_ID, A.name " +
        "FROM (condition M join MOVIE_CAST_ACTOR C on M.TITLE_ID = C.MOVIE_Title_id) join ACTOR A on A.Actor_id = C.ACTOR_Actor_id ";

        try {
            //System.out.println(second_sql);
            PreparedStatement ppst = connection.prepareStatement(third_sql);
            ResultSet rs = ppst.executeQuery();

            while(rs.next()) {
                String title_id = rs.getString(1);
				String name = rs.getString(2);
                MovieDTO movieDTO;
                movieDTO = hashMovies.get(title_id);
                movieDTO.getActorList().add(name);
            }
            rs.close();
        } catch (Exception e)  {
            e.printStackTrace();
            return Result.withError(MovieError.unknown);
        }
        Iterator<Entry<String, MovieDTO>> entries = hashMovies.entrySet().iterator();
        while(entries.hasNext()){
            Entry<String, MovieDTO> entry = entries.next();
            //System.out.println("[Key]:" + entry.getKey() + " [Value]:" +  entry.getValue());
            MovieDTO item = entry.getValue();
            if(condition.genre != ""){
                if(item.getGenreList().contains(condition.genre) == false){
                    entries.remove();
                    continue;
                }
            }
            if(condition.actor != ""){
                if(item.getActorList().contains(condition.actor) == false){
                    entries.remove();
                }
            }
        }
        //List<MovieDTO> list = new ArrayList<MovieDTO>(hashMovies.values());
        //return Result.withValue(list);
        return Result.withValue(hashMovies);
    }

    public Result searchAllMovie() {
        MovieSearchConditionDTO condition = MovieSearchConditionDTO.fillWithDefault();

        HashMap<String, MovieDTO> hashMovies = new HashMap<String, MovieDTO>();

        String sql = "with exceptRating AS( "+
            "SELECT * " +
            "FROM MOVIE " +
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
                ") "; 
        }else {
            sql += ", rated AS( " +
                "select * " +
                "from exceptRating " +
                "where num_votes != 0 " +
                "), afterRate AS( " +
                "select title_id " +
                "from rated " +
                "where rated.total_rating/rated.num_votes >= "+String.valueOf(condition.Minaver)+" AND rated.total_rating/rated.num_votes <= "+String.valueOf(condition.Maxaver)+" " +
                ") ";
        }
        
        sql +=  ", condition AS( " +
        "SELECT M.TITLE_ID, M.runtime, M.start_year, M.total_rating, M.num_votes, M.type, V.title, V.region " +
        "FROM ((afterRate A join MOVIE M on M.TITLE_ID = A.TITLE_ID) join VERSION V on M.TITLE_ID = V.MOVIE_Title_id) " +
        "WHERE M.TITLE_ID IS NOT NULL ";
        
        if(condition.Minyear != null && condition.Maxyear != null){
            sql += "AND Start_year BETWEEN TO_DATE('"+format1.format(condition.Minyear)+"', 'YYYY-MM-DD') AND TO_DATE('"+format1.format(condition.Maxyear)+"', 'YYYY-MM-DD') ";
        }else if(condition.Minyear != null && condition.Maxyear == null){
            sql += "AND Start_year >= TO_DATE('"+format1.format(condition.Minyear)+"', 'YYYY-MM-DD') ";
        }else if(condition.Minyear == null && condition.Maxyear != null){
            sql += "AND Start_year <= TO_DATE('"+format1.format(condition.Maxyear)+"', 'YYYY-MM-DD') ";
        }

        if(condition.Mintime != -1){
            sql += "AND M.runtime >= " + String.valueOf(condition.Mintime) + " ";
        }
        if(condition.Maxtime != -1){
            sql += "AND M.runtime <= " + String.valueOf(condition.Maxtime) + " ";
        }
        if(!condition.movieID.equals("")){
            sql += "AND M.TITLE_ID = '" + condition.movieID + "' ";
        }
        if(!condition.type.equals("")){
            String[] type = condition.type.split(", ");
            sql += "AND (LOWER(M.type) LIKE LOWER('" + type[0] + "%') ";
            for(int i=1;i<type.length;i++){
                sql += "OR LOWER(M.type) LIKE LOWER('" + type[i] + "%') ";
            }
            sql += ") ";
        }
        if(condition.region != null){
            sql += "AND V.Region = '"+condition.region+"' ";
        }else{
            sql += "AND V.Region = '"+this.region+"' ";
        }
        
        if(!condition.movieName.equals("")){
            sql += "AND LOWER(V.title) LIKE LOWER('%" + condition.movieName + "%') ";
        }
        sql += ") ";
        String first_sql="";
        first_sql = sql +
        "SELECT TITLE_ID, runtime, start_year, total_rating, num_votes, type, title, region " +
        "FROM condition ";

        try {
            PreparedStatement ppst = connection.prepareStatement(first_sql);
            ResultSet rs = ppst.executeQuery();

            while(rs.next()) {
                String title_id = rs.getString(1);
				String title = rs.getString(7);
				String region = rs.getString(8);
                String runtime = rs.getString(2);
                String startYear = rs.getString(3);
                int total  = rs.getInt(4);
                String numVotes = rs.getString(5);
                int num = rs.getInt(5);
                String type = rs.getString(6);
                String genre = "";
                double avg = 0;
                String avgRating = "0";
                MovieDTO movieDTO;
                ArrayList<String> genreList = new ArrayList<String>();
                ArrayList<String> actorList = new ArrayList<String>();
                
                if(num == 0){
                    avgRating = "0";
                }else{
                    avg = ((double)total/num);
                    avgRating = String.valueOf(avg);
                }
                movieDTO = new MovieDTO(title_id, title, region, runtime, startYear, total, numVotes, num, avg, genreList, type ,actorList);
                hashMovies.put(title_id, movieDTO);
            }     
            rs.close();

        } catch (Exception e)  {
            e.printStackTrace();
            return Result.withError(MovieError.unknown);
        }
        String second_sql = "";
        second_sql = sql +
        "SELECT TITLE_ID, G.GENRE_Genre_id " +
        "FROM condition M join MOVIE_HAS_GENRE G on M.TITLE_ID = G.MOVIE_Title_id";

        try {
            //System.out.println(second_sql);
            PreparedStatement ppst = connection.prepareStatement(second_sql);
            ResultSet rs = ppst.executeQuery();

            while(rs.next()) {
                String title_id = rs.getString(1);
				String genre = rs.getString(2);
                MovieDTO movieDTO;
                movieDTO = hashMovies.get(title_id);
                movieDTO.getGenreList().add(genre);
            }
            rs.close();
        } catch (Exception e)  {
            e.printStackTrace();
            return Result.withError(MovieError.unknown);
        } 

        String third_sql = "";
        third_sql = sql +
        "SELECT TITLE_ID, A.name " +
        "FROM (condition M join MOVIE_CAST_ACTOR C on M.TITLE_ID = C.MOVIE_Title_id) join ACTOR A on A.Actor_id = C.ACTOR_Actor_id ";

        try {
            //System.out.println(second_sql);
            PreparedStatement ppst = connection.prepareStatement(third_sql);
            ResultSet rs = ppst.executeQuery();

            while(rs.next()) {
                String title_id = rs.getString(1);
				String name = rs.getString(2);
                MovieDTO movieDTO;
                movieDTO = hashMovies.get(title_id);
                movieDTO.getActorList().add(name);
            }
            rs.close();
        } catch (Exception e)  {
            e.printStackTrace();
            return Result.withError(MovieError.unknown);
        }
        Iterator<Entry<String, MovieDTO>> entries = hashMovies.entrySet().iterator();
        while(entries.hasNext()){
            Entry<String, MovieDTO> entry = entries.next();
            //System.out.println("[Key]:" + entry.getKey() + " [Value]:" +  entry.getValue());
            MovieDTO item = entry.getValue();
            if(condition.genre != ""){
                if(item.getGenreList().contains(condition.genre) == false){
                    entries.remove();
                    continue;
                }
            }
            if(condition.actor != ""){
                if(item.getActorList().contains(condition.actor) == false){
                    entries.remove();
                }
            }
        }
        //List<MovieDTO> list = new ArrayList<MovieDTO>(hashMovies.values());
        //return Result.withValue(list);
        return Result.withValue(hashMovies);
    }

    // rate를 여기에 두는게 맞는진 모르겠는데 그냥 여기 둠. 그리고 2. rate 할 때 제목 받은걸로
    // condition 만들어서 search 하고 그 중에 선택 해서 여기 condition에 title_id 채워서 입력 인자로 넣어줘
    public Result rateMovie(String id, String password, MovieSearchConditionDTO condition, double stars)
    {
        
        String sql = "INSERT INTO RATING VALUES( " +
        "'"+condition.movieID+"','"+authService.getloggedInAccountInfo(id, password).getEmail_id()+"',"+String.valueOf(stars)+")";

        lock.lock();
        try {
            PreparedStatement ppst = connection.prepareStatement(sql);
            int r = ppst.executeUpdate();
            if (r != 1) {
                lock.unlock();
                return Result.withError(MovieError.unknown);
            }
        } catch (Exception e)  {
            e.printStackTrace();
            lock.unlock();
        } 

        sql="UPDATE MOVIE " +
            "SET num_votes = num_votes + 1, total_rating = total_rating + "+String.valueOf(stars)+" " +
            "WHERE title_id = '"+condition.movieID+"'";
        try {
            PreparedStatement ppst = connection.prepareStatement(sql);
            int r = ppst.executeUpdate();
            if (r != 1) return Result.withError(MovieError.unknown);
            else {
                connection.commit();
                lock.unlock();
                return Result.success;
            }
        } catch (Exception e)  {
            e.printStackTrace();
            lock.unlock();
        }
        lock.unlock();
        return Result.withError(MovieError.unknown);
    }

    public Result movieUpload(MovieDTO movieDTO)
    {
        String sql = "SELECT * FROM MOVIE WHERE title_id = '"+movieDTO.getTitleId()+"'";
        try {
            PreparedStatement ppst = connection.prepareStatement(sql);
            ResultSet rs = ppst.executeQuery();
            if (rs.next()) {
                rs.close();
                return Result.withError(MovieError.title_idAlreadyExists);
            }
            rs.close();
        } catch(Exception e) {
            return Result.withError(MovieError.unknown);
        }

        sql = "INSERT INTO MOVIE VALUES('"+movieDTO.getTitleId()+"',";   
        if(movieDTO.getType()==null){
            sql+=" '"+"null"+"'";
        }else{
            sql+=" '"+movieDTO.getType()+"'";
        }

        if(movieDTO.getRuntime()==null){
            sql+=", "+"null";
        }else{
            sql+=", "+movieDTO.getRuntime();
        }

        if(movieDTO.getStartYear()==null){
            sql+=", null, 0, 0)";
        }else{
            sql+=", TO_DATE('"+String.valueOf(movieDTO.getStartYear().substring(0, 4))+"', 'yyyy'), 0, 0)";
        }

        try {
            PreparedStatement ppst = connection.prepareStatement(sql);
            int r = ppst.executeUpdate();
            if (r != 1) return Result.withError(MovieError.title_idAlreadyExists);
            else {
                connection.commit();
            }

        } catch (Exception e)  {
            e.printStackTrace();
        } 

        sql = "INSERT INTO VERSION VALUES ( '"+movieDTO.getTitleId()+ "', 1, ";

        if(movieDTO.getTitle()==null){
            sql+="null, ";
        }else{
            sql+="'"+movieDTO.getTitle()+"', ";
        }

        if(movieDTO.getRegion()==null){
            sql+="'"+this.region+"', '\\N' )";
        }else{
            sql+="'"+movieDTO.getRegion()+"', '\\N' )";
        }
        try {
            PreparedStatement ppst = connection.prepareStatement(sql);
            //System.out.println(sql);
            int r = ppst.executeUpdate();
            if (r != 1) return Result.withError(MovieError.unknown);
            else {
                connection.commit();
            }

        } catch (Exception e)  {
            e.printStackTrace();
        } 

        if(movieDTO.getGenreList() != null){
            if(movieDTO.getGenreList().size() > 0){
                for(String genre : movieDTO.getGenreList()){
                    sql = "INSERT INTO MOVIE_HAS_GENRE VALUES('"+movieDTO.getTitleId()+"', '"+genre+"')";
                    try {
                        PreparedStatement ppst = connection.prepareStatement(sql);
                        int r = ppst.executeUpdate();
                        if (r != 1) return Result.withError(MovieError.unknown);
                        else {
                            connection.commit();
                        }
            
                    } catch (Exception e)  {
                        e.printStackTrace();
                    } 
                }
            }
        }
        if(movieDTO.getActorList() != null){
            if(movieDTO.getActorList().size() > 0){
                for(String actor : movieDTO.getActorList()){
                    sql = "SELECT Actor_id FROM ACTOR WHERE name = '"+actor+"'";
                    String actor_id = "";
                    try {
                        PreparedStatement ppst = connection.prepareStatement(sql);
                        ResultSet rs = ppst.executeQuery();
                        rs.next();
                        actor_id = rs.getString(1);
            
                    } catch (Exception e)  {
                        e.printStackTrace();
                        return Result.withError(MovieError.noActors);
                    } 
    
                    sql = "INSERT INTO MOVIE_CAST_ACTOR VALUES ( '"+movieDTO.getTitleId()+"', '"+actor_id+"' )";
                    try {
                        PreparedStatement ppst = connection.prepareStatement(sql);
                        int r = ppst.executeUpdate();
                        if (r != 1) return Result.withError(MovieError.unknown);
                        else {
                            connection.commit();
                        }
            
                    } catch (Exception e)  {
                        e.printStackTrace();
                    } 
                }
            }
        }
        
        return Result.success;
    }

    public Result movieDelete(String title_id){    
        String sql = "SELECT * FROM MOVIE_CAST_ACTOR WHERE MOVIE_title_id='" + title_id + "'";
        //System.out.println(sql);
        try {
            PreparedStatement ppst = connection.prepareStatement(sql);
            ResultSet rs = ppst.executeQuery();
            if (rs.next()) {
                sql = "DELETE FROM MOVIE_CAST_ACTOR WHERE MOVIE_title_id='" + title_id + "'";
                 try {
                    ppst = connection.prepareStatement(sql);
                    int r = ppst.executeUpdate();
                    if (r < 1) return Result.withError(MovieError.unknown);
                    else {
                        connection.commit();
                    }

                } catch (Exception e)  {
                    e.printStackTrace();
                }               
            }
            rs.close();
        } catch(Exception e) {
            e.printStackTrace();
            return Result.withError(MovieError.unknown);
        }

        sql = "SELECT * FROM MOVIE_HAS_GENRE WHERE MOVIE_title_id='" + title_id + "'";
        try {
            PreparedStatement ppst = connection.prepareStatement(sql);
            ResultSet rs = ppst.executeQuery();
            if (rs.next()) {
                sql = "DELETE FROM MOVIE_HAS_GENRE WHERE MOVIE_title_id='" + title_id + "'";
                try {
                    ppst = connection.prepareStatement(sql);
                    int r = ppst.executeUpdate();
                    if (r < 1) return Result.withError(MovieError.unknown);
                    else {
                        connection.commit();
                    }

                } catch (Exception e)  {
                    e.printStackTrace();
                }           
            }
            rs.close();
        } catch(Exception e) {
            return Result.withError(MovieError.unknown);
        }

        sql = "SELECT * FROM RATING WHERE MOVIE_title_id='" + title_id + "'";
        try {
            PreparedStatement ppst = connection.prepareStatement(sql);
            ResultSet rs = ppst.executeQuery();
            if (rs.next()) {
                sql = "DELETE FROM RATING WHERE MOVIE_title_id='" + title_id + "'";

                try {
                    ppst = connection.prepareStatement(sql);
                    int r = ppst.executeUpdate();
                    if (r < 1) return Result.withError(MovieError.unknown);
                    else {
                        connection.commit();
                    }

                } catch (Exception e)  {
                    e.printStackTrace();
                }       
            }
            rs.close();
        } catch(Exception e) {
            return Result.withError(MovieError.unknown);
        }

        sql = "SELECT * FROM VERSION WHERE MOVIE_title_id='" + title_id + "'";
        try {
            PreparedStatement ppst = connection.prepareStatement(sql);
            ResultSet rs = ppst.executeQuery();
            if (rs.next()) {
                sql = "DELETE FROM VERSION WHERE MOVIE_title_id='" + title_id + "'";

                try {
                    ppst = connection.prepareStatement(sql);
                    int r = ppst.executeUpdate();
                    if (r < 1) return Result.withError(MovieError.unknown);
                    else {
                        connection.commit();
                    }

                } catch (Exception e)  {
                    e.printStackTrace();
                }
            }
            rs.close();
        } catch(Exception e) {
            return Result.withError(MovieError.unknown);
        }  
        sql = "SELECT * FROM MOVIE WHERE title_id='" + title_id + "'";
        try {
            PreparedStatement ppst = connection.prepareStatement(sql);
            ResultSet rs = ppst.executeQuery();
            if (rs.next()) {
                sql = "DELETE FROM MOVIE WHERE title_id='" + title_id + "'";

                try {
                    ppst = connection.prepareStatement(sql);
                    int r = ppst.executeUpdate();
                    if (r < 1) return Result.withError(MovieError.unknown);
                    else {
                        connection.commit();
                    }

                } catch (Exception e)  {
                    e.printStackTrace();
                }
            }
            rs.close();
        } catch(Exception e) {
            return Result.withError(MovieError.unknown);
        }  
        
        return Result.success;
    }

    public enum MovieError implements Error {
        noActors(1, "The actor is not registered"),
        title_idAlreadyExists(2, "title_id Already Exists"),
        MOVIE_CAST_ACTOR(3, "DELETE FROM MOVIE_CAST_ACTOR WHERE MOVIE_title_id"),
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

    private boolean shoudUpdateMovie(MovieDTO m) {
        return m.getTitleId() != null ||
                m.getType() != null ||
                m.getRuntime() != null ||
                m.getStartYear() != null ||
                m.getTotal() != null ||
                m.getNum() != null;
    }
    private boolean shoudUpdateVersion(MovieDTO m) {
        return m.getTitle() != null;
    }
    private boolean shouldUpdateGenre(MovieDTO m) {
        return m.getGenreList() != null;
    }
    @Override
    public Result updateMovie(MovieDTO before, MovieDTO changed) {
        try {
            String sql = null;
            PreparedStatement ppst;
            int r;
            if (shoudUpdateMovie(changed)) {
                changed.setNum(before.getNum());
                sql = "UPDATE MOVIE SET " + DB.TABLE.setFormOf("Movie", changed) + " WHERE TITLE_ID='" + before.getTitleId() + "'";
                //System.out.println(sql);
                ppst = connection.prepareStatement(sql);
                r = ppst.executeUpdate();
                if (r == 0) {
                    throw new Exception();
                }
            }
            if (shoudUpdateVersion(changed)) {
                sql = "UPDATE VERSION SET " + DB.TABLE.setFormOf("Version", changed) + " WHERE MOVIE_TITLE_ID='" + before.getTitleId() + "' AND region='"+before.getRegion()+"'";
                //System.out.println(sql);
                ppst = connection.prepareStatement(sql);
                r = ppst.executeUpdate();
                if (r == 0) {
                    throw new Exception();
                }
            }
            
            if (shouldUpdateGenre(changed)) {
                sql = "DELETE FROM MOVIE_HAS_GENRE WHERE MOVIE_TITLE_ID='" + before.getTitleId() + "'";
                //System.out.println(sql);
                ppst = connection.prepareStatement(sql);
                r = ppst.executeUpdate();
                if (r == 0) {
                    throw new Exception();
                }
                changed.getGenreList().forEach(genre -> {
                    try {
                        String s = "INSERT INTO MOVIE_HAS_GENRE " + 
                        "VALUES ( "+ DB.TABLE.valueFormOf("MOVIE_HAS_GENRE", new MovieGenreDTO(before.getTitleId(), genre)) +" )";
                        //System.out.println(s);
                        PreparedStatement pp = connection.prepareStatement(s);
                        int rr = pp.executeUpdate();
                        if (rr == 0) {
                            throw new Exception();
                        }
                    } catch (Exception e) {
                        Result.withError(MovieError.unknown);
                    }
                });
            }
            connection.commit();
            return Result.success;

        } catch (Exception e)  {
            e.printStackTrace();
            Result.withError(MovieError.unknown);
        } 
        return Result.withError(MovieError.unknown);
    }

    @Override
    public Result recommandMovie(String id, String password) {
        MovieSearchConditionDTO condition = MovieSearchConditionDTO.fillWithDefault();
        Result result = searchAllMovie();
        if (result == Result.failure) return result;
        // 모든 영상 목록
        Map<String, MovieDTO> movies = (HashMap<String, MovieDTO>)result.getValue();
        result = ratingService.getMyRatingList(id, password);
        // 평가 항목이 없는 유저일 경우 평점 8 이상 영화 보여줌
        if (result == Result.failure) {
            if (result.getError() != RatingError.noResultWithGivenCondition) return result;
            movies = movies.values().stream()
                    .filter(i->i.getAvg() >= 8.0)
                    .collect(Collectors.toMap(MovieDTO::getTitleId, Function.identity()));
            return Result.withValue(movies);
        } 
        List<MyRatingVO> ratingList = (List<MyRatingVO>)result.getValue();
        Double temp = 0.0;
        for (int i = 0; i < ratingList.size(); i++) 
            if (ratingList.get(i).rating > temp) 
                temp = ratingList.get(i).rating;
        final Double max = temp;
        // 내가 가장 평점을 많이 준 영화 제목들
        List<String> titles = ratingList.stream().filter(i-> i.rating >= max).map(i->i.movieTitle).collect(Collectors.toList());
        // 내가 가장 평점을 많이 준 영화의 목록
        List<MovieDTO> likedMovies = movies.values().stream().filter(i->titles.contains(i.getTitle())).collect(Collectors.toList());
        // 내가 가장 평점을 많이 준 영화의 배우 목록
        Set<String> actors = likedMovies.stream().flatMap(i->i.getActorList().stream()).collect(Collectors.toSet());
        // 내가 안 본 영화 목록
        result = searchMoiveByCondition(id, password, condition);
        if (result == Result.failure) return result;
        movies = (HashMap<String, MovieDTO>)result.getValue();
        // 좋아하는 배우 영화 목록
        Map<String, MovieDTO>selected = movies.values().stream()
                            .filter(i-> i.getActorList().stream().anyMatch(actor->actors.contains(actor)))
                            .collect(Collectors.toMap(MovieDTO::getTitleId, Function.identity()));
        return Result.withValue(selected);
    }

    
}
