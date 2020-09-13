package Tools;

import java.text.NumberFormat;

public class MyTools {
	public static final NumberFormat currencyFormatter = NumberFormat.getInstance();

	/**
	 * Formatiert Zahlen in eine W�hrung
	 * @param d Wert, der in W�hrung umgewandelt werden soll
	 * @return den Eingabewert formatiert als W�hrung
	 */
	public static String formatAsCurrency(double d) {
		currencyFormatter.setMaximumFractionDigits(2);
		currencyFormatter.setMinimumFractionDigits(2);
		return currencyFormatter.format(d) + "�";
	}
}
