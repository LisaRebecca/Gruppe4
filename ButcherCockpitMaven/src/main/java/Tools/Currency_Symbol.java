package Tools;

public class Currency_Symbol {

	private static String currency_symbol = "€";

	public static String setCurrency_Symbol(String currency_symbol_new) {
		return currency_symbol = currency_symbol_new;
	}

	public static String getCurrency_Symbol() {

		return currency_symbol;
	}

}
