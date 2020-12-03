package knu.movie.app.pd.stub;

import java.sql.Connection;

import knu.movie.app.pd.interfaces.MovieService;
import knu.movie.app.pd.model.MovieDTO;
import knu.movie.app.pd.model.MovieSearchConditionDTO;
import knu.movie.app.pd.utils.Result;

public class StubMovieService implements MovieService{
    public StubMovieService() {
    }

    //영상물에 보여줘야 하는 데이터를 나름대로 생각해서 출려함. 그런데 version이 너무 많아서 일단 KR로 고정하였음
    @Override
    public Result viewWholeVideo(){
        return Result.success;
    }
    

    @Override
    public void setConnection(Connection Connection) {
        // TODO Auto-generated method stub
    }

    public void setRegion(){

    }

    public Result rateMovie(MovieSearchConditionDTO condition, double stars)
    {
        return Result.success;
    }
    public Result movieUpload(MovieDTO movieDTO){
        return Result.success;

    }
    public Result movieDelete(String title_id){
        return Result.success;
    }

    @Override
    public Result updateMovie(MovieDTO before, MovieDTO changed) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Result searchMoiveByCondition(String id, String password, MovieSearchConditionDTO condition) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Result rateMovie(String id, String password, MovieSearchConditionDTO condition, double stars) {
        // TODO Auto-generated method stub
        return null;
    }
}
