package ui.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import injected.DIContainer.Services;
import pd.interfaces.AuthenticationService;
import pd.interfaces.MovieService;

public class AdminUI {
	private AuthenticationService AuthService;
	private MovieService MovieService;
	private Connection conn;
	public AdminUI(Services services) {
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
			System.out.println("-admin menu-");
			System.out.println("0.help   1.movie manage   2.check ratings   3.sign out");
			str = scan.nextLine();
			str = str.replaceAll(" ", "");
			if (str.equals("0")) {
			}
			else if(str.equals("1")){
				AdminMovieManageUI ui = new AdminMovieManageUI(MovieService);
				ui.setConnection(conn);
				ui.run();
			}
			else if(str.equals("2")){ // 다이나믹하게 개선 여지
				String movieName = "";
				String Email = "";
				double Maxstars = 10;
				double Minstars = 0;
				while(true) {
					System.out.println("--condition lists--");
					System.out.println("target list");
					System.out.println("movieName   	: " + movieName);
					System.out.println("Email 	    	: " + Email);
					System.out.println("maxstars(0~10) 	: " + Maxstars);
					System.out.println("minstars(0~10) 	: " + Minstars);
					System.out.println("-------------------");
					System.out.print("give condition ' target:=value ', type 'end' if you want result : ");
					str = scan.nextLine();
					if (str.equals("end")) break;
					try {
						String[] temp = str.split(":=");
						if (temp[0].equals("movieName"))
							movieName = movieName+", "+temp[1];
						else if (temp[0].equals("Email"))
							Email = Email+", "+temp[1];
						else if (temp[0].equals("maxstars")) {
							try {
								Maxstars = Double.parseDouble(temp[1]);
								if (Maxstars > 10 || Maxstars < 0)
									System.out.println("***invalid operation, ' maxstars:=0~10 '***");
							}
							catch(Exception e) {
								System.out.println("***invalid operation, ' maxstars:=0~10 '***");
							}
						}
						else if (temp[0].equals("minstars")) {
							try {
								Minstars = Double.parseDouble(temp[1]);
								if (Minstars > 10 || Minstars < 0)
									System.out.println("***invalid operation, ' minstars:=0~10 '***");
							}
							catch(Exception e) {
								System.out.println("***invalid operation, ' minstars:=0~10 '***");
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
			else if(str.equals("3"))	break;
			else System.out.println("invalid operation");
		}
	}
}
