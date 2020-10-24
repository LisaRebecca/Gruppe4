package data;

import java.util.ResourceBundle;

public class Select_Statements {
	
	public enum Statements{ FULL_STOCK, PRODUCTS, AUTOMAT, PURCHASES, AUTOMAT_PRODUCTS;}
	
		private final ResourceBundle language;
		
		public Select_Statements() {
			this.language = ResourceBundle.getBundle("i18n/select_statements/select_statements_en");
		}

		public String getStatement(Select_Statements.Statements stmt) {
			switch (stmt) {
			case FULL_STOCK:
				return "SELECT name as " + this.language.getString("name") + ", haltbar_bis as " + this.language.getString("haltbar_bis") + ", lagerort as " + this.language.getString("lagerort") + ", portionen as " + this.language.getString("portionen") + " from lagerbestand LEFT JOIN produkte ON lagerbestand.produkt = produkte.produkt_id;";
			case AUTOMAT:
				return "SELECT name as " + this.language.getString("name") + ", produkt_id as " + this.language.getString("product_id") + " , kilopreis as " + this.language.getString("kilopreis") + ", gewicht_portion as " + this.language.getString("gewicht_portionen") + " FROM Produkte;";
			case PRODUCTS:
				return "SELECT name as " + this.language.getString("name") + ", haltbar_bis as " + this.language.getString("haltbar_bis") + ", portionen as " + this.language.getString("portionen") + " from lagerbestand LEFT JOIN produkte ON lagerbestand.produkt = produkte.produkt_id WHERE lagerort='automat1';";
			case PURCHASES:
				return "SELECT HEX(verkauf_id) as " + this.language.getString("verkauf_id") + ", datum as " + this.language.getString("datum") + ", uhrzeit as " + this.language.getString("uhrzeit") + ", gesamtpreis as " + this.language.getString("gesamtpreis") + " FROM Verkaeufe;";
			case AUTOMAT_PRODUCTS:
				return "select name, portionen, haltbar_bis, kilopreis, gewicht_portion from lagerbestand left join produkte on lagerbestand.produkt = produkte.produkt_id "
						+ "WHERE lagerort='automat1';";
			default:
				return "SHOW TABLES as 'BAD SQL_SELECT, Datenbanktabellen'';";
			}
		}
	}
