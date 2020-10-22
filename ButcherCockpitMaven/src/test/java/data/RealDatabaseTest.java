package data;

import static org.junit.Assert.assertFalse;
import javax.swing.*;

import org.junit.Before;
import org.junit.Test;

import errorhandling.AbstractButcherException;
import models.Credentials;

@SuppressWarnings("serial")
public class RealDatabaseTest extends JFrame {
	
	@Before
	public void initialize() {
		Database.set(new RealDatabase());
	}

	@Test(expected = AbstractButcherException.class)
	public void establishConnectionTest1() throws AbstractButcherException {
		Credentials.setPassword("wrong password");
		Credentials.setUsername("wrong username");

		Database.get().establishConnection();
	}

	@Test(expected = AbstractButcherException.class)
	public void establishConnectionTest2() throws AbstractButcherException {
		Credentials.setPassword("wrong password");
		Credentials.setUsername("wrong username");

		Database.get().establishConnection();
		assertFalse(Database.get().isConnected);
	}

	
}
