package knu.movie.app.pd.model;

import knu.movie.app.pd.interfaces.DTO;

public class MovieActorDTO implements DTO {
    public String titleId;
    public String actorId;

    public MovieActorDTO() {
        super();
    }

    public MovieActorDTO(String titleId, String actorId) {
        this.titleId = titleId;
        this.actorId = actorId;
    }
}
