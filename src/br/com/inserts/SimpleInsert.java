package br.com.inserts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.main.ConnectionDB;

public class SimpleInsert {
	
	private ConnectionDB conn = new ConnectionDB();
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;

	public String insert(String TABLE, String DB_NAME, String DB_USER, String DB_PASSWORD, String[] fields, String[] values){
		try {
			con = conn.openConnectionDatabase(DB_NAME, DB_USER, DB_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(values == null) {
			return "Missing values field";
		}
		
		String val = "";
		for(int i = 0; i < values.length; i++) {
			if (i == (values.length - 1)) {
				val += values[i];
			} else {
				
				val += values[i] + ", ";
			}
		}
		if(fields == null) {
			sql = "insert into " + TABLE + " values (" + val + ")";
		} else {
			String flds = "";
			for(int i = 0; i < fields.length; i++) {
				if (i == (fields.length - 1)) {
					flds += fields[i];
				} else {
					
					flds += fields[i] + ", ";
				}
			}
			sql = "insert into " + TABLE + " (" + flds + ") values (" + val + ")";
		}
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Success inserting into table " + TABLE;
	}
}
