package view;

import javax.swing.JTable;

import org.junit.jupiter.api.Test;
import org.junit.Test;

/**
 * Mit Hilfe der Klasse AutomatTest soll bestätigt werden, dass die einzelnen Preise der ausgew�hlten Produkte
 * korrekt zum Gesamtpreis aufsummiert werden.
 *
 */
public class AutomatT {
	Object[][] data = {{"Lendchen", "5", "2021-12-31", "15.00", "0.5"}, {"Rippchen", "3", "2021-09-05", "8.00", "0.25"}};
	Object[] colNames = {"","","","",""};
	

	/**
	 * Testet, ob der Gesamtpreis korrekt berechnet wird.
	 */
	@Test
	public void testBerechneGesamtpreis() {
		automat.list_productSelection.get(0).getJb_more().doClick();
		automat.list_productSelection.get(1).getJb_more().doClick();
		Assert.assertEquals(automat.getGesamtpreis(), 9.50, 0.1);
	}
}
