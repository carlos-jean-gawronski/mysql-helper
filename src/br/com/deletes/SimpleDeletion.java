package br.com.deletes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.database.ConnectionDB;

public class SimpleDeletion {

	private Connection conn;
	private PreparedStatement ps;
	private String sql;
	
	public boolean simpleDeletion(ConnectionDB condb, String TABLE, String FIELD, String VALUE) {
		boolean result = false;
		try {
			conn = condb.openConnectionDatabase();
			
			sql = "DELETE FROM " + TABLE + " WHERE (" + FIELD + " = " + VALUE + ")";
			ps = conn.prepareStatement(sql);
			
			result = ps.execute();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
			return result;
		}
		
		return result;
	}
}
