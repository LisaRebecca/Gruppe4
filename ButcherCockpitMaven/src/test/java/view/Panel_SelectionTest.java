package view;

import static org.junit.Assert.assertTrue;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;

import javax.swing.JTable;

import org.junit.Assert;
import org.junit.Test;

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
//	@Test
//	public void testAktualisierePreis() {
//		Portion portion = new Portion();
//		portion.setKilopreis(5.00);
//		portion.setPortionsgewichtKG(0.5);
//
//		try {
//			Automat automat = new Automat();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		Panel_Selection selection = new Panel_Selection(portion);
//
//		selection.getJb_more().doClick();
//		Assert.assertEquals(selection.getPreis(), 2.50, 1);
//	}
	@Test
	public void testGetPreis() {
		Portion portion = new Portion();
		String preis = "3,00€";
		portion.setKilopreis(preis);
		portion.setPortionsgewichtKG(1);

		Panel_Selection selection = new Panel_Selection(portion);
		selection.aktualisierePreis();
		selection.setPreisLabel();

		Assert.assertEquals(selection.getPreis(), 3.00, 0.1);
	}

//		public double getPreis() {
//			String preis = jlbl_preis.getText().replace(',', '.');
//
//			int index = preis.indexOf(Currency_Symbol.getCurrency_Symbol());
//
//			if (index == -1) {
//			} else {
//				preis = preis.substring(0, index);
//			}
//			return Double.parseDouble(preis);
//		}

	class MockAutomat implements PropertyChangeListener {
		public int wasNotified = 0;

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			wasNotified++;
		}
	}

	@Test
	public void observerTest() throws AbstractButcherException {
		Panel_Selection ps = new Panel_Selection(new Portion());
		
		MockAutomat mockAutomat = new MockAutomat();
		ps.addPropertyChangeListener(mockAutomat);
		
		assertTrue(mockAutomat.wasNotified > 0);
	}
}