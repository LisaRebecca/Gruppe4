package classes;

/**
 * Die Klasse Portion modelliert ein abgepacktes Produkt, welches sich in der
 * Metzgerei oder im Kühlautomat auf Lager befinden kann.
 */
class Portion {
	public String name;
	public int lagermenge;
	public double portionspreis;
	public String haltbar;
	

	public Portion(String name, String lagermenge, String haltbar_bis, String kilopreis, String gewicht_portion) {
		this.name = name;
		this.lagermenge = Integer.parseInt(lagermenge);
		this.portionspreis = Double.parseDouble(kilopreis) * Double.parseDouble(gewicht_portion);
		this.haltbar = haltbar_bis;
	}

	@Override
	public String toString() {
		return name + ",  " + portionspreis + "€/Portion,  " + haltbar;
	}
}