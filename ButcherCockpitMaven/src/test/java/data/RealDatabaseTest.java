package data;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.swing.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import errorhandling.AbstractButcherException;
import models.Credentials;

@SuppressWarnings("serial")
public class RealDatabaseTest extends JFrame {

	@BeforeAll
	public void initialize() {
		Database.set(new RealDatabase());
	}

	@Nested
	public class WrongCredentialsTest {

		@BeforeEach
		void init() {
			Credentials.setPassword("wrong password");
			Credentials.setUsername("wrong username");
		}

		@Test
		void establishConnectionTest1() throws AbstractButcherException {
			assertThrows(AbstractButcherException.class, () -> {
				Database.get().establishConnection();
			});
		}

		@Test(expected = AbstractButcherException.class)
		void establishConnectionTest2() throws AbstractButcherException {
			Database.get().establishConnection();
			assertFalse(Database.get().isConnected);
		}
	}

	@Nested
	public class CorrectCredentialsTest {

		@Test
		void establishConnectionTest1() throws AbstractButcherException {
			Credentials.setPassword("Nutzer");
			Credentials.setUsername("nutzerpasswort");
			Database.get().establishConnection();
			assertTrue(Database.get().isConnected);
		}

	}

}
