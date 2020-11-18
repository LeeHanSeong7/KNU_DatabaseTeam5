package pd.stub;

import pd.interfaces.AuthenticationService;
import pd.model.AccountDTO;
import pd.utils.Result;

public class StubAuthenticationService implements AuthenticationService {

    @Override
    public Result login(String id, String password) {
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

}