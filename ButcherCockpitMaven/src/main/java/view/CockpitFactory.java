package view;


import errorhandling.AbstractButcherException;
import java.util.ResourceBundle;
import data.Select_Statements;


public class CockpitFactory extends Factory{
	
	private final ResourceBundle language;
	private Select_Statements stmt;
	
	public CockpitFactory() {
		this.language = ResourceBundle.getBundle("i18n/cockpit/cockpit_en");
	}

	@Override

	public void construct() throws AbstractButcherException {
		Cockpit cockpit = new Cockpit();
		cockpit.addTile(TileFactory.getTile(stmt, Select_Statements.Statements.AUTOMAT, this.language.getString("automat_label")));
		cockpit.addTile(TileFactory.getTile(stmt, Select_Statements.Statements.FULL_STOCK, this.language.getString("stock_label")));
		cockpit.addTile(TileFactory.getTile(stmt, Select_Statements.Statements.PRODUCTS, this.language.getString("product_label")));
		cockpit.addTile(TileFactory.getTile(stmt, Select_Statements.Statements.PURCHASES, this.language.getString("sales_label")));

	}

}
