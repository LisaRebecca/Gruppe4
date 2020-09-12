package controller;

import org.junit.Assert;
import org.junit.Test;

import view.Automat;
import view.Panel_Selection;

/**
 * Mit Hilfe der Klasse Panel_SelectionTest soll bestaetigt werden, dass die die richtigen Preise und Menge zurueckgegeben werden. 
 *
 */

public class Panel_SelectionTest {
	private Portion portion = new Portion("Bockwurst", "10", "2020-11-08", "15.0", "0.100");
	private Automat automat;
	private Panel_Selection Einkauf = new Panel_Selection(portion, automat);
	
	@Test
	public void testGetAmount() {
		Einkauf.setAmount(3);
		Assert.assertEquals(Einkauf.getAmount(), 3);
	}
	
	@Test
	public void testGetPreis() {
		Einkauf.getJb_more().doClick();
		Assert.assertEquals(Einkauf.getPreis(), 1.50);
	}
}
