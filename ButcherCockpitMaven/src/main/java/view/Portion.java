package view;

import java.text.NumberFormat;
import java.util.Objects;

/**
 * Die Klasse Portion modelliert ein abgepacktes Produkt, welches sich in der
 * Metzgerei oder im Kühlautomat auf Lager befinden kann.
 */
public class Portion {

	private String name;
	private int lagermenge;
	private double kilopreis;
	private double portionsgewichtKG;
	private String haltbarBis;

	@Override
	public int hashCode() {
		return Objects.hash(name, lagermenge, kilopreis, portionsgewichtKG, haltbarBis);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Portion other = (Portion) obj;
		if (haltbarBis == null) {
			if (other.haltbarBis != null)
				return false;
		} else if (!haltbarBis.equals(other.haltbarBis))
			return false;
		if (Double.doubleToLongBits(kilopreis) != Double.doubleToLongBits(other.kilopreis))
			return false;
		if (lagermenge != other.lagermenge)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(portionsgewichtKG) != Double.doubleToLongBits(other.portionsgewichtKG))
			return false;
		return true;
	}

	/**
	 * Der Standard-Konstruktor setzt die Instanzvariablen auf Standard-Werte, damit
	 * keine <code>NullPointerException</code> auftritt, falls in einer der
	 * set-Methoden oder dem anderen Konstruktor eine
	 * <code>NumberFormatException</code> eintrat.
	 */
	public Portion() {
		this.name = "no product name specified";
		this.lagermenge = 0;
		this.kilopreis = 0;
		this.portionsgewichtKG = 0;
		this.haltbarBis = "0000-00-00";
	}

	/**
	 * Konstruktor, welcher die Instanzvariablen aus String-Objekten ausliest.
	 * 
	 * @param name              Produktname
	 * @param lagermenge        zurzeit gelagerte Menge
	 * @param haltbarBis        Mindesthaltbarkeitsdatum
	 * @param kilopreis         Preis pro Kilo
	 * @param portionsgewichtKG das Gewicht einer Portion des Pordukts in Kilogramm
	 */
	public Portion(String name, String lagermenge, String haltbarBis, String kilopreis, String portionsgewichtKG) {
		setName(name);
		setLagermenge(lagermenge);
		setHaltbarBis(haltbarBis);
		setKilopreis(kilopreis);
		setPortionsgewichtKG(portionsgewichtKG);
	}

	public String getName() {
		return name;
	}

	/**
	 * @return das Gewicht einer Portion in Kilogramm
	 */
	public double getPortionsgewichtKG() {
		return portionsgewichtKG;
	}

	/**
	 * @return das Gewicht einer Portion in Gramm
	 */
	public int getPortionsgewichtGramm() {
		NumberFormat formatter = NumberFormat.getInstance();
		formatter.setMaximumFractionDigits(2);
		return Integer.parseInt("" +(portionsgewichtKG * 1000));
	}

	public int getLagermenge() {
		return lagermenge;
	}

	/**
	 * @return Preis einer Portion, indem der Kilopreis mit dem Gewicht der Portion in KG multipliziert wird
	 */
	public double getPortionspreis() {
		return this.kilopreis * this.portionsgewichtKG;
	}

	public double getKilopreis() {
		return kilopreis;
	}

	public String getHaltbarBis() {
		return haltbarBis;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLagermenge(int lagermenge) {
		this.lagermenge = lagermenge;
	}

	public void setLagermenge(String lagermenge) {
		this.lagermenge = Integer.parseInt(lagermenge);
	}

	public void setKilopreis(double kilopreis) {
		this.kilopreis = kilopreis;
	}

	public void setKilopreis(String kilopreis) {
		this.kilopreis = Double.parseDouble(kilopreis);
	}

	public void setPortionsgewichtKG(double portionsgewichtKG) {
		this.portionsgewichtKG = portionsgewichtKG;
	}

	public void setPortionsgewichtKG(String portionsgewichtKG) {
		this.portionsgewichtKG = Double.parseDouble(portionsgewichtKG);
	}

	public void setHaltbarBis(String haltbarBis) {
		this.haltbarBis = haltbarBis;
	}

	
}