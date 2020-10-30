package controller.login;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.*;

import controller.Main;
import controller.login.LoginController;
import errorhandling.AbstractButcherException;
import errorhandling.ButcherException;
import errorhandling.ExceptionHandler;
import errorhandling.SQLButcherException;
import models.Credentials;
import view.CockpitFactory;
import view.DefaultFrame;

/**
 * Fenster zur Eingabe der Zugangsdaten für die Datenbank.
 * 
 * @author I518232
 *
 */
public class Password_Screen extends DefaultFrame implements ActionListener {

	private final ResourceBundle language;

	private JLabel label_user;
	private JTextField textField_username;
	private JLabel label_password;
	private JPasswordField passwordField;
	private JButton button_enter;

	public Password_Screen() {

		super("Login", 250, 125);
		this.language = ResourceBundle.getBundle("i18n/password_screen/password_screen_en");

		c.setLayout(new GridLayout(3, 2));

		label_user = new JLabel(this.language.getString("username"));
		c.add(label_user);

		textField_username = new JTextField("");
		c.add(textField_username);

		label_password = new JLabel(this.language.getString("password"));
		c.add(label_password);

		passwordField = new JPasswordField("");
		c.add(passwordField);

		button_enter = new JButton(this.language.getString("enter"));
		button_enter.addActionListener(this);
		button_enter.requestFocusInWindow();
		c.add(button_enter);

		revalidate();
	}

	/**
	 * Wenn der Knopf gedrückt wird, werden die Zugangsdaten in den
	 * {@link Credentials} für die gesamte Laufzeit der Anwendung gespeichert.
	 */
	@Override
	public void actionPerformed(ActionEvent ev) {

		Credentials.setPassword(passwordField.getText());
		Credentials.setUsername(textField_username.getText());
		this.dispose();

		try {
			LoginController.get().giveControl();
		} catch (AbstractButcherException e) {
			JOptionPane.showMessageDialog(null, this.language.getString("error_message"));
			System.exit(0);
		}
	}
}