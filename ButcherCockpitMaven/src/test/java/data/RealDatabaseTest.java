package data;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.*;

import org.junit.jupiter.api.*;

import errorhandling.AbstractButcherException;
import errorhandling.ExceptionHandler;
import errorhandling.ExceptionHandlerDebug;
import models.Credentials;

@SuppressWarnings("serial")
public class RealDatabaseTest extends JFrame {

	@BeforeAll
	public static void initialize() {
		ExceptionHandler.set(new ExceptionHandlerDebug());
	}

	@Nested
	public class WrongCredentialsTest {
		
		@Test
		void establishConnectionTest() throws AbstractButcherException {
			RealDatabase db = new RealDatabase();
			Credentials.setPassword("wrong password");
			Credentials.setUsername("wrong username");
			db.establishConnection();
			assertFalse(db.isConnected);
		}

	}

	@Nested
	public class CorrectCredentialsTest {

		@Test
		void establishConnectionTest() throws AbstractButcherException {
			RealDatabase db = new RealDatabase();
			Credentials.setUsername("DefaultUser");
			Credentials.setPassword("DefaultPassword");
			db.establishConnection();
			assertTrue(db.isConnected);
		}

	}

}
