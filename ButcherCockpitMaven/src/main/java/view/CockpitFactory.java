package view;

import data.Select_Statements;

public class CockpitFactory extends Factory{

	@Override
	public void construct() {
		Cockpit cockpit = new Cockpit();
		cockpit.addTile(TileFactory.getTile(Select_Statements.AUTOMAT, "Füllstand Automat"));
		cockpit.addTile(TileFactory.getTile(Select_Statements.FULL_STOCK, "Lagerbestand"));
		cockpit.addTile(TileFactory.getTile(Select_Statements.PRODUCTS, "Produktportfolio"));
		cockpit.addTile(TileFactory.getTile(Select_Statements.PURCHASES, "Einkäufe"));
	}

}
