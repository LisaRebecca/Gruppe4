package errorhandling;

import java.util.ResourceBundle;

/**
 *  CreditCardException dient der Fehlerbehandlung, im Falle von Schwierigkeiten beim Einlesen der Kreditkarte und dem damit einhergehenden Zahlungsvorgang.
 *	Sie soll dem Kunden eine Beschreibung der (möglichen) Fehlerursachen geben, sowie Änsatze, wie dieser weiter vorgehen kann. 
 */

public class CreditCardException extends AbstractButcherException{
	public CreditCardException() {
		super(null, ResourceBundle.getBundle("i18n/creditcard_exception/creditcard_exception_en").getString("title"), 
				ResourceBundle.getBundle("i18n/creditcard_exception/creditcard_exception_en").getString("error_text"));
		
	
	}
}