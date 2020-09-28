package controller;

public class LoginControllerFilled extends LoginController {

	@Override
	public void giveControl() {
		if (Credentials.getIsSet()) {
			if (Database.get().isConnected) {
				Main.construct();
			} else {
				Database.get().establishConnection();
			}
		} else {
			Credentials.setUsername("root");
			Credentials.setPassword("sequel");
			giveControl();
		}
	}
}