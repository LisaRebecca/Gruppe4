package data;

import java.sql.ResultSet;
import errorhandling.AbstractButcherException;
import errorhandling.SQLButcherException;

public abstract class Database {
	private static Database database;
	public boolean isConnected = false;

	public static Database get() {
		return Database.database;
	}

	public static void set(Database database) {
		Database.database = database;
	}
	
	public abstract void establishConnection() throws  AbstractButcherException;
	public abstract ResultSet executeDBQuery(String select_statement) throws SQLButcherException;
	public abstract void executeDBInsert(String insert_statement) throws SQLButcherException ;
}