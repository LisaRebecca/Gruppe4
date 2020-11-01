package tools;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MyToolsTest {
	@Test
	public void testFormatAsCurrency1() {
		assertEquals("350,00€", Tools.Formatter.formatAsCurrency(350.0000));
	}

	@Test
	public void testFormatAsCurrency2() {
		assertEquals("175,00€", Tools.Formatter.formatAsCurrency(175));
	}

	@Test
	public void testFormatAsCurrency3() {
		assertEquals("89,90€", Tools.Formatter.formatAsCurrency(89.9));
	}
}