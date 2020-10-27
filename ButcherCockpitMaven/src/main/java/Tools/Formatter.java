package Tools;

import java.text.NumberFormat;

public class Formatter {
	public static final NumberFormat currencyFormatter = NumberFormat.getInstance();

	/**
	 * Formatiert Zahlen in eine Währung
	 * 
	 * @param d Wert, der in Währung umgewandelt werden soll
	 * @return den Eingabewert formatiert als Währung
	 */
	public static String formatAsCurrency(double d) {
		currencyFormatter.setMaximumFractionDigits(2);
		currencyFormatter.setMinimumFractionDigits(2);
		return currencyFormatter.format(d) + Currency_Symbol.getCurrency_Symbol();
	}
}
