package view;

import javax.swing.JTable;

import org.junit.Assert;
import org.junit.Test;

import controller.Portion;

/**
 * Mit Hilfe der Klasse Panel_SelectionTest soll bestaetigt werden, dass die die
 * richtigen Preise und Menge zurueckgegeben werden.
 *
 */

public class Panel_SelectionTest {

	/**
	 * Prüft, ob der Preis in der <code>Panel_Selection</code> richtig berechnet
	 * wird. <br>
	 * Hinweis: aktualisierePreis() wird beim Clicken des Buttons aufgerufen, wird
	 * hier also nicht explizit aufgerufen.
	 */
	@Test
	public void testAktualisierePreis() {
		Portion portion = new Portion();
		portion.setKilopreis(5.00);
		portion.setPortionsgewichtKG(0.5);

		Automat automat = new Automat(new JTable());

		Panel_Selection selection = new Panel_Selection(portion, automat);

		selection.getJb_more().doClick();
		Assert.assertEquals(selection.getPreis(), 2.50, 1);
	}
}