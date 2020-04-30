package br.com.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDB {

	public ConnectionDB() {

	}

	private Connection con;

	public Connection getConn() {
		return con;
	}

	public Connection openConnectionDatabase(String DB_NAME, String DB_USER, String DB_PASSWORD) throws SQLException {
		String url = "jdbc:mysql://127.0.0.1:3306/" + DB_NAME;
		String user = DB_USER;
		String password = DB_PASSWORD;
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("Connection Failed!");
			e.printStackTrace();
		}
		System.out.println("Connection ok!");
		return con;
	}

	public void closeConnectionDatabase() throws SQLException {
		con.close();
	}

	public String createDatabase(String DB_NAME, String DB_USER, String DB_PASSWORD){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try {
				con = DriverManager.getConnection(
						"jdbc:mysql://127.0.0.1:3306/?user=" + DB_USER + "&password=" + DB_PASSWORD);
				Statement stmt = con.createStatement();
				String sql = "CREATE DATABASE " + DB_NAME;
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String a = new String("Database " + DB_NAME + " created!");
		return a;
	}
	
	public String deleteDatabase(String DB_NAME, String DB_USER, String DB_PASSWORD){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try {
				con = DriverManager.getConnection(
						"jdbc:mysql://127.0.0.1:3306/?user=" + DB_USER + "&password=" + DB_PASSWORD);
				Statement stmt = con.createStatement();
				String sql = "DROP DATABASE " + DB_NAME;
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String a = new String("Database " + DB_NAME + " deleted!");
		return a;
	}
}
