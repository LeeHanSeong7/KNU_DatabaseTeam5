package ui.admin;

import java.sql.Connection;
import java.sql.Date;
import java.util.Scanner;

import injected.DIContainer.Services;
import pd.interfaces.MovieService;
import pd.model.AccountDTO;

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
				String movieID = "";
				String movieName = "";
				Date Maxyear= Date.valueOf("2500-12-21");
				Date Minyear= Date.valueOf("1000-01-01");
				double Maxaver = 10;
				double Minaver = 0;
				double Maxtime = 0;
				double Mintime = 0;
				String genre = "";
				String actor = "";
				String type = "";
				
				while(true) {
					System.out.println("--condition lists--");
					System.out.println("target list");
					System.out.println("1.movieID   		: " + movieID);
					System.out.println("2.movieName   		: " + movieName);
					System.out.println("3.genre 	    		: " + genre);
					System.out.println("4.actor 	    		: " + actor);
					System.out.println("5.type 	    		: " + type);
					System.out.println("6.maxyear(yyyy-mm-dd) : " + Maxyear.toString());
					System.out.println("7.minyear(yyyy-mm-dd) : " + Minyear.toString());
					System.out.println("8.maxaver(0~10) 		: " + Maxaver);
					System.out.println("9.minaver(0~10) 		: " + Minaver);
					System.out.println("10.maxtime 			: " + Maxtime);
					System.out.println("11.mintime 			: " + Mintime);
					System.out.println("-------------------");
					System.out.print("give condition ' ex) 1:=name ', type 'end' if you want result : ");
					str = scan.nextLine();
					if (str.equals("end")) break;
					try {
						String[] temp = str.split(":=");
						if (temp[0].equals("1"))
							movieID = movieID+", "+temp[1];
						else if (temp[0].equals("2"))
							movieName = movieName+", "+temp[1];
						else if (temp[0].equals("3"))
							genre = genre+", "+temp[1];
						else if (temp[0].equals("4"))
							actor = actor+", "+temp[1];
						else if (temp[0].equals("5"))
							type = type+", "+temp[1];
						else if (temp[0].equals("6")) {
							try {
								Maxyear = Date.valueOf(temp[1]);
							}
							catch(Exception e) {
								System.out.println("***invalid operation, ' maxyear:=yyyy-mm-dd '***");
							}
						}
						else if (temp[0].equals("7")) {
							try {
								Minyear = Date.valueOf(temp[1]);
							}
							catch(Exception e) {
								System.out.println("***invalid operation, ' minyear:=yyyy-mm-dd '***");
							}
						}
						else if (temp[0].equals("8")) {
							try {
								Maxaver = Double.parseDouble(temp[1]);
							}
							catch(Exception e) {
								System.out.println("***invalid operation, ' maxaver:=0~10 '***");
							}
						}
						else if (temp[0].equals("9")) {
							try {
								Minaver = Double.parseDouble(temp[1]);
							}
							catch(Exception e) {
								System.out.println("***invalid operation, ' minaver:=0~10 '***");
							}
						}
						else if (temp[0].equals("10")) {
							try {
								Maxaver = Double.parseDouble(temp[1]);
							}
							catch(Exception e) {
								System.out.println("***invalid operation, ' maxtime:='num' '***");
							}
						}
						else if (temp[0].equals("11")) {
							try {
								Minaver = Double.parseDouble(temp[1]);
							}
							catch(Exception e) {
								System.out.println("***invalid operation, ' mintime:='num' '***");
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
