package pd.services;

import java.sql.*;
import pd.model.AccountDAO.ACCOUNT;
import pd.model.AccountDTO;
import pd.interfaces.AuthenticationService;
import pd.utils.Result;
import pd.utils.DB;
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
        String sql = "SELECT * FROM ACCOUNT WHERE email_id='" + id + "'";
        try {
            PreparedStatement ppst = connection.prepareStatement(sql);
            ResultSet rs = ppst.executeQuery();
            if (rs.next()) {
                rs.close();
                return Result.withError(AuthError.idAlreadyExists);
            }
            rs.close();
        } catch(Exception e) {
            return Result.withError(AuthError.unknown);
        }

        sql = "INSERT INTO ACCOUNT " + 
                    "VALUES ( "+ DB.TABLE.valueFormOf("Account", accountDTO) +" )";
        try {
            PreparedStatement ppst = connection.prepareStatement(sql);
            int r = ppst.executeUpdate();
            if (r != 1) return Result.withError(AuthError.accountInfoWrong);
            else {
                connection.commit();
                return Result.success;
            }
        } catch (Exception e ) {
            e.printStackTrace();
        }
        return Result.withError(AuthError.unknown);
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
        if (!id.equals(loggedInAcountInfo.getEmail_id())) return Result.withError(AuthError.idNotLoggedIn);
        if (!password.equals(loggedInAcountInfo.getPassword())) return Result.withError(AuthError.passwordWrong);
        String sql = "UPDATE ACCOUNT SET password='" + changed + "' WHERE email_id='" + id + "'";
    
        try {
            PreparedStatement ppst = connection.prepareStatement(sql);
            int r = ppst.executeUpdate();
            if (r == 1) {
                ppst.close();
                loggedInAcountInfo.setPassword(changed);
                connection.commit();
                return Result.success;
            } 
            ppst.close();
        } catch (Exception e)  {
            e.printStackTrace();
        } 
        return Result.withError(AuthError.unknown);
    }

    @Override
    public Result deleteAccount(String id, String password, String re_password) {
        if (!password.equals(re_password)) return Result.withError(AuthError.rePasswordDifferent);
        if (!id.equals(loggedInAcountInfo.getEmail_id())) return Result.withError(AuthError.idNotLoggedIn);
        if (!password.equals(loggedInAcountInfo.getPassword())) return Result.withError(AuthError.passwordWrong);
        String sql = "DELETE FROM ACCOUNT WHERE email_id='" + id + "'";
        try {
            PreparedStatement ppst = connection.prepareStatement(sql);
            int r = ppst.executeUpdate();
            if (r == 1) {
                ppst.close();
                loggedInAcountInfo = null;
                connection.commit();
                return Result.success;
            } 
            ppst.close();
        } catch (Exception e)  {
            e.printStackTrace();
        } 
        return Result.withError(AuthError.unknown);
    }

    @Override
    public Result changeAccountInfo(String id, String password, AccountDTO changed) {
        if (!id.equals(loggedInAcountInfo.getEmail_id())) return Result.withError(AuthError.idNotLoggedIn);
        if (!password.equals(loggedInAcountInfo.getPassword())) return Result.withError(AuthError.passwordWrong);
        if (loggedInAcountInfo.equals(changed)) return Result.withError(AuthError.noChangeOnInfo);
        if (changed.getEmail_id() != null) return Result.withError(AuthError.idCantBeChanged);
        if (changed.getPassword() != null) return Result.withError(AuthError.passwordCantBeChanged);
        String sql = "UPDATE ACCOUNT SET " + DB.TABLE.setFormOf("Account", changed) + " WHERE email_id='" + id + "'";
        try {
            PreparedStatement ppst = connection.prepareStatement(sql);
            int r = ppst.executeUpdate();
            if (r == 1) {
                ppst.close();
                sql = "SELECT * FROM ACCOUNT WHERE email_id='" + id + "'";
                ppst = connection.prepareStatement(sql);
                ResultSet rs = ppst.executeQuery();
                if (rs.next()) {
                    if (password.equals(rs.getString(ACCOUNT.PASSWORD))) {
                        this.loggedInAcountInfo = AccountDTO.fromResultSet(rs);
                        rs.close();
                        connection.commit();
                        return Result.success;
                    } else {
                        rs.close();
                        return Result.withError(AuthError.passwordWrong);
                    }
                } else {
                    rs.close();
                    return Result.withError(AuthError.idNotFound);
                } 
            } 
            ppst.close();
        } catch (Exception e)  {
            e.printStackTrace();
        } 
        return Result.withError(AuthError.unknown);
    }

    private enum AuthError implements Error {
        idNotFound(1, "ID Incorrect!"),
        passwordWrong(2, "Wrong Password!"),
        accountInfoWrong(3, "Couldn't Sign Up, Check your Account Info again."),
        rePasswordDifferent(4, "Password Confirmation Incorrect!"),
        idNotLoggedIn(5, "You're Not LoggedIn User. Please Login again."),
        noChangeOnInfo(6, "There is no changed values in account info."),
        idCantBeChanged(7, "You cannot change your ID"),
        passwordCantBeChanged(8, "You cannot change your Password in this menu. use change password menu"),
        idAlreadyExists(9, "Your Email already Registered. please Enter Anoter Email."),
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
    public AccountDTO getloggedInAccountInfo() {
        return new AccountDTO(loggedInAcountInfo);
    }

}