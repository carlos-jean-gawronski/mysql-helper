//package br.com.main;
//
//import java.util.Scanner;
//
//import javax.swing.JFrame;
//
//import br.com.database.ConnectionDB;
//
//public class Main extends JFrame {
//	public static ConnectionDB con;
//
//	public static void main(String[] args) {
//
//		System.out.println("Welcome to MySQL Helper!!!");
//		operation();
//	}
//
//	public static void operation() {
//		System.out.println("Choose an option to operate with your MySQL server:");
//
//		String[] methods = { "Select", "Update", "Delete", "Insert", "Create", "Drop" };
//
//		for (int i = 0; i < methods.length; i++) {
//			System.out.println((i + 1) + " - " + methods[i]);
//		}
//
//		Scanner s = new Scanner(System.in);
//		int a = Integer.parseInt(s.nextLine());
//		a--;
//
//		switch (a) {
//		case 0: {
//			System.out.println("What fields do you want to select?");
//			
//		}
//		case 4: {
//			System.out.println("What do you want to create?");
//			String[] options = { "Database", "Table" };
//			for (int i = 0; i < options.length; i++) {
//				System.out.println((i + 1) + " - " + options[i]);
//			}
//
//			Scanner s1 = new Scanner(System.in);
//			int a1 = Integer.parseInt(s1.nextLine());
//			if (a1 == 1) {
//				System.out.println("Type the server user:");
//				Scanner user = new Scanner(System.in);
//				String userString = user.nextLine();
//
//				System.out.println("Type the server password:");
//				Scanner password = new Scanner(System.in);
//				String passwordString = password.nextLine();
//
//				System.out.println("Type the database name:");
//				Scanner database = new Scanner(System.in);
//				String databaseString = database.nextLine();
//
//				con = new ConnectionDB(databaseString, userString, passwordString);
//				boolean result = con.createDatabase(databaseString);
//				System.out.println(result);
//			}
//
//			else if (a1 == 2) {
//				
//			}
//		}
//		default: {
//			System.out.println("Select a possible option!");
//		}
//		}
//
//	}
//
//}
