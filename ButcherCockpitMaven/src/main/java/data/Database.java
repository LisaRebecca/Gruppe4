package data;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;

import errorhandling.AbstractButcherException;

public abstract class Database {
	private static Database database;
	public boolean isConnected = false;

	public static Database get() {
		return Database.database;
	}

	public static void set(Database database) {
		Database.database = database;
	}
	
	public abstract void establishConnection() throws AbstractButcherException;
	public abstract ResultSet executeDBQuery(String select_statement) throws AbstractButcherException;
	public abstract void executeDBInsert(String insert_statement) throws AbstractButcherException ;
}