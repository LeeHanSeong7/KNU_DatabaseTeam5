package pd.interfaces;

import java.sql.Connection;
import java.sql.Date;

import pd.utils.Result;

public interface MovieService {
    Result viewWholeVideo();
    Result searchMoiveByCondition(String movieName, Date Maxyear, Date Minyear, double Maxaver, double Minaver, String genre, String actor, String type);
    void setConnection(Connection Connection);
}
