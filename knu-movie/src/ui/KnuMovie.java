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
	//connection Temporary
	
	public void run() {
		Connection conn = dbConfig.connection;

		Scanner scan = new Scanner(System.in);
		System.out.println("-----KnuMovie-----\r\n");

		//connection set
		services.authenticationService.setConnection(conn);
		while(true) {
			String str;
			System.out.println("-title menu-");
			System.out.println("0.help   1.sign in   2.sign up   3.exit");
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
			    String email_id;
			    String password;
			    String phone_number;
			    String name;
			    String address;
			    String gender;
			    Date birth_date;
			    String job;
			    String membership;
		   
				System.out.println("---insert mandatory information---");
				System.out.print("id(email) : ");
				email_id = scan.nextLine();
				while(true) {
					System.out.print("password : ");
					String temp = scan.nextLine();
					System.out.print("re-password : ");
					password = scan.nextLine();
					if (password.equals(temp))
						break;
					else
						System.out.println("both passwords is not same, try again");
				}
				System.out.print("phone number : ");
				phone_number = scan.nextLine();
				System.out.print("name : ");
				name = scan.nextLine();
				System.out.println("----------------------------------");
			    AccountDTO newbee = new AccountDTO(email_id, password, phone_number, name, null, null, null, null, "basic", false);
					
				//Result result = services.authenticationService.signUp("id", "password", new accountDTO(null, null, null, ...));
			    if (true == true) {
					System.out.println("sign up is done! please log in and give us more informaions (address, gender, birth_date, job)");
			    }
			    else {}
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
