package models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Credentials_Test {

	@Test
	public void getIsSetTest1() {
		Credentials.setPassword("testpassword");
		Credentials.setUsername("testusername");
		assertEquals(true, Credentials.getIsSet());

	}

	@Test
	public void getIsSetTest2() {
		Credentials.setUsername(null);
		Credentials.setPassword("testpassword");
		assertEquals(false, Credentials.getIsSet());

	}
	
	@Test
	public void getIsSetTest3() {
		Credentials.setUsername("testUsername");
		Credentials.setPassword(null);
		assertEquals(false, Credentials.getIsSet());

	}

	@Test
	public void getIsSetTest4() {
		Credentials.setPassword(null);
		Credentials.setUsername(null);
		assertEquals(false, Credentials.getIsSet());

	}

}
