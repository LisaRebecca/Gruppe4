package controller;

import java.sql.DriverManager;
import java.sql.SQLException;

import view.Cockpit;

public class LoginControllerStandard extends LoginController{
	public void startLoginProcess() {
		new Password_Screen();
	}

	public void establishConnection() {
		String userName = Credentials.getUsername();
		String password = Credentials.getPassword();

		try {
			((RealDatabase) Database.get()).setConn(DriverManager
					.getConnection("jdbc:mysql://localhost:3306/metzgerei?user=" + userName + "&password=" + password));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		new Cockpit();
	}
}
