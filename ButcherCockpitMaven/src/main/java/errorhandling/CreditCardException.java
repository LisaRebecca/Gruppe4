package errorhandling;

import java.util.ResourceBundle;

public class CreditCardException extends AbstractButcherException{
	public CreditCardException() {
		super(null, ResourceBundle.getBundle("i18n/creditcard_exception/creditcard_exception_en").getString("title"), 
				ResourceBundle.getBundle("i18n/creditcard_exception/creditcard_exception_en").getString("error_text"));
		
		
//		super(  "Ihre Kreditkartenzahlung ist leider fehlgeschlagen." 
//				+ "Häufigste Ursache: Eingabe eines falschen Pincodes."
//				+ "Bitte versuchen Sie es erneut oder wählen Sie eine andere Zahlungsmethode." 
//				+ "Wir entschuldigen uns für die Unannehmlichkeit.", errorMessage);
	}
}