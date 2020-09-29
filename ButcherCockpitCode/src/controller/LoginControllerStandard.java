package controller;

public class LoginControllerStandard extends LoginController {

	@Override
	public void giveControl() {
		if (Credentials.getIsSet()) {
			if (Database.get().isConnected) {
				Main.construct();
			} else {
				Database.get().establishConnection();
			}
		} else {
			new Password_Screen();
		}
	}
}