package ui.user;

import java.sql.Connection;
import java.sql.Date;
import java.util.Scanner;

import injected.DIContainer.Services;
import pd.interfaces.AuthenticationService;
import pd.interfaces.MovieService;
import pd.utils.Result;

public class UserUI {
	private AuthenticationService AuthService;
	private MovieService MovieService;
	private Connection conn;
	
	public UserUI(Services services) {
		AuthService = services.authenticationService;
		MovieService = services.movieService;
	}
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	public void run() {
		Scanner scan = new Scanner(System.in);
		while(true) {
			String str;
			System.out.println("-user menu-");
			System.out.println("0.help   1.search   2.rate   3.my ratings   4.account   5.sign out");
			str = scan.nextLine();
			str = str.replaceAll(" ", "");
			if (str.equals("0")) {
			}
			else if(str.equals("1")){

				String movieName = "";
				Date Maxyear= Date.valueOf("2500-12-21");
				Date Minyear= Date.valueOf("1000-01-01");
				double Maxaver = 10;
				double Minaver = 0;
				String genre = "";
				String actor = "";
				String type = "";
				
				while(true) {
					System.out.println("--condition lists--");
					System.out.println("target list");
					System.out.println("1.movieName   		: " + movieName);
					System.out.println("2.genre 	    	: " + genre);
					System.out.println("3.actor 	    	: " + actor);
					System.out.println("4.type 	    		: " + type);
					System.out.println("5.maxyear(yyyy-mm-dd) : " + Maxyear.toString());
					System.out.println("6.minyear(yyyy-mm-dd) : " + Minyear.toString());
					System.out.println("7.maxaver(0~10) 	: " + Maxaver);
					System.out.println("8.minaver(0~10) 	: " + Minaver);
					System.out.println("-------------------");
					System.out.print("give condition ' ex) 1:=name ', type 'end' if you want result : ");
					str = scan.nextLine();
					if (str.equals("end")) break;
					try {
						String[] temp = str.split(":=");
						if (temp[0].equals("1"))
							movieName = movieName+", "+temp[1];
						else if (temp[0].equals("2"))
							genre = genre+", "+temp[1];
						else if (temp[0].equals("3"))
							actor = actor+", "+temp[1];
						else if (temp[0].equals("4"))
							type = temp[1];
						else if (temp[0].equals("5")) {
							try {
								Maxyear = Date.valueOf(temp[1]);
							}
							catch(Exception e) {
								System.out.println("***invalid operation, ' maxyear:=yyyy-mm-dd '***");
							}
						}
						else if (temp[0].equals("6")) {
							try {
								Minyear = Date.valueOf(temp[1]);
							}
							catch(Exception e) {
								System.out.println("***invalid operation, ' minyear:=yyyy-mm-dd '***");
							}
						}
						else if (temp[0].equals("7")) {
							try {
								Maxaver = Double.parseDouble(temp[1]);
							}
							catch(Exception e) {
								System.out.println("***invalid operation, ' maxaver:=0~10 '***");
							}
						}
						else if (temp[0].equals("8")) {
							try {
								Minaver = Double.parseDouble(temp[1]);
							}
							catch(Exception e) {
								System.out.println("***invalid operation, ' minaver:=0~10 '***");
							}
						}
						else 
							System.out.println("***invalid operation, ' target:=value ' or 'end'***");
					}
					catch(Exception e) {
						System.out.println("***invalid operation, ' target:=value ' or 'end'***");
					}
				}
				System.out.println("--result--");
			}
			else if(str.equals("2")){
				String movieName;
				double stars;
				System.out.print("movie name : ");
				movieName = scan.nextLine();
				System.out.print("stars(0~10) : ");
				try {
					stars = Double.parseDouble(scan.nextLine());
				}
				catch(Exception e) {
					System.out.println("wrong format");
					continue;
				}
			}
			else if(str.equals("3")){
				System.out.println("--result--");
				//show my rating list
			}
			else if(str.equals("4")){
				UserAccountUI ui = new UserAccountUI(AuthService);
				ui.setConnection(conn);
				if (ui.run() == false)
					break;
			}
			else if(str.equals("5"))	break;
			else System.out.println("invalid operation");
		}
	}
}
