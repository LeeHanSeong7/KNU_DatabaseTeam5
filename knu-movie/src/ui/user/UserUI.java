package ui.user;

import java.sql.Connection;
import java.sql.Date;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import injected.DIContainer.Services;
import pd.interfaces.AuthenticationService;
import pd.interfaces.MovieService;
import pd.interfaces.RatingService;
import pd.model.AccountDTO;
import pd.model.MovieDTO;
import pd.model.MyRatingVO;
import pd.model.RatingVOList;
import pd.model.MovieSearchConditionDTO;
import pd.utils.Result;
import ui.SearchUITool;
import ui.admin.AdminUI;

public class UserUI {
	private AuthenticationService authService;
	private MovieService movieService;
	private RatingService ratingService;
	private Connection conn;
	
	public UserUI(Services services) {
		authService = services.authenticationService;
		movieService = services.movieService;
		ratingService = services.ratingService;
	}
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	public void run() {
		Scanner scan = new Scanner(System.in);
		while(true) {
			String str;
			System.out.println("-user menu-");
			System.out.println("0.help   1.search   2.my rating   3.account   4.sign out");
			str = scan.nextLine();
			str = str.replaceAll(" ", "");
			if (str.equals("0")) {
			}
			else if(str.equals("1")){
				MovieSearchConditionDTO condition = SearchUITool.makeMovieSearchConditionForUser();
				System.out.println("--result--");
				Result result = movieService.searchMoiveByCondition(condition);
				if (result == Result.success) {
					ArrayList<MovieDTO> movieDTOList = (ArrayList<MovieDTO>)result.getValue();
					int i;
					for(i=0;i<movieDTOList.size();i++){
		                MovieDTO item = movieDTOList.get(i);
		                System.out.println("<"+i+">");
		                System.out.println("title:" + item.getTitle());
		                System.out.println("type: " + item.getType());
		                //System.out.println("type: " + item.getType() + ", region: " + item.getRegion());
		                System.out.println("genre: " + item.getGenreList());
		                //System.out.println("casting: " + item.getActorList());
		                System.out.println("runtime: " + item.getRuntime()+"min "+ ", startYear: " + item.getStartYear());
		                System.out.println("avgRating: " + String.valueOf(item.getAvg()) + ", numVotes: " + item.getNumVotes());
		            }
					//
					int size = i;
					System.out.print("if you want to rate movie, give index :");
					try {
						str = scan.nextLine();
						int index = Integer.parseInt(str);
						if (size > index && index >= 0) {
			                MovieDTO item = movieDTOList.get(index);
							double stars;
							System.out.print("stars(0~10) : ");
							try {
								stars = Double.parseDouble(scan.nextLine());
							}
							catch(Exception e) {
								System.out.println("wrong format");
								continue;
							}
							condition = new MovieSearchConditionDTO();
							condition.fillWithDefault();
							condition.movieID = item.getTitleId();
							condition.movieName = item.getTitle();
							result = movieService.rateMovie(condition, stars);
							if (result == Result.success) 
								System.out.println("movie rated");
							else
								System.out.println(result.getError().getDescription());
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
				Result result = ratingService.getMyRatingList();
				if (result == Result.failure)
					System.out.println(result.getError().getDescription());
				else {
					System.out.println("---result---");
					List<MyRatingVO> list = (List<MyRatingVO>)result.getValue();
					for (int i = 0 ; i<list.size();i++) {
						MyRatingVO item = list.get(i);
						System.out.print("STARS:");
						int j;
						for(j = 1; j<=item.rating;j++) {
							System.out.print("*");
						}
						for(; j<=10;j++) {
							System.out.print("-");
						}
						System.out.print(String.format("(%.1f)", item.rating));
						System.out.println(" TITLE : '"+item.movieTitle+"'");
					}
					System.out.println("------------");
				}
			}
			else if(str.equals("3")){
				UserAccountUI ui = new UserAccountUI(authService);
				ui.setConnection(conn);
				if (ui.run() == false)
					break;
			}
			else if(str.equals("4"))	break;
			else System.out.println("invalid operation");
		}
	}
}
