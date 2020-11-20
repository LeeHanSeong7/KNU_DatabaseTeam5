package pd.stub;

import java.sql.Connection;
import java.sql.Date;

import pd.interfaces.MovieService;
import pd.utils.Result;

public class StubMovieService implements MovieService{
    public StubMovieService() {
    }

    //영상물에 보여줘야 하는 데이터를 나름대로 생각해서 출려함. 그런데 version이 너무 많아서 일단 KR로 고정하였음
    @Override
    public Result viewWholeVideo(){
        return Result.success;
    }
    
    @Override
    public Result searchMoiveByCondition(String movieName, Date Maxyear, Date Minyear, double Maxaver, double Minaver, String genre, String actor, String type){
        return Result.success;
    }

    @Override
    public void setConnection(Connection Connection) {
        // TODO Auto-generated method stub
    }
}
