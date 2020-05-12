package br.com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDB {

	public ConnectionDB(String DB_NAME, String DB_USER, String DB_PASSWORD) {
		setDB_NAME(DB_NAME);
		setDB_USER(DB_USER);
		setDB_PASSWORD(DB_PASSWORD);
	}

	private Connection con;
	private String DB_NAME;
	private String DB_USER;
	private String DB_PASSWORD;
	private String sql;
	private PreparedStatement ps;

	public void setDB_NAME(String dB_NAME) {
		DB_NAME = dB_NAME;
	}

	public void setDB_USER(String dB_USER) {
		DB_USER = dB_USER;
	}

	public void setDB_PASSWORD(String dB_PASSWORD) {
		DB_PASSWORD = dB_PASSWORD;
	}

	public Connection openConnectionDatabase() {
		String url = "jdbc:mysql://127.0.0.1:3306/" + DB_NAME;
		try {
			con = DriverManager.getConnection(url, DB_USER, DB_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public void closeConnectionDatabase() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean createDatabase(String DB_NEW) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try {
				con = DriverManager
						.getConnection("jdbc:mysql://127.0.0.1:3306/?user=" + DB_USER + "&password=" + DB_PASSWORD);
				Statement stmt = con.createStatement();
				sql = "CREATE DATABASE " + DB_NEW;
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean deleteDatabase(String DB_NEW) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try {
				con = DriverManager
						.getConnection("jdbc:mysql://127.0.0.1:3306/?user=" + DB_USER + "&password=" + DB_PASSWORD);
				Statement stmt = con.createStatement();
				String sql = "DROP DATABASE " + DB_NEW;
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean createTable(String TABLE, String[] fields) {
		boolean conditional = false;
		con = openConnectionDatabase();

		if (fields == null) {
			return false;
		}

		String fieldsConcat = "";

		for (int i = 0; i < fields.length; i++) {
			if (i == (fields.length - 1)) {
				fieldsConcat += fields[i];
			} else {

				fieldsConcat += fields[i] + ", ";
			}
		}

		try {
			sql = "CREATE TABLE " + TABLE + " (" + fieldsConcat + ")";
			ps = con.prepareStatement(sql);
			ps.execute();
			conditional = true;
		} catch (SQLException e) {
			e.printStackTrace();
			return conditional;
		}
		return conditional;
	}

	public boolean deleteTable(String TABLE) {
		boolean conditional = false;
		con = openConnectionDatabase();

		try {
			sql = "DROP TABLE " + TABLE;
			ps = con.prepareStatement(sql);
			ps.execute();
			conditional = true;
		} catch (SQLException e) {
			e.printStackTrace();
			return conditional;
		}
		return conditional;
	}
}
