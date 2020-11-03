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
	@Test
	public void testGetPreis() {
		Portion portion = new Portion();
		portion.setKilopreis(15);
		portion.setPortionsgewichtKG(0.5);
		Panel_Selection selection = new Panel_Selection(portion);
		selection.getJb_more().doClick();
		assertEquals(7.5, selection.getPreis());
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
		Portion portion = new Portion("Schnitzel", "5", "16.09.2020", "1", "1");
		Panel_Selection ps = new Panel_Selection(portion);

		MockAutomat mockAutomat = new MockAutomat();
		ps.addPropertyChangeListener(mockAutomat);

		ps.getJb_less().doClick();
		assertTrue(mockAutomat.wasNotified == 0);
	}
}