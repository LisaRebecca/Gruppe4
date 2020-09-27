package controller;

public abstract class LoginController {
	private static LoginController loginController;

	public static LoginController get() {
		return loginController;
	}

	public static void set(LoginController loginController) {
		LoginController.loginController = loginController;
	}

	public abstract void startLoginProcess();

	public abstract void establishConnection();
}
