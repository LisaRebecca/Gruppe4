package data;

import java.sql.ResultSet;

import javax.swing.JTable;

import controller.login.LoginController;
import errorhandling.AbstractButcherException;

public class MockDatabase extends Database {

	public MockDatabase() {
		isConnected = false;
	}

	@Override
	public ResultSet executeDBQuery(Select_Statements.Statements stmt) {		
		return (ResultSet) null;
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