package ui;

import java.sql.*;
import java.util.*;

import injected.DIContainer;
import injected.DIContainer.Services;
import pd.utils.Result;

public class KnuMovie {
	public static final String URL = "jdbc:oracle:thin:@localhost:32769:orcl";
	//public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	public static String default_db_name = "KnuMovie";
	public static String default_db_pwd = "knu";
	private Services services;

	public KnuMovie(DIContainer diContainer){
		services = diContainer.services;
	}	

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
		
		while(true) {
			String str;
			System.out.println("-menu-");
			System.out.println("0.help   1.sign in   2.sign up   3.exit");
			str = scan.nextLine();
			str = str.replaceAll(" ", "");
			if (str.equals("0")) {
				System.out.println("����");
			}
			else if (str.equals("1")) {
				Result result = services.authenticationService.login("id", "password");
				System.out.println(result.toString());
				// ���⼭ ��ü ȣ��
			}
			else if (str.equals("2")) {
				//Result result = services.authenticationService.signUp("id", "password", new accountDTO(null, null, null, ...));
				System.out.println("ȸ������");
				// ���⼭ ��ü ȣ��
			}
			else if (str.equals("3")) {
				System.out.println("����");
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
