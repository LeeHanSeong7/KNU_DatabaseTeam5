package ui;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import config.DBConfig;
import injected.DIContainer;
import injected.DIContainer.Services;
import pd.model.AccountDTO;
import pd.utils.Result;
import ui.admin.AdminUI;
import ui.user.UserUI;

public class KnuMovie {
	private Services services;
	private DBConfig dbConfig;
	public KnuMovie(DIContainer diContainer, DBConfig dbConfig){
		services = diContainer.services;
		this.dbConfig = dbConfig;
	}	
	public void run() {
		Connection conn = dbConfig.connection;

		Scanner scan = new Scanner(System.in);
		System.out.println("-----KnuMovie-----\r\n");

		//connection set
		services.authenticationService.setConnection(conn);
		services.movieService.setConnection(conn);
		services.ratingService.setConnection(conn);
		while(true) {
			String str;
			System.out.println("-title menu-");
			System.out.println("1.sign in   2.sign up   3.exit");
			str = scan.nextLine();
			str = str.replaceAll(" ", "");
			if (str.equals("0")) {
			}
			else if (str.equals("1")) {
				String id;
				String password;
				System.out.print("ID(EMAIL)	: ");
				id = scan.nextLine();
				System.out.print("PASSWORD	: ");
				password = scan.nextLine();
				Result result = services.authenticationService.login(id, password);
				if (result == Result.failure)
					System.out.println(result.getError().getDescription());
				else {
					AccountDTO accountInfo = services.authenticationService.getloggedInAccountInfo();
					if (accountInfo.getIsAdmin()) {
						AdminUI ui = new AdminUI(services);
						ui.setConnection(conn);
						ui.run();
					}
					else {
						UserUI ui = new UserUI(services);
						ui.setConnection(conn);
						ui.run();
					}
				}
			}
			else if (str.equals("2")) {
				AccountDTO newbee = AccountUITool.makeAccountDTO(true);
				if (newbee != null) {
					//newbee = accountMaker.fillNullwithDefault(newbee, false); fixed!
					newbee.setMembership("basic");
					newbee.setIsAdmin(false);
					Result result = services.authenticationService.signUp(newbee.getEmail_id(), newbee.getPassword(), newbee);
				    if (result == Result.success) {
						System.out.println("sign up is done! please log in.");
				    }
				    else {
				    	System.out.println(result.getError().getDescription());
				    }
			    }
			}
			else if (str.equals("3")) {
				System.out.println("***program exited.***");
				break;
			}
			else System.out.println("invalid operation");
		}
		
		//end program
		scan.close();
		try {
			conn.close();
		}catch(SQLException ex) {
			System.err.println("Cannot close" + ex.getMessage());
			System.exit(1);
		}
	}
}
