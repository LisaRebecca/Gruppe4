package controller;

import java.text.NumberFormat;

/**
 * Die Klasse Portion modelliert ein abgepacktes Produkt, welches sich in der
 * Metzgerei oder im Kühlautomat auf Lager befinden kann.
 */
public class Portion {

	public String name;
	public int lagermenge;
	public double portionspreis;
	public double kilopreis;
	public double portionsgewichtKG;
	public String haltbar;

	/**
	 * Konstruktor, welcher Produktname, Lagermenge, Mindesthaltbarkeit, Kilopreis
	 * und Portionsgewicht übergeben bekommt 
	 * 
	 * @param name Produktname
	 * @param lagermenge zurzeit gelagerte Menge eines Produkts
	 * @param haltbar_bis Mindesthaltbarkeitsdatum eines Produkts
	 * @param kilopreis Preis pro Kilo des Produkts
	 * @param gewicht_portion Gewicht in Kilogramm, das bei einem speziellen Produkt eine Portion ausmacht
	 */
	public Portion(String name, String lagermenge, String haltbar_bis, String kilopreis, String gewicht_portion) {
		this.name = name;
		this.haltbar = haltbar_bis;
		this.kilopreis = Double.parseDouble(kilopreis);
		this.portionsgewichtKG = Double.parseDouble(gewicht_portion);
		this.portionspreis = Double.parseDouble(kilopreis) * Double.parseDouble(gewicht_portion);
		this.lagermenge = Integer.parseInt(lagermenge);
		
		}

	/**
	 * @return name den Produktname
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return das Gewicht in Kilogramm
	 */

	public double getPortionsgewichtKG() {
		return portionsgewichtKG;
	}
	
	/**
	 * @return das Gewicht in Kilogramm, das eine Portion ausmacht
	 */

	public int getPortionsgewichtGramm() {
		NumberFormat formatter = NumberFormat.getInstance();
		formatter.setMaximumFractionDigits(2);
		formatter.format(portionsgewichtKG * 1000);
		return Integer.parseInt(formatter.format(portionsgewichtKG * 1000));
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