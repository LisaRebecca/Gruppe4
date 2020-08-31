package classes;

class Portion {
	String name;
	double portionspreis;
	String haltbar;

	public Portion(String name, String haltbar_bis, String kilopreis, String gewicht_portion) {
		this.name = name;
		this.portionspreis = Double.parseDouble(kilopreis) * Double.parseDouble(gewicht_portion);
		this.haltbar = haltbar_bis;
	}

	@Override
	public String toString() {
		return name + ", " + portionspreis + "€/pckg, " + haltbar;
	}
}