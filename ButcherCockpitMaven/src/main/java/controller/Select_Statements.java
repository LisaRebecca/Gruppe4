package controller;

public class Select_Statements {
	public static final String select_full_stock = "SELECT name as Produktname, haltbar_bis as Haltbarkeit, lagerort as Lagerort, portionen as Vorraetig from lagerbestand LEFT JOIN produkte ON lagerbestand.produkt = produkte.produkt_id;";
	public static final String select_products = "SELECT name as Produktname, produkt_id as 'Produkt-ID' , kilopreis as '[â‚¬/kg]', gewicht_portion as '[kg/Portion]' FROM Produkte;";
	public static final String select_automat = "SELECT name as Produktname, haltbar_bis as Haltbarkeit, portionen as Vorraetig from lagerbestand LEFT JOIN produkte ON lagerbestand.produkt = produkte.produkt_id WHERE lagerort='automat1';";
	public static final String select_purchases = "SELECT HEX(verkauf_id) as 'Verkauf-ID', datum as Datum, uhrzeit as Uhrzeit, gesamtpreis as Gesamtpreis FROM Verkaeufe;";
}