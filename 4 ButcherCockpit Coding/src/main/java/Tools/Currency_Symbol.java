package Tools;

public class Currency_Symbol {

	private static String currency_symbol = "€";

	/**
	 * Setzen des Währungssymbols
	 */

	public static String setCurrency_Symbol(String currency_symbol_new) {
		return currency_symbol = currency_symbol_new;
	}

	/**
	 * 
	 * @return Währungssymbol als String
	 */
	public static String getCurrency_Symbol() {
		return currency_symbol;
	}

}
