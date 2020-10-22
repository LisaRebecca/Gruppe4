package Tools;

public class CreditCardException extends Exception{
	
	private final ResourceBundle language;
	
	CreditCardException() {
		this.language = ResourceBundle.getBundle("i18n/creditcard_exception/creditcard_exception_de");
		
		super(this.language.getString("error_text"));
	}
}