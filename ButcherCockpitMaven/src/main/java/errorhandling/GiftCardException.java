package errorhandling;

import java.util.ResourceBundle;

<<<<<<< Upstream, based on origin/master
/**
 *  GiftCardException dient der Fehlerbehandlung, im Falle von Schwierigkeiten beim Einlesen der Gutscheinkarte und dem damit einhergehenden Zahlungsvorgang.
 *	Sie soll dem Kunden eine Beschreibung der (möglichen) Fehlerursachen geben, sowie Änsatze, wie dieser weiter vorgehen kann. 
 */

public class GiftCardException extends AbstractButcherException{
	public GiftCardException(AbstractButcherException e) {
		super(e, ResourceBundle.getBundle("i18n/giftcard_exception/giftcard_exception_en").getString("title"), 
=======
public class GiftCardException extends PaymentButcherException {
	public GiftCardException() {
		super(null, ResourceBundle.getBundle("i18n/giftcard_exception/giftcard_exception_en").getString("title"),
>>>>>>> 41aac9b Exceptions im Payment abgestimmt
				ResourceBundle.getBundle("i18n/giftcard_exception/giftcard_exception_en").getString("error_text"));
<<<<<<< Upstream, based on origin/master
		
=======
>>>>>>> 41aac9b Exceptions im Payment abgestimmt
	}

}