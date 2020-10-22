package Tools;

public class SQLButcherException extends AbstractButcherException {
	
	private final ResourceBundle language;
	
	public SQLButcherException(Exception e) {
		
		this.language = ResourceBundle.getBundle("i18n/sqlbutcher_exception/sqlbutcher_exception_de");
		
		super(e, this.language.getString("error"), this.language.getString("error_message"));
	}
	
}
