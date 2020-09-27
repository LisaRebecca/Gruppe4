package controller;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

import view.Automat;
import view.Cockpit;

public class Password_Screen extends JFrame implements ActionListener {

	Container c;

	JPasswordField password_field;
	JTextField user_field;
	JLabel user_label;
	JLabel password_label;
	JButton button;

	public Password_Screen() {
		c = getContentPane();
		c.setLayout(new GridLayout(3, 2));

		user_label = new JLabel("Username : ");
		user_field = new JTextField("");
		password_label = new JLabel("Password : ");
		password_field = new JPasswordField("");
		button = new JButton("Enter");
		button.addActionListener(this);

		c.add(user_label);
		c.add(user_field);
		c.add(password_label);
		c.add(password_field);
		c.add(button);

		this.setTitle("Login");
		this.setSize(250, 125);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Credentials.setPassword(password_field.getText());
		Credentials.setUsername(user_field.getText());
		this.dispose();
	}
}