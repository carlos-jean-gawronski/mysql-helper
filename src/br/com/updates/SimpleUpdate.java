package br.com.updates;

import java.sql.Connection;
import java.sql.PreparedStatement;

import br.com.database.ConnectionDB;

public class SimpleUpdate {
	private Connection con;
	private String sql = "";
	private PreparedStatement ps;

	public boolean update(ConnectionDB conn, String TABLE, String[] fields, Object[] values, String WHERE,
			String WHERE_VALUE) {
		boolean conditional = false;
		con = conn.openConnectionDatabase();
		if (values == null || fields == null) {
			return false;
		}

		if (values.length != fields.length) {
			return false;
		}
		String set = "";

		for (int i = 0; i < fields.length; i++) {
			if (i == fields.length - 1) {
				set += fields[i] + " = " + values[i];
			} else {
				set += fields[i] + " = " + values[i] + ", ";
			}
		}
		sql = "UPDATE " + TABLE + " SET " + set + " WHERE " + WHERE + " = " + WHERE_VALUE;

		try {
			ps = con.prepareStatement(sql);
			ps.execute();
			conditional = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conditional;
	}
}
