package classes;

/**
 * Die Klasse Portion modelliert ein abgepacktes Produkt, welches sich in der
 * Metzgerei oder im Kühlautomat auf Lager befinden kann.
 */
class Portion {
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
		return name + ",  " + portionspreis + "€/Portion,  " + haltbar;
	}
}