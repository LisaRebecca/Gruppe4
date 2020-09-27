package controller;

public class Credentials {
	private static String username;
	private static String password;

	public static void setUsername(String username) {
		Credentials.username = username;
	}

	public static String getUsername() {
		return username;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		Credentials.password = password;
	}
}
