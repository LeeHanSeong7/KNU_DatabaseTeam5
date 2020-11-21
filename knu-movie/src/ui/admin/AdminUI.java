package ui.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import injected.DIContainer.Services;
import pd.interfaces.AuthenticationService;
import pd.interfaces.MovieService;
import pd.interfaces.RatingService;
import pd.model.MyRatingVO;
import pd.model.RatingSearchConditionDTO;
import pd.model.RatingVO;
import pd.model.RatingVOList;
import pd.utils.Result;
import ui.SearchUITool;

public class AdminUI {
	private AuthenticationService AuthService;
	private MovieService MovieService;
	private RatingService ratingService;
	private Connection conn;
	public AdminUI(Services services) {
		AuthService = services.authenticationService;
		MovieService = services.movieService;
		ratingService = services.ratingService;
	}
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	public void run() {
		Scanner scan = new Scanner(System.in);
		while(true) {
			String str;
			System.out.println("-admin menu-");
			System.out.println("0.help   1.movie manage   2.check ratings   3.sign out");
			str = scan.nextLine();
			str = str.replaceAll(" ", "");
			if (str.equals("0")) {
			}
			else if(str.equals("1")){
				AdminMovieManageUI ui = new AdminMovieManageUI(MovieService);
				
				ui.setConnection(conn);
				ui.run();
			}
			else if(str.equals("2")){				
				Result result;
				RatingSearchConditionDTO condi = SearchUITool.makeRatingSearchCondition();
				String movieName = condi.movieName.replaceAll(" ", "").length()==0 ? null:condi.movieName;
				String email = condi.Email;
				
				System.out.println("--result--");			
				if (email.replaceAll(" ", "").length() == 0) {
					result = ratingService.getUserRatingListWith(movieName, null, condi.Maxstars, condi.Minstars);
					if (result ==  Result.success) {
						List<RatingVOList> movieList = (List<RatingVOList>)result.getValue();
						for (int i = 0 ; i<movieList.size();i++) {
							RatingVOList movieItem = movieList.get(i);
							List<RatingVO> ratingList = movieItem.ratings;
							System.out.println("<"+i+">"+movieItem.title+":");
							for (int j = 0 ; j < ratingList.size();j++) {
								RatingVO item = ratingList.get(j);
								System.out.print(String.format("%d. (%.1f)", j , item.rating));
								int k;
								for(k = 1; k<=item.rating;k++) {
									System.out.print("*");
								}
								for(; k<=10;k++) {
									System.out.print("-");
								}
								System.out.println(" ["+item.emailId+"] ");
							}
						}
					}
				}
				else {
					String[] list = SearchUITool.NoRedundantSplit(email);
					for(int index=0; index<list.length;index++) {
						result = ratingService.getUserRatingListWith(movieName, list[index], condi.Maxstars, condi.Minstars);
						if (result == Result.success) {
							List<RatingVOList> movieList = (List<RatingVOList>)result.getValue();
							for (int i = 0 ; i<movieList.size();i++) {
								RatingVOList movieItem = movieList.get(i);
								List<RatingVO> ratingList = movieItem.ratings;
								System.out.println("<"+i+">"+movieItem.title+":");
								for (int j = 0 ; j < ratingList.size();j++) {
									RatingVO item = ratingList.get(j);
									System.out.print(String.format("%d. (%.1f)", j , item.rating));
									int k;
									for(k = 1; k<=item.rating;k++) {
										System.out.print("*");
									}
									for(; k<=10;k++) {
										System.out.print("-");
									}
									System.out.println(" ["+item.emailId+"] ");
								}
							}
						}
					}
				}
			}
			else if(str.equals("3"))	break;
			else System.out.println("invalid operation");
		}
	}
}
