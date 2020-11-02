package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Nested;
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
	@Nested
	public class GetterTests {
		Portion portion = new Portion("Schnitzel", "5", "16.09.2020", "16.90", "0.3");

		@Test
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

	}

	// --------------- Testing Setter ---------------
	@Nested
	public class SetterTests {

		Portion portion = new Portion();

		@Test
		public void testWrongKilopreisThrowsException() {
			assertThrows(NumberFormatException.class, () -> portion.setKilopreis("kein Double"));
			assertNotNull(portion.getKilopreis());
		}

		@Test
		public void testWrongPortionsgewichtKGThrowsException() {
			assertThrows(NumberFormatException.class, () -> portion.setPortionsgewichtKG("kein Double"));
			assertNotNull(portion.getPortionsgewichtKG());
		}

		@Test
		public void testWrongLagermengeThrowsException() {
			assertThrows(NumberFormatException.class, () -> portion.setLagermenge("kein int"));
			assertNotNull(portion.getLagermenge());
		}
	}

	// --------------- Testing Equals ---------------
	@Nested
	public class EqualsTests {

		@Test
		public void testReflexive() {
			Portion portion = new Portion();
			assertTrue(portion.equals(portion));

		}

		@Test
		public void testReflexiveNull() {
			Portion p = null;
//			assertThrows(NullPointerException.class, () -> p.equals(p));	
			assertFalse(p.equals(p));
		}

		@Test
		public void testSymmetricTrueOnEqual() {
			Portion a = new Portion("Schnitzel", "5", "16.09.2020", "16.90", "0.3");
			Portion b = new Portion("Schnitzel", "5", "16.09.2020", "16.90", "0.3");
			assertEquals(a.equals(b), b.equals(a));
		}
		@Test
		public void testSymmetricFalseOnNotEqual() {
			Portion a = new Portion("Pute", "5", "16.09.2020", "16.90", "0.3");
			Portion b = new Portion("Schnitzel", "5", "16.09.2020", "16.90", "0.3");
			assertEquals(a.equals(b), b.equals(a));
		}
		
		@Test 
		public void testTransitive(){
			Portion a = new Portion("Schnitzel", "5", "16.09.2020", "16.90", "0.3");
			Portion b = new Portion("Schnitzel", "5", "16.09.2020", "16.90", "0.3");
			Portion c = new Portion("Schnitzel", "5", "16.09.2020", "16.90", "0.3");
			
			boolean ab = a.equals(b);
			boolean bc = b.equals(c);
			
			assertEquals(ab&bc, a.equals(c));
		}
		
		@Test
		public void testConsistent() {
			// nur ein attempt, es zu testen. man müsste das wohl mit einer extension testen.
			Portion a = new Portion("Schnitzel", "5", "16.09.2020", "16.90", "0.3");
			Portion b = new Portion("Schnitzel", "5", "16.09.2020", "16.90", "0.3");
			assertEquals(a.equals(b), a.equals(b));
		}
		
		@Test
		public void testNullReferenceReturnsFalse() {
			Portion a = new Portion("Schnitzel", "5", "16.09.2020", "16.90", "0.3");
			assertFalse(a.equals(null));
		}

	}

	// --------------- Testing HashCode ---------------
}