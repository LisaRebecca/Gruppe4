package controller;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import view.Portion;

/**
 * Mit Hilfe der Klasse PortionTest werden die Get-Methoden der Klasse Portion
 * gepr�ft, sowie sichergestellt, dass Exceptions nur in den gewollten Szenarien
 * geworfen werden.
 *
 */
public class PortionTest {

	// --------------- Testing Getter ---------------

	Portion portion = new Portion("Schnitzel", "5", "16.09.2020", "16.90", "0.3");

	@org.junit.jupiter.api.Test
	public void testName() {
		assertEquals("Schnitzel", portion.getName());
	}

	@Test
	public void testLagermenge() {
		assertEquals(5, portion.getLagermenge());
	}

	@Test
	public void testHaltbarkeit() {
		assertEquals("16.09.2020", portion.getHaltbarBis());
	}

	@Test
	public void testKilopreis() {
		assertEquals(16.90, portion.getKilopreis());
	}

	@Test
	public void testPortionsgewicht() {
		assertEquals(0.3, portion.getPortionsgewichtKG());
	}

	@Test
	public void testPortionspreis() {
		assertEquals(16.90 * 0.3, portion.getPortionspreis());
	}

	@Test
	public void testgetPortionsgewichtGramm() {
		assertEquals(300, portion.getPortionsgewichtGramm());
	}

	// --------------- Testing Setter ---------------

	Portion newPortion = new Portion();

	@Test
	public void testWrongKilopreisThrowsException() {
		assertThrows(NumberFormatException.class, () -> newPortion.setKilopreis("kein Double"));
		assertNotNull(portion.getKilopreis());
	}

	@Test
	public void testWrongPortionsgewichtKGThrowsException() {
		assertThrows(NumberFormatException.class, () -> newPortion.setPortionsgewichtKG("kein Double"));
		assertNotNull(portion.getPortionsgewichtKG());
	}

	@Test
	public void testWrongLagermengeThrowsException() {
		assertThrows(NumberFormatException.class, () -> newPortion.setLagermenge("kein int"));
		assertNotNull(portion.getLagermenge());
	}
}