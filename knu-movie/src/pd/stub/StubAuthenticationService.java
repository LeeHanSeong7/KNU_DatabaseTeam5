package pd.stub;

import java.sql.Connection;

import pd.interfaces.AuthenticationService;
import pd.model.AccountDTO;
import pd.utils.Result;

public class StubAuthenticationService implements AuthenticationService {
    private AccountDTO loggedinUser;
    @Override
    public Result login(String id, String password) {
        if (id.equals("admin")) loggedinUser = StubModels.Account.admin;
        else loggedinUser = StubModels.Account.user;
        return Result.success;
    }

    @Override
    public Result signUp(String id, String password, AccountDTO accountDTO) {
        return Result.success;
    }

    @Override
    public Result changePassword(String id, String password, String changed) {
        // TODO Auto-generated method stub
        return Result.success;
    }

    @Override
    public Result deleteAccount(String id, String password, String re_password) {
        // TODO Auto-generated method stub
        return Result.success;
    }

    @Override
    public AccountDTO getloggedInAccountInfo() {
        return loggedinUser;
    }

    @Override
    public void setConnection(Connection Connection) {
        // TODO Auto-generated method stub
    }

    @Override
    public Result changeAccountInfo(String id, String password, AccountDTO changed) {
        // TODO Auto-generated method stub
        return Result.success;
    }

}