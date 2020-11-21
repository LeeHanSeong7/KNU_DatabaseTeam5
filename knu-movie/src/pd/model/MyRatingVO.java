package pd.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MyRatingVO {
	public final String movieTitle;
	public final Double rating;

    public MyRatingVO(String movieTitle, Double rating) {
        this.movieTitle = movieTitle;
        this.rating = rating;
    }

    public static final class COLUNM {
        public static final int TITLE = 1;
        public static final int RATING = 2;
    }

	public static List<MyRatingVO> getListFromResultSet(ResultSet rs) {
        List<MyRatingVO> list = new ArrayList<>();
        try {
            while(rs.next()) {
                list.add(
                    new MyRatingVO(
                        rs.getString(COLUNM.TITLE), 
                        rs.getDouble(COLUNM.RATING)
                    )
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return list;
	}
}
