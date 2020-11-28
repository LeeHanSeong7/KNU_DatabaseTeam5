package knu.movie.app.pd.stub;

import java.sql.Date;

import knu.movie.app.pd.model.AccountDTO;

public class StubModels {
    public static class Account {
        public static final AccountDTO admin = new AccountDTO("admin1", "admin", "phone", "haha", "3123-232 goho", "M", new Date(2010,12,2), "Com", "PREM", true);
        public static final AccountDTO user = new AccountDTO("user", "user", "phone", "haha", "3123-232 goho", "M", new Date(2010,12,2), "Com", "PREM", false);
    }
}
