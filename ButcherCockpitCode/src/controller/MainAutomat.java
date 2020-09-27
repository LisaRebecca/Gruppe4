package controller;

public class MainAutomat {
	public static void main(String[] args) {
		
		LoginController.set(new LoginControllerAutomat());
		Database.set(new RealDatabase());
	}
}
