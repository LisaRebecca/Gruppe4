package view;

import controller.Select_Statements;

public class CockpitFactory extends Factory{

	@Override
	public void construct() {
		Cockpit cockpit = new Cockpit();
		cockpit.addTile(TileFactory.getTile(Select_Statements.select_automat, "Füllstand Automat"));
		cockpit.addTile(TileFactory.getTile(Select_Statements.select_full_stock, "Lagerbestand"));
		cockpit.addTile(TileFactory.getTile(Select_Statements.select_products, "Produktportfolio"));
		cockpit.addTile(TileFactory.getTile(Select_Statements.select_purchases, "Einkäufe"));
	}

}
