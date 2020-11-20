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
        String sql = "INSERT INTO ACCOUNT " + 
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
        String sql = "UPDATE ACCOUNT SET password='" + DB.TABLE.setFormOf("Account", changed) + "' WHERE email_id='" + id + "'";
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

    //영상물에 보여줘야 하는 데이터를 나름대로 생각해서 출려함. 그런데 version이 너무 많아서 일단 KR로 고정하였음
    @Override
    public Result viewWholeVideo(){
        String sql = "SELECT V.title, V.region, M.runtime, M.start_year, M.total_rating, M.num_votes " +
        "FROM MOVIE M join VERSION V on M.TITLE_ID = V.MOVIE_Title_id " +
        "WHERE V.Region = 'KR'";
        try {
            PreparedStatement ppst = connection.prepareStatement(sql);
            ResultSet rs = ppst.executeQuery();
            
            while(rs.next()) {
				String title = rs.getString(1);
				String region = rs.getString(2);
                String runtime = rs.getString(3);
                String startYear = rs.getString(4);
                int total  = rs.getInt(5);
                String numVotes = rs.getString(6);
                int num = rs.getInt(6);

                String avgRating;
                if(num == 0){
                    avgRating = "0";
                }else{
                    double avg = ((double)total/num);
                    avgRating = String.valueOf(avg);
                }
	
				System.out.println("title: " + title
                                +  ", region: " + region
                                +  ", runtime: " + runtime
                                +  ", startYear: " + startYear
                                +  ", avgRating: " + avgRating
								+  ", numVotes: " + numVotes);		
			}
            rs.close();
            return Result.success;
        } catch (Exception e)  {
            e.printStackTrace();
        } 
        return Result.withError(AuthError.unknown);
    }
    
    @Override
    public Result searchMoiveByCondition(String movieName, Date Maxyear, Date Minyear, double Maxaver, double Minaver, String genre, String actor, String type){
        return Result.success;
    }

    public enum AuthError implements Error {
        idNotFound(1, "ID Incorrect!"),
        passwordWrong(2, "Wrong Password!"),
        accountInfoWrong(3, "Couldn't Sign Up, Check your Account Info again."),
        rePasswordDifferent(4, "Password Confirmation Incorrect!"),
        idNotLoggedIn(5, "You're Not LoggedIn User. Please Login again."),
        noChangeOnInfo(6, "There is no changed values in account info."),
        idCantBeChanged(7, "You cannot change your ID"),
        passwordCantBeChanged(7, "You cannot change your Password in this menu. use change password menu"),
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