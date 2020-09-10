package controller;

/**
 * Die Klasse Portion modelliert ein abgepacktes Produkt, welches sich in der
 * Metzgerei oder im Kühlautomat auf Lager befinden kann.
 */
public class Portion {

	String name;
	int lagermenge;
	double portionspreis;
	double kilopreis;
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