package pd.stub;

import java.sql.Date;

import pd.model.AccountDTO;

public class StubModels {
    public static class Account {
        public static final AccountDTO admin = new AccountDTO("admin", "admin", "phone", "haha", "3123-232 goho", "M", new Date(2010,12,2), "Com", "PREM", true);
        public static final AccountDTO user = new AccountDTO("admin", "admin", "phone", "haha", "3123-232 goho", "M", new Date(2010,12,2), "Com", "PREM", false);
    }
}
