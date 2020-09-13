package view;

import org.junit.Assert;
import org.junit.Test;

import controller.Portion;

/**
 * Mit Hilfe der Klasse Panel_SelectionTest soll bestaetigt werden, dass die die richtigen Preise und Menge zurueckgegeben werden. 
 *
 */

public class Panel_SelectionTest {
	private Portion portion = new Portion("Bockwurst", "10", "2020-11-08", "15.0", "0.100");
	private Automat automat;
	private Panel_Selection selection = new Panel_Selection(portion, automat);
	
//	@Test
//	public void testGetAmount() {
//		Einkauf.getJb_more().doClick();
//		Einkauf.getJb_more().doClick();
//		Einkauf.getJb_more().doClick();
//		Assert.assertEquals(Einkauf.getAmount(), 3);
//	}
	
	@Test
	public void testGetPreis() {
		selection.getJb_more().doClick();
		Assert.assertEquals(selection.getPreis(), 1.50, 1);
	}
}