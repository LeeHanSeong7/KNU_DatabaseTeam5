package knu.movie.app.pd.model;

public class RatingSearchConditionDTO {
	public String movieName;
	public String Email;
	public double Maxstars;
	public double Minstars;
	
	public RatingSearchConditionDTO() {super();}
	
	public RatingSearchConditionDTO(
			String movieName,
			String Email,
			double Maxstars,
			double Minstars
			) {
		this.movieName = movieName;
		this.Email = Email;
		this.Maxstars = Maxstars;
		this.Minstars = Minstars;
	}
	public RatingSearchConditionDTO fillWithDefault() {
		return new RatingSearchConditionDTO("", "", 10, 0);
	}
}
