package controller;

import Tools.CreditCardException;

public class CreditCard extends Payment{
	private final ResourceBundle language;
	
	public CreditCard() {
		this.language = ResourceBundle.getBundle("i18n/creditcard/creditcard_de");
	}
	
	public String processPurchase() throws CreditCardException{
		return this.language.getString("processing_message");
		
	}
}
