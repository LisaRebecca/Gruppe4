package payment;

import java.util.ResourceBundle;

import errorhandling.GiftCardException;
import errorhandling.SQLButcherException;
import payment.Payment;

/**
 * GiftCardPayment erbt von der abstrakten Klasse Payment und steht für den Sonderfall, dass der Kunde mit einer Gutscheinkarte zahlen möchte.
 * Von dieser Entscheidung abhängig sind die Nachrichten, die der Kunde beim erfolgreichen Kauf erhält.
 *
 */

public class GiftCardPayment extends Payment {

	private final ResourceBundle language;

	public GiftCardPayment() {
		this.language = ResourceBundle.getBundle("i18n/giftcard/giftcard_en");
	}

	public String processPurchase(double gesamtpreis) throws GiftCardException, SQLButcherException {
		saveIntoDatabase(gesamtpreis);
		return this.language.getString("processing_message");
	}
}