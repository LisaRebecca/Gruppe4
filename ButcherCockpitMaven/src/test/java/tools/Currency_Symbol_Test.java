package tools;

import Tools.Currency_Symbol;
import junit.framework.Assert;

public class Currency_Symbol_Test {

	private static String currency_symbol_test_pfund = "£";

	public void getCurrency_SymbolTest() {

		Assert.assertEquals("£", Currency_Symbol.setCurrency_Symbol("£"));
	}

}
