package br.com.inserts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import br.com.database.ConnectionDB;

public class SimpleInsert {
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private ResultSetMetaData rsmd;
	private String sql;

	public boolean insert(ConnectionDB condb, String TABLE, String[] fields, Object[] values){
		boolean conditional = false;		
		con = condb.openConnectionDatabase();
		if(values == null) {
			return false;
		}
		
		String val = "";
		for(int i = 0; i < values.length; i++) {
			if (i == (values.length - 1)) {
				val += "'" + values[i] + "'";
			} else {
				
				val += "'" + values[i] + "'" + ", ";
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
			ps.execute();
			conditional = true;
		} catch (SQLException e) {
			e.printStackTrace();
			conditional = false;
		}
		return conditional;
	}
}
