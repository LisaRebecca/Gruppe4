package controller;

import org.junit.Test;

/**
 * Mit Hilfe der Klasse PortionTest werden die Get-Methoden der Klasse Portion geprüft,
 * sowie sichergestellt, dass Exceptions nur in den gewollten Szenarien geworfen werden.
 *
 */

import junit.framework.Assert;

public class PortionTest {
	
	@Test
	public void testName () {
		Portion portion = new Portion("Schnitzel", "5","16.09.2020", "16.90", "0.3");
		Assert.assertEquals("Schnitzel", portion.getName());
	}
	@Test
	public void testLagermenge () {
	Portion portion = new Portion("Schnitzel", "5","16.09.2020", "16.90", "0.3");
	Assert.assertEquals(5, portion.getLagermenge());
	}
	@Test
	public void testHaltbarkeit () {
	Portion portion = new Portion("Schnitzel", "5","16.09.2020", "16.90", "0.3");
	Assert.assertEquals("16.09.2020", portion.getHaltbar());
	}
	
	@Test
	public void testKilopreis () {
	Portion portion = new Portion("Schnitzel", "5","16.09.2020", "16.90", "0.3");
	Assert.assertEquals(16.90, portion.getKilopreis());
	}
	
	@Test
	public void testPortionsgewicht () {
	Portion portion = new Portion("Schnitzel", "5","16.09.2020", "16.90", "0.3");
	Assert.assertEquals(0.3, portion.getPortionsgewichtKG());
	}
	@Test
	public void testPortionspreis () {
	Portion portion = new Portion("Schnitzel", "5","16.09.2020", "16.90", "0.3");
	Assert.assertEquals(Double.parseDouble("16.90") * Double.parseDouble("0.3"), portion.getPortionspreis());
	}
	
	@Test(expected=NumberFormatException.class)
	public void testWrongKilopreisThrowsException() {
		Portion portion = new Portion("Schnitzel", "5","16.09.2020", "abc", "0.3");
	}
	@Test(expected=NumberFormatException.class)
	public void testWrongPortionsgewichtThrowsException() {
		Portion portion = new Portion("Schnitzel", "5","16.09.2020", "16.90", "ab");
	}
	@Test(expected=NumberFormatException.class)
	public void testWrongPortionspreisThrowsException() {
		Portion portion = new Portion("Schnitzel", "5","16.09.2020", "abc", "0.3");
	}
	@Test(expected=NumberFormatException.class)
	public void testWrongLagermengeThrowsException() {
		Portion portion = new Portion("Schnitzel", "?.)?","16.09.2020", "abc", "0.3");
	}
	@Test
	public void testgetPortionsgewichtGramm() {
		Portion portion = new Portion("Schnitzel", "5","16.09.2020", "16.90", "0.3");
		Assert.assertEquals(300, portion.getPortionsgewichtGramm());
	}
}
