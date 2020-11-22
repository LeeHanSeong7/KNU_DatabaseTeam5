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
    Result movieUpload(MovieDTO movieDTO);
    Result movieDelete(String title_id);
    void setConnection(Connection Connection);
    void setRegion();
}
