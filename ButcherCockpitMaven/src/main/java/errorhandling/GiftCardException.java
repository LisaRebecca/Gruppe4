package errorhandling;

import java.util.ResourceBundle;

public class GiftCardException extends AbstractButcherException{
	public GiftCardException() {
		super(null, ResourceBundle.getBundle("i18n/giftcard_exception/giftcard_exception_en").getString("title"), 
				ResourceBundle.getBundle("i18n/giftcard_exception/giftcard_exception_en").getString("error_text"));
		
	}

}