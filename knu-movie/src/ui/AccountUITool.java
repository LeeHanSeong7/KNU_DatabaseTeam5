package ui;

import java.sql.Date;
import java.util.Scanner;

import pd.model.AccountDTO;
import pd.utils.Result;

public class AccountUITool {
	public AccountUITool() {};
	final String NULLSTR = "";
	public AccountDTO makeAccountDTO(Boolean isFirst) {
		String email_id = null;
	    String password = null;
	    String phone_number = null;
	    String name = null;
	    String address = null;
	    String gender = null;
	    Date birth_date = null;
	    String job = null;
	    String membership = null;
	    Boolean isAdmin = null;

		String temp;
	    Scanner scan = new Scanner(System.in);
	    
		System.out.println("---insert mandatory information---");
		if (isFirst == true) {
			while(true) {
				System.out.print("email(id@domain) : ");
				email_id = scan.nextLine();
				if (!email_id.contains("@")) {
					System.out.println("wrong format(id@domain), try again = 'y' : ");
					temp = scan.nextLine();
					temp = temp.replaceAll(" ", "");
					if (temp.equals("y") || temp.equals("Y"))
						continue;
					else 
						return null;
				}
				else
					 break;
			}
		
			while(true) {
				System.out.print("password : ");
				temp = scan.nextLine();
				
				if (temp.isEmpty()) {
					System.out.println("mandatory info, try again = 'y' : ");
					temp = scan.nextLine();
					temp = temp.replaceAll(" ", "");
					if (temp.equals("y") || temp.equals("Y"))
						continue;
					else 
						return null;
				}
				System.out.print("re-password : ");
				password = scan.nextLine();
				if (!password.equals(temp)) {
					System.out.println("both passwords is not same, try again = 'y' : ");
					temp = scan.nextLine();
					temp = temp.replaceAll(" ", "");
					if (temp.equals("y") || temp.equals("Y"))
						continue;
					else 
						return null;
				}
				else
					 break;
			}
			while(true) {
				System.out.print("phone number : ");
				phone_number = scan.nextLine();
				
				if (phone_number.isEmpty()) {
					System.out.println("mandatory info, try again = 'y' : ");
					temp = scan.nextLine();
					temp = temp.replaceAll(" ", "");
					if (temp.equals("y") || temp.equals("Y"))
						continue;
					else 
						return null;
				}
				else
					 break;
			}
			while(true) {
				System.out.print("name : ");
				name = scan.nextLine();
				
				if (name.isEmpty()) {
					System.out.println("mandatory info, try again = 'y' : ");
					temp = scan.nextLine();
					temp = temp.replaceAll(" ", "");
					if (temp.equals("y") || temp.equals("Y"))
						continue;
					else 
						return null;
				}
				else
					 break;
			}
			System.out.println("-------optional information-------");
		}
		else {
			System.out.print("phone number : ");
			phone_number = scan.nextLine();
			
			System.out.print("name : ");
			name = scan.nextLine();
		}
		System.out.print("address : ");
		address = scan.nextLine();
		
		int input = 0;
		System.out.print("gender (1: male 2: female): ");
		temp = scan.nextLine();
		try {
			input = Integer.parseInt(temp);
			if (input == 1 )
				gender = "M";
			else if (input == 2)
				gender = "F";
			else {
				System.out.println("wrong format, please modify later");
			}
		}
		catch(Exception e) {
			System.out.println("wrong format, please modify later");
		}
		
		System.out.print("birth_date(yyyy-mm-dd) : ");
		temp = scan.nextLine();
		try {
			birth_date = Date.valueOf(temp);
		}
		catch(Exception e) {
			System.out.println("wrong format, please modify later");
		}
		System.out.print("job : ");
		job = scan.nextLine();
		System.out.println("----------------------------------");
		return new AccountDTO(email_id, password, phone_number, name, address, gender, birth_date, job, membership,  isAdmin);
		
	}
	public AccountDTO fillNullwithDefault(AccountDTO data,Boolean isAdmin) {
		AccountDTO temp = data;
		if (data.getEmail_id() == null)
			temp.setEmail_id(NULLSTR);
		if (data.getPassword() == null)
			temp.setPassword(NULLSTR);
		if (data.getPhone_number() == null)
			temp.setPhone_number(NULLSTR);
		if (data.getName() == null)
			temp.setName(NULLSTR);
		if (data.getAddress() == null)
			temp.setAddress(NULLSTR);
		if (data.getGender() == null)
			temp.setGender(NULLSTR);
		if (data.getBirth_date() == null)
			temp.setBirth_date(Date.valueOf("1000-01-01"));
		if (data.getJob() == null)
			temp.setJob(NULLSTR);
		if (data.getMembership() == null) {
			if (isAdmin)
				temp.setMembership("prime");
			else
				temp.setMembership("basic");
		}
		if (data.getIsAdmin() == null) {
			if (isAdmin)
				temp.setIsAdmin(true);
			else
				temp.setIsAdmin(false);
		}
		return temp;
	}
	public AccountDTO fillNullwithOther(AccountDTO data,AccountDTO source) {
		AccountDTO temp = data;
		if (data.getEmail_id() == null)
			temp.setEmail_id(source.getEmail_id());
		if (data.getPassword() == null)
			temp.setPassword(source.getPassword());
		if (data.getPhone_number() == null)
			temp.setPhone_number(source.getPhone_number());
		if (data.getName() == null)
			temp.setName(source.getName());
		if (data.getAddress() == null)
			temp.setAddress(source.getAddress());
		if (data.getGender() == null)
			temp.setGender(source.getGender());
		if (data.getBirth_date() == null)
			temp.setBirth_date(source.getBirth_date());
		if (data.getJob() == null)
			temp.setJob(source.getJob());
		if (data.getMembership() == null)
			temp.setMembership(source.getMembership());
		if (data.getIsAdmin() == null)
			temp.setIsAdmin(source.getIsAdmin());
		return temp;
	}
}
