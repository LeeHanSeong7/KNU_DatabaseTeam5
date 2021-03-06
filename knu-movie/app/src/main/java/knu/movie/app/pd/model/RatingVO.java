package knu.movie.app.pd.model;

public class RatingVO {
    public String emailId;
    public double rating;

    public RatingVO() {
        super();
    }

    public RatingVO(String emailId, double rating) {
        this.emailId = emailId;
        this.rating = rating;
    }

    public static final class COLUNM {
        public static final int MOVIE_TITLE_ID = 1;
        public static final int TITLE = 2;
        public static final int ACCOUNT_EMAIL_ID = 3;
        public static final int STARS = 4;
    }
}
