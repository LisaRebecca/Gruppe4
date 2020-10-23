package controller.login;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.*;

import controller.login.LoginController;
import errorhandling.AbstractButcherException;
import errorhandling.ButcherException;
import errorhandling.ExceptionHandler;
import models.Credentials;
import view.DefaultFrame;

public class Password_Screen extends DefaultFrame implements ActionListener {

//	Container c;
	
	private final ResourceBundle language;

	JPasswordField password_field;
	JTextField user_field;
	JLabel user_label;
	JLabel password_label;
	JButton button;

	public Password_Screen() throws AbstractButcherException {
	
		
		super("Login",250,125);
		this.language = ResourceBundle.getBundle("i18n/password_screen/password_screen_de");
		
		
		c.setLayout(new GridLayout(3, 2));

		user_label = new JLabel(this.language.getString("username"));
		user_field = new JTextField("root");
		password_label = new JLabel(this.language.getString("password"));
		password_field = new JPasswordField("");
		button = new JButton(this.language.getString("enter"));
		button.addActionListener(this);
		button.requestFocusInWindow();

		c.add(user_label);
		c.add(user_field);
		c.add(password_label);
		c.add(password_field);
		c.add(button);

		revalidate();
		}
		catch (AbstractButcherException e) {
			throw new ButcherException(e, "Anmelden fehlgeschlagen", "Bitte wenden Sie sich an einen Mitarbeiter");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Credentials.setPassword(password_field.getText());
		Credentials.setUsername(user_field.getText());
		this.dispose();
		try {
			LoginController.get().giveControl();
		} catch (AbstractButcherException e1) {
			ExceptionHandler.get().showException(e1);
		}
	}
}