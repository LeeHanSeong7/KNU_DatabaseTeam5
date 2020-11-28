package knu.movie.app.pd.utils;

import knu.movie.app.pd.interfaces.DTO;
import knu.movie.app.pd.model.AccountDTO;
import knu.movie.app.pd.model.MovieDTO;
import knu.movie.app.pd.model.MovieGenreDTO;

public class DB {
    public enum TABLE {
        ACCOUNT, RATING, MOVIE, VERSION, MOVIE_HAS_GENRE, MOVIE_CAST_ACTOR, NONE;

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

        public static String whereFormOf(String table, DTO dto) {
            switch (TABLE.of(table)) {
                case ACCOUNT:
                    AccountDTO a = (AccountDTO) dto;
                    return (
                        (a.getEmail_id()==null ? "":("id="+VARCHAR(a.getEmail_id(), OPTION.NOT_NULL) + ", "))  + 
                        (a.getPassword()==null ? "":("password="+VARCHAR(a.getPassword(), OPTION.NOT_NULL) + ", ")) + 
                        (a.getPhone_number()==null ? "":("phone_number="+VARCHAR(a.getPhone_number(), OPTION.NOT_NULL) + ", ")) + 
                        (a.getName()==null ? "":("name="+VARCHAR(a.getName(), OPTION.NOT_NULL) + ", ")) + 
                        (a.getAddress()==null ? "":("address="+VARCHAR(a.getAddress()) + ", ")) + 
                        (a.getGender()==null ? "":("gender="+CHAR(a.getGender()) + ", ")) + 
                        (a.getBirth_date()==null ? "":("birth_date="+DATE(a.getBirth_date().toString()) + ", ")) + 
                        (a.getJob()==null ? "":("job="+VARCHAR(a.getJob()) + ", ")) + 
                        (a.getMembership()==null ? "":("membership="+VARCHAR(a.getMembership()) + ", ")) + 
                        (a.getIsAdmin()==null ? "":("isAdmin="+NUMBER(a.getIsAdmin()? "1":"0")))
                    );
                default:
                    break;
            }
            return "";
        }

        public static String setFormOf(String table, DTO dto) {
            switch (TABLE.of(table)) {
                case ACCOUNT:
                    AccountDTO a = (AccountDTO) dto;
                    return (
                        (a.getEmail_id()==null ? "":("id="+VARCHAR(a.getEmail_id(), OPTION.NOT_NULL) + ", "))  + 
                        (a.getPassword()==null ? "":("password="+VARCHAR(a.getPassword(), OPTION.NOT_NULL) + ", ")) + 
                        (a.getPhone_number()==null ? "":("phone_number="+VARCHAR(a.getPhone_number(), OPTION.NOT_NULL) + ", ")) + 
                        (a.getName()==null ? "":("name="+VARCHAR(a.getName(), OPTION.NOT_NULL) + ", ")) + 
                        (a.getAddress()==null ? "":("address="+VARCHAR(a.getAddress()) + ", ")) + 
                        (a.getGender()==null ? "":("gender="+CHAR(a.getGender()) + ", ")) + 
                        (a.getBirth_date()==null ? "":("birth_date="+DATE(a.getBirth_date().toString()) + ", ")) + 
                        (a.getJob()==null ? "":("job="+VARCHAR(a.getJob()) + ", ")) + 
                        (a.getMembership()==null ? "":("membership="+VARCHAR(a.getMembership()) + ", ")) + 
                        (a.getIsAdmin()==null ? "":("isAdmin="+NUMBER(a.getIsAdmin()? "1":"0")))
                    );
                case MOVIE:
                    MovieDTO m = (MovieDTO) dto;
                    return (
                        (m.getTitleId()==null ? "":("Title_id="+VARCHAR(m.getTitleId(), OPTION.NOT_NULL) + ", "))  + 
                        (m.getType()==null ? "":("Type="+VARCHAR(m.getType(), OPTION.NOT_NULL) + ", ")) + 
                        (m.getRuntime()==null ? "":("Runtime="+NUMBER(m.getRuntime()) + ", ")) + 
                        (m.getStartYear()==null ? "":("Start_year="+DATE(m.getStartYear()) + ", ")) + 
                        (m.getTotal()==null ? "":("Total_rating="+NUMBER(m.getTotal().toString()) + ", ")) + 
                        (m.getNum()==null ? "":("Num_votes="+NUMBER(m.getNum().toString())))
                    );
                case VERSION:
                    MovieDTO v = (MovieDTO) dto;
                    return (
                        (v.getTitle()==null ? "":("Title="+VARCHAR(v.getTitle(), OPTION.NOT_NULL)))
                    );
                default:
                    break;
            }
            return "";
        }

        public static String valueFormOf(String table, DTO dto) {
            switch (TABLE.of(table)) {
                case ACCOUNT:
                    AccountDTO a = (AccountDTO) dto;
                    return ((a.getEmail_id()==null ? "NULL, ":VARCHAR(a.getEmail_id(), OPTION.NOT_NULL) + ", ") + 
                            (a.getPassword()==null ? "NULL, ":VARCHAR(a.getPassword(), OPTION.NOT_NULL) + ", ") + 
                            (a.getPhone_number()==null ? "NULL, ":VARCHAR(a.getPhone_number(), OPTION.NOT_NULL) + ", ") + 
                            (a.getName()==null ? "NULL, ":VARCHAR(a.getName(), OPTION.NOT_NULL) + ", ") + 
                            (a.getAddress()==null ? "NULL, ":VARCHAR(a.getAddress()) + ", ") + 
                            (a.getGender()==null ? "NULL, ":CHAR(a.getGender()) + ", ") + 
                            (a.getBirth_date()==null ? "NULL, ":DATE(a.getBirth_date().toString()) + ", ") + 
                            (a.getJob()==null ? "NULL, ":VARCHAR(a.getJob()) + ", ") + 
                            (a.getMembership()==null ? "NULL, ":VARCHAR(a.getMembership()) + ", ") + 
                            (a.getIsAdmin()==null ? "NULL":NUMBER(a.getIsAdmin()? "1":"0"))
                            );
                case MOVIE_HAS_GENRE:
                    MovieGenreDTO mg = (MovieGenreDTO) dto;
                    return ((mg.titleId==null ? "NULL, ":VARCHAR(mg.titleId, OPTION.NOT_NULL) + ", ") + 
                            (mg.genreId==null ? "NULL, ":VARCHAR(mg.genreId, OPTION.NOT_NULL))
                            );
                default:
                    break;

            }
            return "";
        }	
	}
}
