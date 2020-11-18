package interfaces;

import java.sql.Connection;

import model.AccountDTO;
import utils.Result;

public interface AuthenticationService {
    Result login(String id, String password);

    Result signUp(String id, String password, AccountDTO accountDTO);
    Result changePassword(String id, String password, String changed);
} 