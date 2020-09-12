package controller;

import javax.swing.JTable;

import org.junit.Assert;
import org.junit.Test;

import view.Automat;

/**
 * Mit Hilfe der Klasse AutomatTest soll bestätigt werden, dass die einzelnen Preise der ausgewählten Produkte
 * korrekt zum Gesamtpreis aufsummiert werden.
 *
 */
public class AutomatTest {
	Object[][] data = {{"Lendchen", "5", "2021-12-31", "15.00", "0.5"}};
	Object[] colNames = {"","","","",""};
	
	Automat automat = new Automat(new JTable(data, colNames));

	@Test
	public void testBerechneGesamtpreis() {
		automat.list_productSelection.get(0).getJb_more().doClick();
		Assert.assertEquals(automat.getGesamtpreis(), 7.50, 1);
		
	}
}
