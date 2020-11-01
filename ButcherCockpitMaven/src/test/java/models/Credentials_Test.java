package models;

import org.junit.Test;

import junit.framework.Assert;

public class Credentials_Test {

	@Test
	public void getIsSetTest1() {
		Credentials.setPassword("testpassword");
		Credentials.setUsername("testusername");
		Assert.assertEquals(true, Credentials.getIsSet());

	}

	@Test
	public void getIsSetTest2() {
		Credentials.setUsername(null);
		Credentials.setPassword("testpassword");
		Assert.assertEquals(false, Credentials.getIsSet());

	}
	
	@Test
	public void getIsSetTest3() {
		Credentials.setUsername("testUsername");
		Credentials.setPassword(null);
		Assert.assertEquals(false, Credentials.getIsSet());

	}

	@Test
	public void getIsSetTest4() {
		Credentials.setPassword(null);
		Credentials.setUsername(null);
		Assert.assertEquals(false, Credentials.getIsSet());

	}

}
