package knu.movie.app.pd.model;

import knu.movie.app.pd.interfaces.DTO;

public class MovieGenreDTO implements DTO {
    public final String titleId;
    public final String genreId;
    public MovieGenreDTO(String titleId, String genreId) {
        this.titleId = titleId;
        this.genreId = genreId;
    }
}
