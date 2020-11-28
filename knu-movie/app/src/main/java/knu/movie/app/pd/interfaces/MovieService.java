package knu.movie.app.pd.interfaces;

import java.sql.Connection;

import knu.movie.app.pd.model.MovieDTO;
import knu.movie.app.pd.model.MovieSearchConditionDTO;
import knu.movie.app.pd.utils.Result;

public interface MovieService {
    Result viewWholeVideo();
    Result searchMoiveByCondition(MovieSearchConditionDTO condition);
    Result rateMovie(MovieSearchConditionDTO condition, double stars);
    Result movieUpload(MovieDTO movieDTO);
    Result movieDelete(String title_id);
    Result updateMovie(MovieDTO before, MovieDTO changed);
    void setConnection(Connection Connection);
    void setRegion();
}
