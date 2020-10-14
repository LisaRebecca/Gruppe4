package controller;

import javax.swing.JTable;

import Tools.AbstractButcherException;

public class MockDatabase extends Database {
	private static Object[][] emptyRowData = { { "###" } };
	private static Object[] emptyColumnNames = { "###" };
	private static JTable emptyTable = new JTable(emptyRowData, emptyColumnNames);

	public MockDatabase() {
		isConnected = false;
	}

	@Override
	public JTable executeDBQuery(String select_statement) {
		return emptyTable;
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