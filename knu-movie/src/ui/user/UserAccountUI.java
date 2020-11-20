package ui.user;

import java.sql.Connection;
import java.sql.Date;
import java.util.Scanner;

import injected.DIContainer.Services;
import pd.interfaces.AuthenticationService;
import pd.model.AccountDTO;
import pd.utils.Result;
import ui.AccountUITool;
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
	public Boolean run() {
		Scanner scan = new Scanner(System.in);
		while(true) {
			String str;
			System.out.println("-account menu-");
			System.out.println("0.help   1.my info   2.change password   3.modify info   4.delete   5.back");
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
				System.out.print("type new password : ");
				String password = scan.nextLine();
				System.out.print("one more : ");
				if (!password.equals(scan.nextLine())) {
					System.out.println("two passwords is not same");
					continue;
				}
				System.out.print("type your current password : ");
				Result result = AuthService.changePassword(myinfo.getEmail_id(),scan.nextLine(), password);
				if (result == Result.success) {
					System.out.println("password changed");
			    }
			    else {
			    	System.out.println(result.getError().getDescription());
			    }
			}
			else if(str.equals("3")){
				AccountUITool accountMaker = new AccountUITool();
				AccountDTO changed = accountMaker.makeAccountDTO(false);
				if (changed != null) {
					changed = accountMaker.fillNullwithOther(changed, myinfo);
					changed.setEmail_id(null);changed.setPassword(null);
					
					Result result = AuthService.changeAccountInfo(myinfo.getEmail_id(), myinfo.getPassword(), changed);
				    if (result == Result.success) {
						System.out.println("information changed");
				    }
				    else {
				    	System.out.println(result.getError().getDescription());
				    }
			    }
			}
			else if(str.equals("4")){
				System.out.print("really want to delete account? then type your password : ");
				String str1 = scan.nextLine();
				System.out.print("one more : ");
				String str2 = scan.nextLine();
				Result result = AuthService.deleteAccount(myinfo.getEmail_id(), str1, str2);
				if (result == Result.success) {
					System.out.println("account deleted");
					return false;
			    }
			    else {
			    	System.out.println(result.getError().getDescription());
			    }
			}
			else if(str.equals("5"))	break;
			else System.out.println("invalid operation");
		}
		return true;
	}
}
