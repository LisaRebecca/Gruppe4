package view;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;

import javax.swing.JTable;


import Tools.Currency_Symbol;
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
		portion.setKilopreis("3.00");
		Panel_Selection selection = new Panel_Selection(portion);
		selection.aktualisierePreis();
		selection.setPreisLabel();
		assertEquals(selection.getPreis(), 3.00, 0.1);
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
		Portion portion = new Portion();
		portion.setName("Wurst");
		portion.setLagermenge(rs_products.getString("portionen"));
		portion.setHaltbarBis(rs_products.getString("haltbar_bis"));
		portion.setKilopreis(rs_products.getString("kilopreis"));
		portion.setPortionsgewichtKG(rs_products.getString("gewicht_portion"));
		portion.setKilopreis("3.00");
		
		Panel_Selection ps = new Panel_Selection(portion);

		MockAutomat mockAutomat = new MockAutomat();
		ps.addPropertyChangeListener(mockAutomat);
		
		ps.getJb_less().doClick();
		assertTrue(mockAutomat.wasNotified > 0);
	}
}