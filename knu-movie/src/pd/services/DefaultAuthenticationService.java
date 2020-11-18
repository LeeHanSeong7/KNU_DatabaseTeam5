package pd.services;

import java.sql.*;
import pd.model.AccountDAO.ACCOUNT;
import pd.model.AccountDTO;
import pd.interfaces.AuthenticationService;
import pd.utils.Result;
import pd.utils.Error;

public class DefaultAuthenticationService implements AuthenticationService {
    private Connection connection;
    private AccountDTO loggedInAcountInfo;

    public DefaultAuthenticationService() {
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Result signUp(String id, String password, AccountDTO accountDTO) {
        return null;
    }

    @Override
    public Result login(String id, String password) {
        String sql = "SELECT * FROM ACCOUNT WHERE email_id='" + id + "'";
        try {
            PreparedStatement ppst = connection.prepareStatement(sql);
            ResultSet rs = ppst.executeQuery();
            if (rs.next()) {
                if (password.equals(rs.getString(ACCOUNT.PASSWORD))) {
                    this.loggedInAcountInfo = AccountDTO.fromResultSet(rs);
                    rs.close();
                    return Result.success;
                } else {
                    rs.close();
                    return Result.withError(AuthError.passwordWrong);
                }
            } else {
                rs.close();
                return Result.withError(AuthError.idNotFound);
            }
        } catch (Exception e)  {
            e.printStackTrace();
        } 
        return Result.withError(AuthError.unknown);
    }

    @Override
    public Result changePassword(String id, String password, String changed) {
        // TODO Auto-generated method stub
        return null;
    }

    public enum AuthError implements Error {
        idNotFound(1, "id incorrect!"),
        passwordWrong(2, "Wrong Password!"),
        unknown(400, "Unknown Error!");

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
    public Result deleteAccount(String id, String password, String re_password) {
        // TODO Auto-generated method stub
        return null;
    }
}