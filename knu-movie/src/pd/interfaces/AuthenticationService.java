package pd.interfaces;

import java.sql.Connection;

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
    
} 
