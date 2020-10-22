package Tools;

public class GiftCardException extends Exception{
	
	private final ResourceBundle language;
	
	GiftCardException() {
		
		this.language = ResourceBundle.getBundle("i18n/giftcard_exception/giftcard_exception_de");
		
		super(this.language.getString("error_text");
	}

}