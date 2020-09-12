package Tools;

import org.junit.Assert;
import org.junit.Test;

public class MyToolsTest {
	
	@Test
	public void testFormatAsCurrency1() {
	Assert.assertEquals("350,00€", MyTools.formatAsCurrency(350.0000));
	}
	
	@Test
	public void testFormatAsCurrency2() {
	Assert.assertEquals("175,00€", MyTools.formatAsCurrency(175));
	}
	
	@Test
	public void testFormatAsCurrency3() {
	Assert.assertEquals("89,90€", MyTools.formatAsCurrency(89.9));
	}
}