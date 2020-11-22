package pd.interfaces;

import java.sql.Connection;
import java.sql.Date;

import pd.model.MovieDTO;
import pd.model.MovieSearchConditionDTO;
import pd.utils.Result;

public interface MovieService {
    Result viewWholeVideo();
    Result searchMoiveByCondition(MovieSearchConditionDTO condition);
    Result rateMovie(MovieSearchConditionDTO condition, double stars);
    Result movieUpload(String title_id, String type, double runtime, Date start_year);
    Result movieDelete(String title_id);
    Result updateMovie(MovieDTO before, MovieDTO changed);
    void setConnection(Connection Connection);
    void setRegion();
}
