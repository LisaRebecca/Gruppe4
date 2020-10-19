package view;

import java.sql.SQLException;

import javax.swing.JTable;

import org.junit.Assert;
import org.junit.Test;

/**
 * Mit Hilfe der Klasse Panel_SelectionTest soll bestaetigt werden, dass die die
 * richtigen Preise und Menge zurueckgegeben werden.
 *
 */

public class Panel_SelectionT{

	/**
	 * Pr�ft, ob der Preis in der <code>Panel_Selection</code> richtig berechnet
	 * wird. <br>
	 * Hinweis: aktualisierePreis() wird beim Clicken des Buttons aufgerufen, wird
	 * hier also nicht explizit aufgerufen.
	 */
	@Test
	public void testAktualisierePreis() {
		Portion portion = new Portion();
		portion.setKilopreis(5.00);
		portion.setPortionsgewichtKG(0.5);

		try {
			Automat automat = new Automat();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Panel_Selection selection = new Panel_Selection(portion);

		selection.getJb_more().doClick();
		Assert.assertEquals(selection.getPreis(), 2.50, 1);
	}
}