package view;

import errorhandling.AbstractButcherException;
import java.util.ResourceBundle;
import data.Select_Statements;

/**
 * Die Factory-Klasse erstellt {@link Cockpit}s und füllt diese mit Daten.
 * 
 * @author I518232
 *
 */
public class CockpitFactory extends Factory {

	private final ResourceBundle language;

	public CockpitFactory() {
		this.language = ResourceBundle.getBundle("i18n/cockpit/cockpit_en");
	}

	/**
	 * Erstellen des {@link Cockpit}, füllen mit {@link Tile}s.
	 */
	@Override
	public void construct() throws AbstractButcherException {
		Cockpit cockpit = new Cockpit();
		cockpit.addTile(TileFactory.getTile(Select_Statements.AUTOMAT, this.language.getString("automat_label")));
		cockpit.addTile(TileFactory.getTile(Select_Statements.FULL_STOCK, this.language.getString("stock_label")));
		cockpit.addTile(TileFactory.getTile(Select_Statements.PRODUCTS, this.language.getString("product_label")));
		cockpit.addTile(TileFactory.getTile(Select_Statements.PURCHASES, this.language.getString("sales_label")));

	}

}
