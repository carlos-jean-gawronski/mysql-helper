package br.com.tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import br.com.database.ConnectionDB;
import br.com.deletes.SimpleDeletion;
import br.com.inserts.SimpleInsert;
import br.com.main.CredentialReader;
import br.com.selects.SimpleSelection;
import br.com.updates.SimpleUpdate;

public class Tests {
	private static int success = 0;
	private static int failed = 0;
	private static int total = 0;

	private static ConnectionDB condb;
	private static String DB_USER;
	private static String DB_PASSWORD;
	private static String DB_NAME;

	public static void main(String[] args) {
		Properties prop = new Properties();
		CredentialReader cr = new CredentialReader();
		prop = cr.getVariables();
		DB_USER = prop.getProperty("DB_USER");
		DB_PASSWORD = prop.getProperty("DB_PASSWORD");
		DB_NAME = prop.getProperty("DB_NAME");

		DB_NAME += "_testing";

		condb = new ConnectionDB(DB_NAME, DB_USER, DB_PASSWORD);

		createDatabase();
		createTable("users");
		insert("users");
		simpleSelection("users");
		simpleUpdate("users");
		simpleDeletion("users");
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
		String[] fields = { "id int not null auto_increment primary key", "email varchar(255) not null",
				"password varchar(25) not null" };

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
		String[] fields = {"email", "password"};
		Object[] values = {"mark@tes.com", "123" };

		if (si.insert(condb, TABLE, fields, values)) {
			success++;
			System.out.println("Insert in table " + TABLE + ": OK");
		} else {
			failed++;
			System.out.println("Insert in table " + TABLE + ": ERROR");
		}
	}

	public static void simpleSelection(String TABLE) {
		SimpleSelection ss = new SimpleSelection();
		ArrayList<HashMap<String, Object>> al = new ArrayList<HashMap<String, Object>>();
		al = ss.selection(condb, TABLE, null);

		if (al.size() != 0) {
			success++;
			System.out.println("Select on table " + TABLE + ": OK");
		} else {
			failed++;
			System.out.println("Select on table " + TABLE + ": ERROR");
		}
	}

	public static void simpleDeletion(String TABLE) {
		SimpleDeletion sd = new SimpleDeletion();
		if (sd.simpleDeletion(condb, TABLE, "id", "1")) {
			success++;
			System.out.println("Delete on table " + TABLE + ": OK");
		} else {
			failed++;
			System.out.println("Delete on table " + TABLE + ": ERROR");
		}
	}
	public static void simpleUpdate(String TABLE) {
		SimpleUpdate su = new SimpleUpdate();
		String[] fields = {"password"};
		String[] values = {"321"};
		if (su.update(condb, TABLE, fields, values, "id", "1")) {
			success++;
			System.out.println("Update on table " + TABLE + ": OK");
		} else {
			failed++;
			System.out.println("Update on table " + TABLE + ": ERROR");
		}
	}
}
