package br.com.inserts;

import java.sql.Connection;
import java.sql.PreparedStatement;

import br.com.database.ConnectionDB;

public class SimpleInsert {

	private Connection con;
	private PreparedStatement ps;
	private String sql;

	public boolean insert(ConnectionDB condb, String TABLE, String[] fields, Object[] values) {
		boolean conditional = false;
		con = condb.openConnectionDatabase();
		if (values == null) {
			return false;
		}

		String valuesSanitized = new String("");

		for (int i = 0; i < values.length; i++) {
			if (i == values.length - 1) {
				valuesSanitized += "\"" + values[i] + "\"";
			} else {
				valuesSanitized += "\"" + values[i] + "\", ";
			}
		}

		String select = "";
		if (fields != null) {
			for (int i = 0; i < fields.length; i++) {
				if (i == (fields.length - 1)) {
					select += fields[i];
				} else {

					select += fields[i] + ", ";
				}
			}
		}

		if (select.length() != 0) {
			sql = "INSERT INTO " + TABLE + "(" + select + ") VALUES (" + valuesSanitized + ")";
		} else {
			sql = "INSERT INTO " + TABLE + " VALUES (" + valuesSanitized + ")";
		}

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
