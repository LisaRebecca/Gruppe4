package payment;

import errorhandling.GiftCardException;

public class GiftCard extends Payment{
	
	public String processPurchase() throws GiftCardException{
		return "Danke für Ihren Einkauf, der Kassenbetrag wurde von ihrer Gutscheinkarte abgezogen.";
		
	}
	
}