package controller;

import javax.swing.JTable;

public class MockDatabase extends Database {
	private static Object[][] emptyRowData = {{"###"}};
	private static Object[] emptyColumnNames = {"###"};
	private static JTable emptyTable = new JTable(emptyRowData, emptyColumnNames);
	@Override
	public JTable executeDBQuery(String select_statement) {
		return emptyTable;
	}

	@Override
	public void executeDBInsert(String insert_statement) {
		
	}
}
