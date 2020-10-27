package errorhandling;

import java.sql.SQLException;
import java.util.ResourceBundle;

public class SQLButcherException extends AbstractButcherException {
	
	public SQLButcherException(SQLException e) {
		//super(e, "Datenbankfehler", "Bitte wenden Sie sich an einen Mitarbeiter");
		super (e, ResourceBundle.getBundle("i18n/sqlbutcher_exception/sqlbutcher_exception_en").getString("error"), 
				ResourceBundle.getBundle("i18n/sqlbutcher_exception/sqlbutcher_exception_en").getString("error_message"));
	}
	
}
