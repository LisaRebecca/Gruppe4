package errorhandling;

import java.util.ResourceBundle;

/**
 * GiftCardException dient der Fehlerbehandlung, im Falle von Schwierigkeiten
 * beim Einlesen der Gutscheinkarte und dem damit einhergehenden
 * Zahlungsvorgang. Sie soll dem Kunden eine Beschreibung der (möglichen)
 * Fehlerursachen geben, sowie Änsatze, wie dieser weiter vorgehen kann.
 */
public class GiftCardException extends PaymentButcherException {
	public GiftCardException() {
		super(null, ResourceBundle.getBundle("i18n/giftcard_exception/giftcard_exception_en").getString("title"),
				ResourceBundle.getBundle("i18n/giftcard_exception/giftcard_exception_en").getString("error_text"));
	}
}