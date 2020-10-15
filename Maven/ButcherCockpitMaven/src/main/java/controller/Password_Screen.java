package controller;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

import Tools.AbstractButcherException;
import view.Automat;
import view.Cockpit;
import view.DefaultFrame;

public class Password_Screen extends DefaultFrame implements ActionListener {

//	Container c;

	JPasswordField password_field;
	JTextField user_field;
	JLabel user_label;
	JLabel password_label;
	JButton button;

	public Password_Screen(){
		super("Login",250,125);
		
		c.setLayout(new GridLayout(3, 2));

		user_label = new JLabel("Username : ");
		user_field = new JTextField("root");
		password_label = new JLabel("Password : ");
		password_field = new JPasswordField("");
		button = new JButton("Enter");
		button.addActionListener(this);
		button.requestFocusInWindow();

		c.add(user_label);
		c.add(user_field);
		c.add(password_label);
		c.add(password_field);
		c.add(button);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Credentials.setPassword(password_field.getText());
		Credentials.setUsername(user_field.getText());
		this.dispose();
		try {
			LoginController.get().giveControl();
		} catch (AbstractButcherException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}