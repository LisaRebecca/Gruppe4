package data;

import java.sql.ResultSet;

import javax.swing.JTable;

import controller.login.LoginController;
import errorhandling.AbstractButcherException;

public class MockDatabase extends Database {
	private static Object[][] emptyRowData = { { "###" } };
	private static Object[] emptyColumnNames = { "###" };
	private static JTable emptyTable = new JTable(emptyRowData, emptyColumnNames);

	public MockDatabase() {
		isConnected = false;
	}

	@Override
	public ResultSet executeDBQuery(Select_Statements stmt) {
		return (ResultSet) emptyTable;
	}

	@Override
	public void executeDBInsert(String insert_statement) {

	}

	@Override
	public void establishConnection() throws AbstractButcherException {
		isConnected = true;
		LoginController.get().giveControl();
	}
}