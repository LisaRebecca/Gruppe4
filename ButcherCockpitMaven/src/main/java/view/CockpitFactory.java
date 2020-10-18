package view;

import controller.Select_Statements;
import models.Tile;

public class CockpitFactory extends Factory{

	@Override
	public void construct() {
		Cockpit cockpit = new Cockpit();
		cockpit.addTile(new Tile(Select_Statements.select_automat, "Füllstand Automat"));
		cockpit.addTile(new Tile(Select_Statements.select_full_stock, "Lagerbestand"));
		cockpit.addTile(new Tile(Select_Statements.select_products, "Produktportfolio"));
		cockpit.addTile(new Tile(Select_Statements.select_purchases, "Einkäufe"));
	}

}
