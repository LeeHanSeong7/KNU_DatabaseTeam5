package ui;

import java.sql.Date;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

import pd.model.MovieSearchConditionDTO;
import pd.model.RatingSearchConditionDTO;
import pd.utils.Result;

public class SearchUITool {
	public SearchUITool() {}
	public static MovieSearchConditionDTO makeMovieSearchCondition() {
		MovieSearchConditionDTO res = new MovieSearchConditionDTO();
		res = res.fillWithDefault();
		String str = "";
		Scanner scan = new Scanner(System.in);
		while(true) {
			System.out.println("--condition lists--");
			System.out.println("target list");
			System.out.println("1.movieID(only)   	: " + res.movieID);
			System.out.println("2.movieName(only)   : " + res.movieName);
			System.out.println("3.genre 	    	: " + res.genre);
			System.out.println("4.actor 	    	: " + res.actor);
			System.out.println("5.type 	    		: " + res.type);
			if(res.Maxyear != null){
				System.out.println("5.maxyear(yyyy-mm-dd) : " + res.Maxyear.toString());
			}else{
				System.out.println("5.maxyear(yyyy-mm-dd) : ");
			}
			if(res.Minyear != null){
				System.out.println("6.minyear(yyyy-mm-dd) : " + res.Minyear.toString());
			}else{
				System.out.println("5.maxyear(yyyy-mm-dd) : ");
			}
			System.out.println("8.maxaver(0~10) 	: " + res.Maxaver);
			System.out.println("9.minaver(0~10) 	: " + res.Minaver);
			System.out.println("10.maxtime 			: " + res.Maxtime);
			System.out.println("11.mintime 			: " + res.Mintime);
			System.out.println("-------------------");
			System.out.print("give condition ' ex) 1:=name ', type 'end' if you want result : ");
			str = scan.nextLine();
			if (str.length() == 0) continue;
			else if (str.equals("end")) break;
			try {
				String[] temp = str.split(":=");
				if (temp[1].length() == 0) continue;
				else if (temp[0].equals("1"))
					res.movieID = temp[1];
				else if (temp[0].equals("2"))
					res.movieName = temp[1];
				else if (temp[0].equals("3")) {
					str = temp[1];	
					res.genre = str.substring(0,1).toUpperCase() + str.substring(1);
				}
				else if (temp[0].equals("4")) {
					res.actor = temp[1];
				}
				else if (temp[0].equals("5"))
					res.type = temp[1];
				else if (temp[0].equals("6")) {
					try {
						res.Maxyear = Date.valueOf(temp[1]);
					}
					catch(Exception e) {
						System.out.println("***invalid operation, ' maxyear:=yyyy-mm-dd '***");
					}
				}
				else if (temp[0].equals("7")) {
					try {
						res.Minyear = Date.valueOf(temp[1]);
					}
					catch(Exception e) {
						System.out.println("***invalid operation, ' minyear:=yyyy-mm-dd '***");
					}
				}
				else if (temp[0].equals("8")) {
					try {
						res.Maxaver = Double.parseDouble(temp[1]);
					}
					catch(Exception e) {
						System.out.println("***invalid operation, ' maxaver:=0~10 '***");
					}
				}
				else if (temp[0].equals("9")) {
					try {
						res.Minaver = Double.parseDouble(temp[1]);
					}
					catch(Exception e) {
						System.out.println("***invalid operation, ' minaver:=0~10 '***");
					}
				}
				else if (temp[0].equals("10")) {
					try {
						res.Maxaver = Double.parseDouble(temp[1]);
					}
					catch(Exception e) {
						System.out.println("***invalid operation, ' maxtime:='num' '***");
					}
				}
				else if (temp[0].equals("11")) {
					try {
						res.Minaver = Double.parseDouble(temp[1]);
					}
					catch(Exception e) {
						System.out.println("***invalid operation, ' mintime:='num' '***");
					}
				}
				else 
					System.out.println("***invalid operation, ' target:=value ' or 'end'***");
			}
			catch(Exception e) {
				System.out.println("***invalid operation, ' target:=value ' or 'end'***");
			}
		}
		return res;
	}
	public static MovieSearchConditionDTO makeMovieSearchConditionForUser() {
		MovieSearchConditionDTO res = new MovieSearchConditionDTO();
		res = res.fillWithDefault();
		String str = "";
		Scanner scan = new Scanner(System.in);
		while(true) {
			System.out.println("--condition lists--");
			System.out.println("target list");
			System.out.println("1.movieName(only)  	: " + res.movieName);
			System.out.println("2.genre 	    	: " + res.genre);
			System.out.println("3.actor 	    	: " + res.actor);
			System.out.println("4.type 	    		: " + res.type);
			if(res.Maxyear != null){
				System.out.println("5.maxyear(yyyy-mm-dd) : " + res.Maxyear.toString());
			}else{
				System.out.println("5.maxyear(yyyy-mm-dd) : ");
			}
			if(res.Minyear != null){
				System.out.println("6.minyear(yyyy-mm-dd) : " + res.Minyear.toString());
			}else{
				System.out.println("5.maxyear(yyyy-mm-dd) : ");
			}
			System.out.println("7.maxaver(0~10) 	: " + res.Maxaver);
			System.out.println("8.minaver(0~10) 	: " + res.Minaver);
			System.out.println("-------------------");
			System.out.print("give condition ' ex) 1:=name ', type 'end' if you want result : ");
			str = scan.nextLine();
			if (str.length() == 0) continue;
			else if (str.equals("end")) break;
			try {
				String[] temp = str.split(":=");
				if (temp[1].length() == 0) continue;
				else if (temp[0].equals("1"))
					res.movieName = temp[1];
				else if (temp[0].equals("2")) {
					str = temp[1];	
					res.genre = str.substring(0,1).toUpperCase() + str.substring(1);
				}
				else if (temp[0].equals("3"))
					res.actor = temp[1];
				else if (temp[0].equals("4"))
					res.type = temp[1];
				else if (temp[0].equals("5")) {
					try {
						res.Maxyear = Date.valueOf(temp[1]);
					}
					catch(Exception e) {
						System.out.println("***invalid operation, ' maxyear:=yyyy-mm-dd '***");
					}
				}
				else if (temp[0].equals("6")) {
					try {
						res.Minyear = Date.valueOf(temp[1]);
					}
					catch(Exception e) {
						System.out.println("***invalid operation, ' minyear:=yyyy-mm-dd '***");
					}
				}
				else if (temp[0].equals("7")) {
					try {
						res.Maxaver = Double.parseDouble(temp[1]);
					}
					catch(Exception e) {
						System.out.println("***invalid operation, ' maxaver:=0~10 '***");
					}
				}
				else if (temp[0].equals("8")) {
					try {
						res.Minaver = Double.parseDouble(temp[1]);
					}
					catch(Exception e) {
						System.out.println("***invalid operation, ' minaver:=0~10 '***");
					}
				}
				else 
					System.out.println("***invalid operation, ' target:=value ' or 'end'***");
			}
			catch(Exception e) {
				System.out.println("***invalid operation, ' target:=value ' or 'end'***");
			}
		}
		return res;
	}
	public static RatingSearchConditionDTO makeRatingSearchCondition() {
		RatingSearchConditionDTO res = new RatingSearchConditionDTO();
		res = res.fillWithDefault();
		String str = "";
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			System.out.println("--condition lists--");
			System.out.println("target list");
			System.out.println("1.movieName(only)  	: " + res.movieName);
			System.out.println("2.Email 	    	: " + res.Email);
			System.out.println("3.maxstars(0~10) 	: " + res.Maxstars);
			System.out.println("4.minstars(0~10) 	: " + res.Minstars);
			System.out.println("-------------------");
			System.out.print("give condition ' ex) 1:=name ', type 'end' if you want result : ");
			str = scan.nextLine();
			if (str.length() == 0) continue;
			else if (str.equals("end")) break;
			try {
				String[] temp = str.split(":=");
				if (temp[1].length() == 0) continue;
				else if (temp[0].equals("1"))
					res.movieName = temp[1];
				else if (temp[0].equals("2"))
					res.Email = res.Email+", "+temp[1];
				else if (temp[0].equals("3")) {
					try {
						res.Maxstars = Double.parseDouble(temp[1]);
						if (res.Maxstars > 10 ||res. Maxstars < 0)
							System.out.println("***invalid operation, ' maxstars:=0~10 '***");
					}
					catch(Exception e) {
						System.out.println("***invalid operation, ' maxstars:=0~10 '***");
					}
				}
				else if (temp[0].equals("4")) {
					try {
						res.Minstars = Double.parseDouble(temp[1]);
						if (res.Minstars > 10 || res.Minstars < 0)
							System.out.println("***invalid operation, ' minstars:=0~10 '***");
					}
					catch(Exception e) {
						System.out.println("***invalid operation, ' minstars:=0~10 '***");
					}
				}
				else 
					System.out.println("***invalid operation, ' target:=value ' or 'end'***");
			}
			catch(Exception e) {
				System.out.println("***invalid operation, ' target:=value ' or 'end'***");
			}
		}
		return res;
	}
	public static String[] NoRedundantSplit(String input) {
		try {
			String[] Array = input.split(",");
			return new HashSet<String>(Arrays.asList(Array)).toArray(new String[0]);
		}
		catch(Exception e) {
			return null;
		}
	}
}
