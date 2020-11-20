package pd.interfaces;

import java.sql.Connection;
import java.sql.Date;

import pd.model.AccountDTO;
import pd.utils.Result;

public interface AuthenticationService {
    Result login(String id, String password);
    Result signUp(String id, String password, AccountDTO accountDTO);
    Result changeAccountInfo(String id, String password, AccountDTO changed);
    Result changePassword(String id, String password, String changed);
    Result deleteAccount(String id, String password, String re_password);
    AccountDTO getloggedInAccountInfo();
    void setConnection(Connection Connection);
    Result viewWholeVideo();
    Result searchMoiveByCondition(String movieName, Date Maxyear, Date Minyear, double Maxaver, double Minaver, String genre, String actor, String type);
} 
