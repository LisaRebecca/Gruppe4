package controller;

import org.junit.Test;

/**
 * Mit Hilfe der Klasse PortionTest werden die Get-Methoden der Klasse Portion gepr�ft,
 * sowie sichergestellt, dass Exceptions nur in den gewollten Szenarien geworfen werden.
 *
 */

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class PortionTest {

	/**
	 * --------------- Testing Getter ---------------
	 */
	Portion portion = new Portion("Schnitzel", "5", "16.09.2020", "16.90", "0.3");

	@Test
	public void testName() {
		Assert.assertEquals("Schnitzel", portion.getName());
	}

	@Test
	public void testLagermenge() {
		Assert.assertEquals(5, portion.getLagermenge());
	}

	@Test
	public void testHaltbarkeit() {
		Assert.assertEquals("16.09.2020", portion.getHaltbarBis());
	}

	@Test
	public void testKilopreis() {
		Assert.assertEquals(16.90, portion.getKilopreis());
	}

	@Test
	public void testPortionsgewicht() {
		Assert.assertEquals(0.3, portion.getPortionsgewichtKG());
	}

	@Test
	public void testPortionspreis() {
		Assert.assertEquals(16.90 * 0.3, portion.getPortionspreis());
	}

	@Test
	public void testgetPortionsgewichtGramm() {
		Assert.assertEquals(300, portion.getPortionsgewichtGramm());
	}

	/**
	 * --------------- Testing Setter ---------------
	 */
	Portion newPortion = new Portion();

	@Test(expected = NumberFormatException.class)
	public void testWrongKilopreisThrowsException() {
		newPortion.setKilopreis("kein Double");
		Assert.assertNotNull(portion.getKilopreis());
	}

	@Test(expected = NumberFormatException.class)
	public void testWrongPortionsgewichtKGThrowsException() {
		newPortion.setPortionsgewichtKG("auch kein Double");
		Assert.assertNotNull(portion.getPortionsgewichtKG());
	}

	@Test(expected = NumberFormatException.class)
	public void testWrongLagermengeThrowsException() {
		newPortion.setLagermenge("kein int");
		Assert.assertNotNull(portion.getLagermenge());
	}
}