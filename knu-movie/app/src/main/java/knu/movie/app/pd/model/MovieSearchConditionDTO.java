package knu.movie.app.pd.model;

import java.sql.Date;

public class MovieSearchConditionDTO {
	public String movieID;
	public String movieName;
	public Date Maxyear;
	public Date Minyear;
	public double Maxaver;
	public double Minaver;
	public double Maxtime;
	public double Mintime;
	public String genre;
	public String actor;
	public String type;
	public String region;
	
	public MovieSearchConditionDTO() {super();}
	
	public MovieSearchConditionDTO(
			String movieID,
			String movieName,
			Date Maxyear,
			Date Minyear,
			double Maxaver,
			double Minaver,
			double Maxtime,
			double Mintime,
			String genre,
			String actor,
			String type,
			String region
			) {
		this.movieID = movieID;
		this.movieName = movieName;
		this.Maxyear= Maxyear;
		this.Minyear= Minyear;
		this.Maxaver = Maxaver;
		this.Minaver = Minaver;
		this.Maxtime = Maxtime;
		this.Mintime = Mintime;
		this.genre = genre;
		this.actor = actor;
		this.type = type;
		this.region = region;
	}
	public static MovieSearchConditionDTO fillWithDefault() {
		return new MovieSearchConditionDTO("","",
				null,
				null,
				10,0,-1,-1, "", "","",null);
	}
}
