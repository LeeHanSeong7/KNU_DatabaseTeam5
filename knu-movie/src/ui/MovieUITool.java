package ui;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import pd.model.MovieDTO;
import pd.model.MovieSearchConditionDTO;

public class MovieUITool {
	public MovieUITool() {};
	public static MovieDTO makeMovieDTO(MovieDTO init) {
		MovieDTO res = init;
		String genreList = "";
		String actorList = "";
		String str = "";
		Scanner scan = new Scanner(System.in);
		res.setStartYear("2020-01-01");
		while(true) {
			System.out.println("--movie Information--");
			System.out.println("target list");
			System.out.println("1.titleID(only) 	: " + res.getTitleId());
			System.out.println("2.title(only)   	: " + res.getTitle());
			System.out.println("3.region(only) 	  	: " + res.getRegion());
			System.out.println("4.runtime 	    	: " + res.getRuntime());
			System.out.println("5.startyear(yyyy-mm-dd): " + res.getStartYear());
			System.out.println("6.genres 			: " + genreList);
			System.out.println("7.actors 			: " + actorList);
			System.out.println("8.type 				: " + res.getType());
			System.out.println("---------------------");
			System.out.print("give condition ' ex) 2:=name ', type 'end' if you want result : ");
			str = scan.nextLine();
			if (str.length() == 0) continue;
			else if (str.equals("end")) {
				break;
			}
			try {
				String[] temp = str.split(":=");
				if (temp[1].length() == 0) continue;
				else if (temp[0].equals("1"))
					res.setTitleId(temp[1]);
				else if (temp[0].equals("2"))
					res.setTitle(temp[1]);
				else if (temp[0].equals("3"))
					res.setRegion(temp[1]);
				else if (temp[0].equals("4")) {
					try {
						Integer.parseInt(temp[1]);
						res.setRuntime(temp[1]);
					}
					catch(Exception e)
					{
						System.out.println("***invalid operation, ' maxyear:=yyyy-mm-dd '***");
						continue;
					}
				}
				else if (temp[0].equals("5")) {
					try {
						Date.valueOf(temp[1]);
						res.setStartYear(temp[1]);
					}
					catch(Exception e) {
						System.out.println("***invalid operation, ' maxyear:=yyyy-mm-dd '***");
					}
				}
				else if (temp[0].equals("6")) {
					genreList = genreList+", "+temp[1];
				}
				else if (temp[0].equals("7")) {
					actorList = actorList+", "+temp[1];
				}
				else if (temp[0].equals("8")) 
					res.setType(temp[1]);
				else 
					System.out.println("***invalid operation, ' target:=value ' or 'end'***");
			}
			catch(Exception e) {
				System.out.println("***invalid operation, ' target:=value ' or 'end'***");
			}
		}
		if (genreList.length() == 0) genreList = null;
		else {
			try {
				List<String> temp = new ArrayList<String>();
				String[] items = genreList.split(", ");
				temp = (List<String>)Arrays.asList(items);
				for (int i =0; i< temp.size() ; i++) {
					String t = temp.get(i).toLowerCase();
					t = t.substring(0,1).toUpperCase() + t.substring(1);
					temp.set(i, t);
				}
				res.setGenreList(temp);
			}
			catch(Exception e) {
				res.setGenreList(null);
				System.out.println("wrong format, please modify later");
			}
		}
		if (actorList.length() == 0) actorList = null;
		else {
			try {
				List<String> temp = new ArrayList<String>();
				String[] items = actorList.split(", ");
				temp = (List<String>)Arrays.asList(items);
				for (int i =0; i< temp.size() ; i++) {
					String t = temp.get(i).toLowerCase();
					t = t.substring(0,1).toUpperCase() + t.substring(1);
					temp.set(i, t);
				}
				res.setAcotrList(temp);
			}
			catch(Exception e) {
				res.setAcotrList(null);
				System.out.println("wrong format, please modify later");
			}
		}
		return res;
	}
}
