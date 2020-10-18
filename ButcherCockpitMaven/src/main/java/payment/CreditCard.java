package payment;

import errorhandling.CreditCardException;

public class CreditCard extends Payment{
	public String processPurchase() throws CreditCardException{
		return "Danke für Ihren Einkauf, Ihre Kreditkartenzahlung wurde erfolgreich abgeschlossen.";
		
	}
}
