package br.com.tests;

import br.com.database.ConnectionDB;
import br.com.inserts.SimpleInsert;

public class Tests {
	private static int success = 0;
	private static int failed = 0;
	private static int total = 0;

	private static ConnectionDB condb;
	private static String DB_USER = "";
	private static String DB_PASSWORD = "";
	private static String DB_NAME = "mysql_helper_tests";

	public static void main(String[] args) {
		condb = new ConnectionDB(DB_NAME, DB_USER, DB_PASSWORD);

		createDatabase();
		createTable("users");
//		insert("users");
//		simpleSelection();
		deleteTable("users");
		deleteDatabase();

		total = success + failed;
		System.out.println("");
		System.out.println("----------------------------------");
		System.out.println("Success: " + success);
		System.out.println("Failed: " + failed);
		System.out.println("Total: " + total);
	}

	public static void createDatabase() {
		if (condb.createDatabase(DB_NAME)) {
			success++;
			System.out.println("Create database " + DB_NAME + ": OK");
		} else {
			failed++;
			System.out.println("Create database " + DB_NAME + ": ERROR");
		}
	}

	public static void deleteDatabase() {
		if (condb.deleteDatabase(DB_NAME)) {
			success++;
			System.out.println("Delete database " + DB_NAME + ": OK");
		} else {
			failed++;
			System.out.println("Delete database " + DB_NAME + ": ERROR");
		}
	}

	public static void createTable(String NEW_TABLE) {
		String[] fields = {"id int not null auto_increment primary key", "email varchar(255) not null", "password varchar(25) not null"};
		
		if (condb.createTable(NEW_TABLE, fields)) {
			success++;
			System.out.println("Create table " + NEW_TABLE + ": OK");
		} else {
			failed++;
			System.out.println("Create table " + NEW_TABLE + ": ERROR");
		}
	}
	
	public static void deleteTable(String NEW_TABLE) {
		if (condb.deleteTable(NEW_TABLE)) {
			success++;
			System.out.println("Delete table " + NEW_TABLE + ": OK");
		} else {
			failed++;
			System.out.println("Delete table " + NEW_TABLE + ": ERROR");
		}
	}
	
	public static void insert(String TABLE) {
		SimpleInsert si = new SimpleInsert();
		String[] fields = {"id", "email", "password"};
		String[] values = {null, "mark@tes.com", "123"};
		
		if (si.insert(condb, TABLE, fields, values)) {
			success++;
			System.out.println("Insert in table " + TABLE + ": OK");
		} else {
			failed++;
			System.out.println("Insert in table " + TABLE + ": ERROR");
		}
	}
//	SimpleSelection ss = new SimpleSelection();
//	ArrayList<HashMap<String, Object>> al = new ArrayList<HashMap<String, Object>>();
}
