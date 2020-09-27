package controller;

import java.sql.SQLException;

import javax.swing.JTable;

public abstract class Database {
	private static Database database = new MockDatabase();

	public static Database get() {
		return Database.database;
	}

	public static void set(Database database) {
		Database.database = database;
	}
	
	public abstract JTable executeDBQuery(String select_statement) throws SQLException;
	public abstract void executeDBInsert(String insert_statement) throws SQLException;
}