package pd.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RatingVOList {
    public final String title;
    public final List<RatingVO> ratings;

    public RatingVOList(String title, List<RatingVO> ratings) {
        this.title= title;
        this.ratings = ratings;
    }

    static public List<RatingVOList> getRatingListsFromResultSet(ResultSet rs) {
       List<RatingVOList> lists = new ArrayList<>();
       
        try {
            String movieID = null;
            RatingVOList list = null;
            while (rs.next()) {
                //first
                if (movieID == null) {
                    movieID = rs.getString(RatingVO.COLUNM.MOVIE_TITLE_ID);
                    list = new RatingVOList(rs.getString(RatingVO.COLUNM.TITLE), new ArrayList<>());
                    list.ratings.add(
                        new RatingVO(
                            rs.getString(RatingVO.COLUNM.ACCOUNT_EMAIL_ID),
                             rs.getDouble(RatingVO.COLUNM.STARS)
                        )
                    );
                    continue;
                }
                if (!movieID.equals(rs.getString(RatingVO.COLUNM.MOVIE_TITLE_ID))) {
                    movieID = rs.getString(RatingVO.COLUNM.MOVIE_TITLE_ID);
                    lists.add(list);
                    list = new RatingVOList(rs.getString(RatingVO.COLUNM.TITLE), new ArrayList<>());
                }

                list.ratings.add(
                    new RatingVO(
                        rs.getString(RatingVO.COLUNM.ACCOUNT_EMAIL_ID),
                         rs.getDouble(RatingVO.COLUNM.STARS)
                    )
                );
            }
            if (list != null) {
                lists.add(list);
            }
       } catch(Exception e) {
           e.printStackTrace();
       }
       return lists;
    }
}
