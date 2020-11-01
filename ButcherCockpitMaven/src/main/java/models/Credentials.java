package models;

public class Credentials {
	private static String username;
	private static String password;

	
	/**
	 * Setzen des Nutzernamen
	 */
	public static void setUsername(String username) {
		Credentials.username = username;
	}
/**
 * 
 * @return Nutzername als String
 */
	public static String getUsername() {
		return username;
	}
/**
 * 
 * @return Passwort als String
 */
	public static String getPassword() {
		return password;
	}
/**
 * Setzen des Passwort's
 */
	public static void setPassword(String password) {
		Credentials.password = password;
	}
	
	/**
	 * 
	 * @return True, unter der Bedingung, dass sowohl Passwort als auch Username ungleich "null" sind
	 */
	public static boolean getIsSet() {
		return !(password == null || username == null);
	}
}
