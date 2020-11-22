package ui.admin;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
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
			System.out.println("0.help   1.search   2.upload   3.back");
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
		                System.out.println("[id:"+item.getTitleId()+"]title:" + item.getTitle());
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
						new MovieDTO(null, null, null, null, null, 0, null, 0, 0, new ArrayList<String>(), null, new ArrayList<String>()));
				if (movieData == null) {
					System.out.println("upload failed");
					continue;
				}
				else {
//					Result result = movieService.movieUpload(movieData);
//					if (result == Result.success)
//						System.out.println("upload conplete");
//					else {
//						System.out.println(result.getError().getDescription());
//						continue;
//					}
				}
			}
			else if(str.equals("3"))	break;
			else System.out.println("invalid operation");
		}
	}
	Boolean modify(MovieDTO data) {
		Scanner scan = new Scanner(System.in);
		MovieSearchConditionDTO condition = new MovieSearchConditionDTO();
		condition.fillWithDefault();
		System.out.print("MovieId	: ");
		condition.movieID = scan.nextLine();
		System.out.print("Region	: ");
		condition.movieName = scan.nextLine();
		Result result = movieService.searchMoiveByCondition(condition) ;
		if (result == Result.success) {
			ArrayList<MovieDTO> temp = (ArrayList<MovieDTO>)result.getValue();
			MovieDTO movieData = temp.get(0);
			movieData = MovieUITool.makeMovieDTO(movieData);
			
			//result = movieService.movieDelete(condition.movieID,condition.movieName);
			// 재업로드
		}
		else
			System.out.println(result.getError().getDescription());
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
