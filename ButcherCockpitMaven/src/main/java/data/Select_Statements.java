package data;

public enum Select_Statements {

	FULL_STOCK, PRODUCTS, AUTOMAT, PURCHASES, AUTOMAT_PRODUCTS;

	public String getStatement() {
		switch (this) {
		case FULL_STOCK:
			return "SELECT name as Produktname, haltbar_bis as Haltbarkeit, lagerort as Lagerort, portionen as Vorraetig from lagerbestand LEFT JOIN produkte ON lagerbestand.produkt = produkte.produkt_id;";
		case AUTOMAT:
			return "SELECT name as Produktname, produkt_id as 'Produkt-ID' , kilopreis as '[â‚¬/kg]', gewicht_portion as '[kg/Portion]' FROM Produkte;";
		case PRODUCTS:
			return "SELECT name as Produktname, haltbar_bis as Haltbarkeit, portionen as Vorraetig from lagerbestand LEFT JOIN produkte ON lagerbestand.produkt = produkte.produkt_id WHERE lagerort='automat1';";
		case PURCHASES:
			return "SELECT HEX(verkauf_id) as 'Verkauf-ID', datum as Datum, uhrzeit as Uhrzeit, gesamtpreis as Gesamtpreis FROM Verkaeufe;";
		case AUTOMAT_PRODUCTS:
			return "select name, portionen, haltbar_bis, kilopreis, gewicht_portion from lagerbestand left join produkte on lagerbestand.produkt = produkte.produkt_id "
					+ "WHERE lagerort='automat1';";
		default:
			return "SHOW TABLES as 'BAD SQL_SELECT, Datenbanktabellen'';";
		}
	}
}