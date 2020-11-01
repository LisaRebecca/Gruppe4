package view;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import errorhandling.AbstractButcherException;

/**
 * Mit Hilfe der Klasse Panel_SelectionTest soll bestaetigt werden, dass die die
 * richtigen Preise und Menge zurueckgegeben werden.
 *
 */

public class Panel_SelectionTest {

	/**
	 * Pr�ft, ob der Preis in der <code>Panel_Selection</code> richtig berechnet
	 * wird. <br>
	 * Hinweis: aktualisierePreis() wird beim Clicken des Buttons aufgerufen, wird
	 * hier also nicht explizit aufgerufen.
	 * 
	 * @throws AbstractButcherException
	 */
	static Portion portion = new Portion();

	@BeforeAll
	public static void init() {
		portion.setName("Wurst");
		portion.setLagermenge("3");
		portion.setHaltbarBis("0000-00-00");
		portion.setKilopreis("4.00");
		portion.setPortionsgewichtKG("0.5");
	}

	@Test
	public void testGetPreis() {
		Panel_Selection selection = new Panel_Selection(portion);
		selection.getJb_more().doClick();
		assertEquals(2.00, selection.getPreis());
	}

	class MockAutomat implements PropertyChangeListener {
		public int wasNotified = 0;

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			wasNotified++;
		}
	}

	@Test
	public void observerTest() throws AbstractButcherException {
		Panel_Selection ps = new Panel_Selection(portion);

		MockAutomat mockAutomat = new MockAutomat();
		ps.addPropertyChangeListener(mockAutomat);

		ps.getJb_less().doClick();
		assertTrue(mockAutomat.wasNotified > 0);
	}
}