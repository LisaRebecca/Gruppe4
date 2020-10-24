package payment;

import java.util.ResourceBundle;


import errorhandling.GiftCardException;
import payment.Payment;

public class GiftCard extends Payment{
	
	private final ResourceBundle language;
	
	public GiftCard () {
		this.language = ResourceBundle.getBundle("i18n/giftcard/giftcard_en");
	}
	
	public String processPurchase() throws GiftCardException{
		return this.language.getString("processing_message");
		
	}
	
}