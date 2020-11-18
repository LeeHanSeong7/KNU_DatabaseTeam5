package ui.user;

import java.sql.Connection;
import java.sql.Date;
import java.util.Scanner;

import injected.DIContainer.Services;
import pd.interfaces.AuthenticationService;
import pd.model.AccountDTO;
import ui.admin.AdminMovieManageUI;

public class UserAccountUI {
	private AuthenticationService AuthService;
	private Connection conn;
	public UserAccountUI(AuthenticationService services) {
		AuthService = services;
	}
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	public void run() {
		Scanner scan = new Scanner(System.in);
		while(true) {
			String str;
			System.out.println("-account menu-");
			System.out.println("0.help   1.my info   2.modify   3.delete   4.back");
			str = scan.nextLine();
			str = str.replaceAll(" ", "");
			AccountDTO myinfo = AuthService.getloggedInAccountInfo();
			if (str.equals("0")) {
			}
			else if(str.equals("1")){
				System.out.println("---my info---");
				System.out.println("email_id : "+myinfo.getEmail_id());
				System.out.println("phone_number : "+myinfo.getPhone_number());
				System.out.println("name : "+myinfo.getName());
				System.out.println("address : "+myinfo.getAddress());
				System.out.println("gender : "+myinfo.getGender());
				System.out.println("birth_date : "+myinfo.getBirth_date());
				System.out.println("Job : "+myinfo.getJob());
				System.out.println("membership : "+myinfo.getMembership());
				System.out.println("-------------");
			}
			else if(str.equals("2")){
				while(true) {
					System.out.print("give modify operation, 'column:value'. type 'end' if you want to end : ");
					// isadmin:password??
					str = scan.nextLine();
					if (str.equals("end")) break;
					else
						System.out.println("--result--");
				}
			}
			else if(str.equals("3")){
				System.out.print("really want to delete account? then type your password : ");
				if (myinfo.getPassword().equals(scan.nextLine()))
					System.out.println("do delete");
				else
					System.out.println("password incorrect");
					
			}
			else if(str.equals("4"))	break;
			else System.out.println("invalid operation");
		}
	}
}
