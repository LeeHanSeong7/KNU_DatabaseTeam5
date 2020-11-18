package pd.utils;
import pd.interfaces.DTO;
import pd.model.AccountDTO;

public class DB {
    public enum TABLE {
        ACCOUNT, NONE;

        public static TABLE of(String s) {
            try {
                return valueOf(s.toUpperCase());
            } catch (Exception e) {
                return NONE;
            }

        }

        private enum OPTION {
            DEFAULT, NOT_NULL;
        }

        private static String VARCHAR(String s, OPTION... op) {
            boolean nullable = true;
            String ret = "";
            for (int i = 0; i < op.length; i++)
                switch (op[i]) {
                    case DEFAULT:
                        if (s.equals("DEFAULT"))
                            return s;
                        break;
                    case NOT_NULL:
                        nullable = false;
                        break;
                    default:
                        break;
                }
            if (nullable && s.equals("NULL"))
                return s;
            ret = "'" + s + "'";
            return ret;
        }

        private static String NUMBER(String s, OPTION... op) {
            boolean nullable = true;
            String ret = "";
            for (int i = 0; i < op.length; i++)
                switch (op[i]) {
                    case DEFAULT:
                        if (s.equals("DEFAULT"))
                            return s;
                        break;
                    case NOT_NULL:
                        nullable = false;
                        break;
                    default:
                        break;
                }
            if (nullable && s.equals("NULL"))
                return s;
            ret = s;
            return ret;
        }

        private static String DATE(String s, OPTION... op) {
            boolean nullable = true;
            String ret = "";
            for (int i = 0; i < op.length; i++)
                switch (op[i]) {
                    case DEFAULT:
                        if (s.equals("DEFAULT"))
                            return s;
                        break;
                    case NOT_NULL:
                        nullable = false;
                        break;
                    default:
                        break;
                }
            if (nullable && s.equals("NULL"))
                return s;
            ret = "TO_DATE('" + s + "', 'yyyy-mm-dd')";
            return ret;
        }

        private static String CHAR(String s, OPTION... op) {
            boolean nullable = true;
            String ret = "";
            for (int i = 0; i < op.length; i++)
                switch (op[i]) {
                    case DEFAULT:
                        if (s.equals("DEFAULT"))
                            return s;
                        break;
                    case NOT_NULL:
                        nullable = false;
                        break;
                    default:
                        break;
                }
            if (nullable && s.equals("NULL"))
                return s;
            ret = "'" + s + "'";
            return ret;
        }

        public static String valueFormOf(String table, DTO dto) {
            switch (TABLE.of(table)) {
                case ACCOUNT:
                    AccountDTO a = (AccountDTO) dto;
                    return (VARCHAR(a.getEmail_id(), OPTION.NOT_NULL) + ", " + 
                            VARCHAR(a.getPassword(), OPTION.NOT_NULL) + ", " + 
                            VARCHAR(a.getPhone_number(), OPTION.NOT_NULL) + ", " + 
                            VARCHAR(a.getName(), OPTION.NOT_NULL) + ", " + 
                            VARCHAR(a.getAddress()) + ", " + 
                            CHAR(a.getGender()) + ", " + 
                            DATE(a.getBirth_date().toString()) + ", " + 
                            VARCHAR(a.getJob()) + ", " + 
                            VARCHAR(a.getMembership()) + ", " + 
                            NUMBER(a.getIsAdmin()? "1":"0"));
                default:
                    break;

            }
            return "";
        }	
	}
}
