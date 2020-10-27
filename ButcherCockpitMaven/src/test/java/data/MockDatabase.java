package data;


import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mockrunner.jdbc.FileResultSetFactory;

import Tools.Converter;
import errorhandling.AbstractButcherException;

public class MockDatabase extends Database {
	
	private Connection connection;
	FileResultSetFactory rsf;
	public void disconnect() throws SQLException {
		if (null != connection) {
			connection.close();
			connection = null;
		}
	}

	public MockDatabase() {
		isConnected = false;
		File csv = new File("src/test/resources/metzgerei.csv");
		rsf = new FileResultSetFactory(csv);
	}
	
	public ResultSet getRS() {
		return rsf.create("");
	}

	@Override
	public ResultSet executeDBQuery(Select_Statements stmt) {
		return (ResultSet) null;
	}

	@Override
	public void executeDBInsert(String insert_statement) {

	}

	@Override
	public void establishConnection() throws AbstractButcherException {
		try {
			disconnect();
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test");
			connection.setAutoCommit(false);			
		}catch(SQLException e) {
//			throw new AbstractButcherException();
		}
	}
	public static void main(String[] args) {
		MockDatabase md = new MockDatabase();
		System.out.println(Converter.resultSetToTable(md.rsf.create("hi")));
	}
}