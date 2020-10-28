package errorhandling;

import java.util.ResourceBundle;

public class CreditCardException extends AbstractButcherException{
	public CreditCardException() {
		super(null, ResourceBundle.getBundle("i18n/creditcard_exception/creditcard_exception_en").getString("title"), 
				ResourceBundle.getBundle("i18n/creditcard_exception/creditcard_exception_en").getString("error_text"));
		
	
	}
}