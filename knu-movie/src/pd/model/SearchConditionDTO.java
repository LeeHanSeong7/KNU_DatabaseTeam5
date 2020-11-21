package pd.model;

import java.sql.Date;

public class SearchConditionDTO {
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

	public String Email = "";
	public double Maxstars = 10;
	public double Minstars = 0;
	
	public SearchConditionDTO() {
	}
	public SearchConditionDTO(
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
			String Email,
			double Maxstars,
			double Minstars
	) 
	{
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
		this.Email = Email;
		this.Maxstars = Maxstars;
		this.Minstars = Minstars;
	}
	public void fillWithDefault() {
		movieID = "";
		movieName = "";
		Maxyear= Date.valueOf("2500-12-21");
		Minyear= Date.valueOf("1000-01-01");
		Maxaver = 10;
		Minaver = 0;
		Maxtime = 0;
		Mintime = 0;
		genre = "";
		actor = "";
		type = "";
		Email = "";
		Maxstars = 10;
		Minstars = 0;
	}
}
