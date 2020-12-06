package knu.movie.app.pd.model;

import knu.movie.app.pd.interfaces.DTO;

public class MovieGenreDTO implements DTO {
    public String titleId;
    public String genreId;

    public MovieGenreDTO() {
        super();
    }

    public MovieGenreDTO(String titleId, String genreId) {
        this.titleId = titleId;
        this.genreId = genreId;
    }
}
