import java.sql.*;

import model.AccountDTO;
//import interfaces.AccountInfo;
import interfaces.AuthenticationService;
import utils.Result;
import utils.Error;

public class DefaultAuthenticationService implements AuthenticationService {
    private Connection connection;

    private DefaultAuthenticationService() {
    }

    public static DefaultAuthenticationService getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final DefaultAuthenticationService INSTANCE = new DefaultAuthenticationService();
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Result login(String id, String password) {
        Result result = Result.withError(AuthError.idNotFound);
        return result;
    }

    @Override
    public Result changePassword(String id, String password, String changed) {
        // TODO Auto-generated method stub
        return null;
    }

    public enum AuthError implements Error {
        idNotFound(1, "id incorrect!"),
        passwordWrong(2, "Wrong Password!");

        private int code;
        private String description;

        @Override
        public String getDescription() {
            return this.description;
        }

        @Override
        public int getCode() {
            return this.code;
        }

        private AuthError(int code, String description) {
            this.code = code;
            this.description = description;
        }

    }

    @Override
    public Result signUp(String id, String password, AccountDTO accountDTO) {
        // TODO Auto-generated method stub
        return null;
    }
}