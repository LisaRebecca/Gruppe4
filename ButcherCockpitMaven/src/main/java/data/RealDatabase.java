package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import errorhandling.AbstractButcherException;
import errorhandling.ButcherException;
import errorhandling.ExceptionHandler;
import errorhandling.SQLButcherException;
import controller.login.LoginController;
import data.Database;
import models.Credentials;

public class RealDatabase extends Database {

	private final ResourceBundle language;

	/**
	 * Verbindung zur Datenbank
	 */
	private Connection conn;

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public RealDatabase() {
		this.language = ResourceBundle.getBundle("i18n/real_database/real_database_en");
		isConnected = false;
	}

	@Override
	public ResultSet executeDBQuery(Select_Statements stmt) {
		if (!isConnected) {
			ExceptionHandler.get()
					.showException(new SQLButcherException(new SQLException(this.language.getString("not_logged_in"))));
		}
		try {
			return conn.createStatement().executeQuery(stmt.getStatement());
		} catch (SQLException e) {
			ExceptionHandler.get().showException(new ButcherException(e, this.language.getString("error"),
					this.language.getString("error_message")));
			return null;
		}
	}

	@Override
	public void executeDBInsert(String insert_statement) {
		try {
			conn.createStatement().execute(insert_statement);
		} catch (SQLException e) {
			ExceptionHandler.get().showException(new ButcherException(e, this.language.getString("error"),
					this.language.getString("error_message")));
		}

	}

	public void establishConnection() throws AbstractButcherException {
		String userName = Credentials.getUsername();
		String password = Credentials.getPassword();

		try {
			((RealDatabase) Database.get()).setConn(DriverManager
					.getConnection("jdbc:mysql://localhost:3306/metzgerei?user=" + userName + "&password=" + password));
			isConnected = true;
			LoginController.get().giveControl();
		} catch (SQLException e) {
			ExceptionHandler.get().showException(
					new ButcherException(e, this.language.getString("error"), this.language.getString("no_access")));
			isConnected = false;
		}
	}
}