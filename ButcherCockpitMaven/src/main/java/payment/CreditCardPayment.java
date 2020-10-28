package payment;

import java.util.ResourceBundle;

import errorhandling.CreditCardException;
import errorhandling.SQLButcherException;
import payment.Payment;

/**
 * CreditCardPayment erbt von der abstrakten Klasse Payment und steht für den Sonderfall, dass der Kunde mit der Kreditkarte zahlen möchte.
 * Von dieser Entscheidung abhängig sind die Nachrichten, die der Kunde beim erfolgreichen Kauf erhält.
 *
 */

public class CreditCardPayment extends Payment {
	private final ResourceBundle language;

	public CreditCardPayment() {
		this.language = ResourceBundle.getBundle("i18n/creditcard/creditcard_en");
	}

	public String processPurchase(double gesamtpreis) throws CreditCardException, SQLButcherException {
		saveIntoDatabase(gesamtpreis);
		return this.language.getString("processing_message");
	}
}
