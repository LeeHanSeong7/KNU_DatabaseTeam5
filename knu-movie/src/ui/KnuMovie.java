package ui;

import java.sql.*;
import java.util.*;

import injected.DIContainer;
import injected.DIContainer.Services;
import pd.services.DefaultAuthenticationService;
import pd.utils.Result;

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
			System.out.println("-menu-");
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
					//adminUI ui = new adminUI(services); in progress
				}
			}
			else if (str.equals("2")) {
				//Result result = services.authenticationService.signUp("id", "password", new accountDTO(null, null, null, ...));
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
