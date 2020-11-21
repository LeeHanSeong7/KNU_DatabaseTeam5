package ui.admin;

import java.sql.Connection;
import java.sql.Date;
import java.util.Scanner;

import injected.DIContainer.Services;
import pd.interfaces.MovieService;
import pd.model.AccountDTO;
import pd.model.MovieSearchConditionDTO;
import ui.SearchUITool;

public class AdminMovieManageUI {
	private Connection conn;
	private MovieService MovieService;
	
	public AdminMovieManageUI(MovieService services) {
		MovieService = services;
	}
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	public void run() {
		Scanner scan = new Scanner(System.in);
		while(true) {
			String str;
			System.out.println("-movie manage menu-");
			System.out.println("0.help   1.search   2.upload   3.modify   4.delete   5.back");
			str = scan.nextLine();
			str = str.replaceAll(" ", "");
			if (str.equals("0")) {
			}
			else if(str.equals("1")){
				MovieSearchConditionDTO condition = SearchUITool.makeMovieSearchCondition();
				System.out.println("--result--");
			}
			else if(str.equals("2")){
				String title_id;
			    String type;
			    double runtime;
			    Date start_year;
		   
				System.out.println("---insert information---");
				System.out.print("title_id : ");
				title_id = scan.nextLine();
				System.out.print("type : ");
				type = scan.nextLine();
				System.out.print("runtime :");
				try {
					runtime = Double.parseDouble(scan.nextLine());
				}
				catch(Exception e) {
					System.out.println("type error");
					continue;
				}
				System.out.print("start_year (yyyy-mm-dd) :");
				try {
					start_year = Date.valueOf(scan.nextLine());
				}
				catch(Exception e) {
					System.out.println("type error");
					continue;
				}
				//Result result = services.authenticationService.signUp("id", "password", new accountDTO(null, null, null, ...));
			    if (true == true) {
					System.out.println("upload complete!");
			    }
			    else {}
			}
			else if(str.equals("3")){
				System.out.print("give movie id for modify :");
				while(true) {
					System.out.print("give modify operation, 'column:value'. type 'end' if you want to end : ");
					str = scan.nextLine();
					if (str.equals("end")) break;
					else
						System.out.println("--result--");
				}
			}
			else if(str.equals("4")){
				System.out.print("give movie id for delete :");
				str = scan.nextLine();
				System.out.println("--result--");
			}
			else if(str.equals("5"))	break;
			else System.out.println("invalid operation");
		}
	}
}
