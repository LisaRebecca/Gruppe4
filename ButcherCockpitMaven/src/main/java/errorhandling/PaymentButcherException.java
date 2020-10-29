package errorhandling;

import java.util.ResourceBundle;

public class PaymentButcherException extends AbstractButcherException{

	public PaymentButcherException (Exception e) {
		super (e, ResourceBundle.getBundle("i18n/giftcard_exception/giftcard_exception_en").getString("title"), 
				ResourceBundle.getBundle("i18n/giftcard_exception/giftcard_exception_en").getString("error_text"));
	}
	
	public PaymentButcherException(Exception e, String title, String message) {
		super(e, title, message);
	}
}
