public class MyToolsTest {
	
	@Test
	public void testFormatAsCurrency() {
	assertEquals("350.00€", new MyTools().formatAsCurrency(350.00));
	}
	
	@Test
	public void testFormatAsCurrency() {
	assertEquals("175.00€", new MyTools().formatAsCurrency(175.00));
	}
	
	@Test
	public void testFormatAsCurrency() {
	assertEquals("89.90€", new MyTools().formatAsCurrency(89.00));
	}

}
