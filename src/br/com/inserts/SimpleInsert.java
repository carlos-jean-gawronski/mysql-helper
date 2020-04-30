package br.com.inserts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SimpleInsert {

	public SimpleInsert(Connection dbConnection) {
		this.setCon(dbConnection);
	}
	private Connection con;
	public PreparedStatement ps;
	public ResultSet rs;
	public String sql;
	
	public Connection getCon() {
		return con;
	}
	
	public void setCon(Connection con) {
		this.con = con;
	}

	public void insert(String TABLE) throws SQLException{
		sql = "insert into ? ";
		
	}
}
