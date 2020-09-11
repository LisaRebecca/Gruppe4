package controller;

import java.text.NumberFormat;

/**
 * Die Klasse Portion modelliert ein abgepacktes Produkt, welches sich in der
 * Metzgerei oder im Kühlautomat auf Lager befinden kann.
 */
public class Portion {

	String name;
	int lagermenge;
	double portionspreis;
	double kilopreis;
	double portionsgewicht;
	/**
	 * Das Mindesthaltbarkeitsdatum
	 */
	String haltbar;

	/**
	 * Konstruktor, welcher
	 * 
	 * @param name
	 * @param lagermenge
	 * @param haltbar_bis
	 * @param kilopreis
	 * @param gewicht_portion
	 */
	public Portion(String name, String lagermenge, String haltbar_bis, String kilopreis, String gewicht_portion) {
		this.name = name;
		this.haltbar = haltbar_bis;
		this.kilopreis = Double.parseDouble(kilopreis);
		this.portionsgewicht = Double.parseDouble(gewicht_portion);
		try {
			this.portionspreis = Double.parseDouble(kilopreis) * Double.parseDouble(gewicht_portion);
			this.lagermenge = Integer.parseInt(lagermenge);
		} catch (NumberFormatException e) {
			// TODO: handle exception
		}
	}

	/**
	 * Ausgabe des Produktes inklusive Eigenschaften als Text
	 */
	@Override
	public String toString() {
		return getName() + ",  " + portionspreis + "€/Portion,  " + haltbar;
	}

	/**
	 * 
	 * @return name den Produktname
	 */
	public String getName() {
		return name;
	}

	public double getPortionsgewichtKG() {
		return portionsgewicht;
	}

	public int getPortionsgewichtGramm() {
		NumberFormat formatter = NumberFormat.getInstance();
		formatter.setMaximumFractionDigits(2);
		formatter.format(portionsgewicht * 1000);
		return Integer.parseInt(formatter.format(portionsgewicht * 1000));
	}

	/**
	 * @return the lagermenge
	 */
	public int getLagermenge() {
		return lagermenge;
	}

	/**
	 * @return the portionspreis
	 */
	public double getPortionspreis() {
		return portionspreis;
	}

	/**
	 * @return the kilopreis
	 */
	public double getKilopreis() {
		return kilopreis;
	}

	/**
	 * @return the haltbar
	 */
	public String getHaltbar() {
		return haltbar;
	}
}