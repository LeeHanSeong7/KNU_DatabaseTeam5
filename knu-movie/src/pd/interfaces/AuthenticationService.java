package pd.interfaces;
import pd.model.AccountDTO;
import pd.utils.Result;

public interface AuthenticationService {
    Result login(String id, String password);
    Result signUp(String id, String password, AccountDTO accountDTO);
    //Result changeAccountInfo()
    Result changePassword(String id, String password, String changed);
    Result deleteAccount(String id, String password, String re_password);
    AccountDTO getloggedInAccountInfo();
} 