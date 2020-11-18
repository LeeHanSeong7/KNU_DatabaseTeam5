import java.sql.*;

import model.AccountDAO.ACCOUNT;
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
}