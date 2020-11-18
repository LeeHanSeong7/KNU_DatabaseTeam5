package ui;

import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

import injected.DIContainer;
import injected.DIContainer.Services;
import pd.model.AccountDTO;
import pd.services.DefaultAuthenticationService;
import pd.utils.Result;
import ui.admin.AdminUI;
import ui.user.UserUI;

public class KnuMovie {
	//public static final String URL = "jdbc:oracle:thin:@localhost:32769:orcl";
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	public static String default_db_name = "KnuMovie";
	public static String default_db_pwd = "knu";
	private Services services;
	private DefaultAuthenticationService de;
	public KnuMovie(DIContainer diContainer){
		services = diContainer.services;
	}	
	//connection Temporary
	
	public void run() {
		String db_name = "";
		String db_pwd = "";
		Connection conn = null;

		Scanner scan = new Scanner(System.in);
		System.out.println("-----KnuMovie-----\r\n");
		
		while(true) {
			System.out.println("DB name(insert default'"+default_db_name+"' if you give blank) : ");
			db_name = scan.nextLine();
			if (db_name.replaceAll(" ","").equals("")) {
				db_name = default_db_name;
				db_pwd = default_db_pwd;
			}
			else {
				System.out.println("DB password : ");
				db_pwd = scan.nextLine();
			}
			// try to connect
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch(ClassNotFoundException e) {
				System.err.println("error = " + e.getMessage());
				System.exit(1);
			}
			
			try {
				conn = DriverManager.getConnection(URL,  db_name, db_pwd);
				conn.setAutoCommit(false);
			}catch(SQLException ex) {
				System.out.println("err)Cannot get a connection : " + ex.getMessage());
				continue;
			}
			break;
		}	
		//connection set
		de = (DefaultAuthenticationService)services.authenticationService;
		de.setConnection(conn);
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
