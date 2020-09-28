package controller;

public class MainCockpit {
	public static void main(String[] args) {
		LoginController.set(new LoginControllerStandard());
		Database.set(new RealDatabase());
	}
}
