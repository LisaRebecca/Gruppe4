package controller;

import java.sql.DriverManager;
import java.sql.SQLException;

import view.Cockpit;

public class LoginControllerAutomat extends LoginController {

	@Override
	public void startLoginProcess() {
		Credentials.setUsername("root");
		Credentials.setPassword("sequel");
		establishConnection();
	}

	@Override
	public void establishConnection() {
		String userName = Credentials.getUsername();
		String password = Credentials.getPassword();

		try {
			((RealDatabase) Database.get()).setConn(DriverManager
					.getConnection("jdbc:mysql://localhost:3306/metzgerei?user=" + userName + "&password=" + password));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		new Automat();
	}

}
