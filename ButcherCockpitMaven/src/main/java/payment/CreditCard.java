package payment;

import java.util.ResourceBundle;


import errorhandling.CreditCardException;
import payment.Payment;

public class CreditCard extends Payment{
	private final ResourceBundle language;
	
	public CreditCard() {
		this.language = ResourceBundle.getBundle("i18n/creditcard/creditcard_en");
	}
	
	public String processPurchase() throws CreditCardException{
		return this.language.getString("processing_message");
		
	}
}
