package tools;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import Tools.Currency_Symbol;

public class Currency_Symbol_Test {

	private static String currency_symbol_test_pfund = "£";

	@Test
	public void getCurrency_SymbolTest() {
		assertEquals("£", Currency_Symbol.setCurrency_Symbol("£"));
	}

}
