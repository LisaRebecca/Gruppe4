package controller;

import view.Automat;
import view.Cockpit;

public class Main {
	public static void main(String[] args) {
		/**
		 * Konkatenieren des Strings, der das SQL-Select-Statement zum Auslesen der
		 * Produkte (und ihrer Daten), die sich im Automaten befinden, darstellt
		 */
		String cus_sql = "select name, portionen, haltbar_bis, kilopreis, gewicht_portion from lagerbestand "
				+ "left join produkte on lagerbestand.produkt = produkte.produkt_id " + "WHERE lagerort='automat1';";

		new Automat(DatabaseConnector.executeDBQuery(cus_sql));
	}
}