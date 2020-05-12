package br.com.selects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import br.com.database.ConnectionDB;

public class SimpleSelection {
	private PreparedStatement ps;
	private Connection con;
	private String sql;
	private ArrayList<HashMap<String, Object>> al = new ArrayList<HashMap<String, Object>>();
	private ResultSet rs;
	HashMap<String, Object> prop = new HashMap<String, Object>();
//	private Set sets;
//	private Iterator iter;
	private ResultSetMetaData rsmd;

	public ArrayList<HashMap<String, Object>> selection(ConnectionDB condb, String TABLE, String[] fields) {
		try {
			con = condb.openConnectionDatabase();
			if (fields == null) {
				sql = "SELECT * FROM " + TABLE;
			} else if (fields.length != 0) {
				String select = "";
				for (int i = 0; i < fields.length; i++) {
					if (i == (fields.length - 1)) {
						select += fields[i];
					} else {

						select += fields[i] + ", ";
					}
				}
				sql = "SELECT (" + select + ") FROM " + TABLE;

			}
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			String[] columns = new String[columnCount];

			for (int i = 0; i < columnCount; i++) {
				String tmp = rsmd.getColumnName(i + 1);
				columns[i] = tmp;
			}

			al = new ArrayList<HashMap<String, Object>>();
			while (rs.next()) {
				for (int i = 0; i < columnCount; i++) {
					try {
						prop.put(columns[i], rs.getObject((i + 1)));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				al.add(prop);
				prop = new HashMap<String, Object>();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return al;
	}
}