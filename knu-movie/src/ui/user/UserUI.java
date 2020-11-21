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
			System.out.println("0.help   1.search   2.rate   3.my ratings   4.account   5.sign out");
			str = scan.nextLine();
			str = str.replaceAll(" ", "");
			if (str.equals("0")) {
			}
			else if(str.equals("1")){
				MovieSearchConditionDTO condition = SearchUITool.makeMovieSearchConditionForUser();
				System.out.println("--result--");
			}
			else if(str.equals("2")){
				String movieName;
				double stars;
				System.out.print("movie name : ");
				movieName = scan.nextLine();
				System.out.print("stars(0~10) : ");
				try {
					stars = Double.parseDouble(scan.nextLine());
				}
				catch(Exception e) {
					System.out.println("wrong format");
					continue;
				}
			}
			else if(str.equals("3")){
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
			else if(str.equals("4")){
				UserAccountUI ui = new UserAccountUI(authService);
				ui.setConnection(conn);
				if (ui.run() == false)
					break;
			}
			else if(str.equals("5"))	break;
			else System.out.println("invalid operation");
		}
	}
}
