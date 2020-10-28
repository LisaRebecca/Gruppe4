package models;

import org.junit.Test;

import junit.framework.Assert;

public class Credentials_Test {

	
	

	@Test
	public void getIsSetTest() {
		Credentials.setPassword("testpassword");
		Credentials.setUsername("testusername");
		Assert.assertEquals(true, Credentials.getIsSet());

	}

}
