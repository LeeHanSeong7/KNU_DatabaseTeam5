package ui.admin;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import injected.DIContainer.Services;
import pd.interfaces.MovieService;
import pd.model.AccountDTO;
import pd.model.MovieDTO;
import pd.model.MovieSearchConditionDTO;
import pd.utils.Result;
import ui.MovieUITool;
import ui.SearchUITool;

public class AdminMovieManageUI {
	private Connection conn;
	private MovieService movieService;
	
	public AdminMovieManageUI(MovieService services) {
		movieService = services;
	}
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	public void run() {
		Scanner scan = new Scanner(System.in);
		while(true) {
			String str;
			System.out.println("-movie manage menu-");
			System.out.println("1.search   2.upload   3.back");
			str = scan.nextLine();
			str = str.replaceAll(" ", "");
			if (str.equals("0")) {
			}
			else if(str.equals("1")){
				MovieSearchConditionDTO condition = SearchUITool.makeMovieSearchCondition();
				System.out.println("--result--");
				Result result = movieService.searchMoiveByCondition(condition);
				if (result == Result.success) {
					ArrayList<MovieDTO> movieDTOList = (ArrayList<MovieDTO>)result.getValue();
					int i;
					for(i=0;i<movieDTOList.size();i++){
		                MovieDTO item = movieDTOList.get(i);
		                System.out.println("<"+i+">");
		                System.out.println("[id:"+item.getTitleId()+"] title:" + item.getTitle());
		                System.out.println("type: " + item.getType() + ", region: " + item.getRegion());
		                System.out.println("genre: " + item.getGenreList());
		                System.out.println("casting: " + item.getActorList());
		                System.out.println("runtime: " + item.getRuntime()+"min "+ ", startYear: " + item.getStartYear());
		                System.out.println("avgRating: " + String.valueOf(item.getAvg()) + ", numVotes: " + item.getNumVotes());
		            }
					int size = i;
					System.out.print("if you want to manage movie, give index :");
					try {
						str = scan.nextLine();
						int index = Integer.parseInt(str);
						if (size > index && index >= 0) {
							System.out.print("1.modify   2.delete :");
							str = scan.nextLine();
							if (str.equals("1")) {
								modify(movieDTOList.get(index));
							}
							else if (str.equals("2")) {
								delete(movieDTOList.get(index));
							}
							else {
								System.out.println("wrong format.");
							}
						}
						else
							System.out.println("wrong format.");
					}
					catch(Exception e) {
						System.out.println("wrong format.");
					}
			    }
			    else {
			    	System.out.println(result.getError().getDescription());
			    }
			}
			else if(str.equals("2")){
				MovieDTO movieData = MovieUITool.makeMovieDTO(
						new MovieDTO(null, null, null, null, null, null, null, null, null, new ArrayList<String>(), null, new ArrayList<String>()));
				if (movieData == null) {
					System.out.println("upload failed");
					continue;
				}
				else {
					System.out.println("upload this video? 'y'");
					str = scan.nextLine();
					if (str.equals("y") || str.equals("Y")) {
						Result result = movieService.movieUpload(movieData);
						if (result == Result.success)
							System.out.println("upload conplete");
						else {
							System.out.println(result.getError().getDescription());
							continue;
						}
					}
				}
			}
			else if(str.equals("3"))	break;
			else System.out.println("invalid operation");
		}
	}
	Boolean modify(MovieDTO data) {
		MovieDTO movieData = new MovieDTO(null, null, null, null, null, null, null, data.getNum(), null, new ArrayList<String>(), null, new ArrayList<String>());
		String str;
		Scanner scan = new Scanner(System.in);
		System.out.println("---information---");
		System.out.print("title 	: ");
		str = scan.nextLine();
		if (str.length() != 0) movieData.setRegion(str);
		System.out.print("runtime 	: ");
		str = scan.nextLine();
		try {
			Integer.parseInt(str);
			movieData.setRuntime(str);
		}
		catch(Exception e) {
			System.out.println("wrong format, please modify later");
		}
		System.out.print("startyear(yyyy-mm-dd) : ");
		str = scan.nextLine();
		try {
			Date.valueOf(str);
			movieData.setStartYear(str);
		}
		catch(Exception e) {
			System.out.println("wrong format, please modify later");
		}
		System.out.print("genres , 'ex) war , romance , comedy': ");
		str = scan.nextLine();
		if (str.length() != 0) {
			try {
				List<String> temp = new ArrayList<String>();
				String[] items = str.replaceAll(" ", "").split(",");
				temp = (List<String>)Arrays.asList(items);
				for (int i =0; i< temp.size() ; i++) {
					String t = temp.get(i).toLowerCase();
					t = t.substring(0,1).toUpperCase() + t.substring(1);
					temp.set(i, t);
				}
				movieData.setGenreList(temp);
			}
			catch(Exception e) {
				movieData.setGenreList(null);
				System.out.println("wrong format, please modify later");
			}
		}
		else
			movieData.setGenreList(null);
		System.out.print("type (1.KnuMovieDB Original 2.Movie 3.TV Series): ");
		str = scan.nextLine();
		if (str.equals("1"))
			movieData.setType("KnuMovieDB Original");
		else if (str.equals("2"))
			movieData.setType("Movie");
		else if (str.equals("3"))
			movieData.setType("TV Series");
		else
			System.out.println("wrong format, please modify later");
		System.out.println("-----------------");
		
		Result result = movieService.updateMovie(data, movieData);
		if (result == Result.success)
			System.out.println("modify conplete");
		else {
			System.out.println(result.getError().getDescription());
			return false;
		}
		return true;
	}
	Boolean delete(MovieDTO data) {
		Scanner scan = new Scanner(System.in);
		String str;
		System.out.print("delete this movie ? 'y' :");
		str = scan.nextLine();
		if (str.equals("y") || str.equals("Y")) {
			Result result = movieService.movieDelete(data.getTitleId());
			if (result == Result.success)
				System.out.println("movie has deleted");
			else 
				System.out.println(result.getError().getDescription());
		}
		else {
			return false;
		}
		return true;
	}
}
