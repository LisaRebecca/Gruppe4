package errorhandling;

import java.sql.SQLException;
import java.util.ResourceBundle;
/**
 * Exception die bei Fehlfunktionen in Verbindung mit der Datenbank auftritt.
 * @author a-sch
 *
 */
public class SQLButcherException extends AbstractButcherException {
	
	public SQLButcherException(SQLException e) {
		super (e, ResourceBundle.getBundle("i18n/sqlbutcher_exception/sqlbutcher_exception_en").getString("error"), 
				ResourceBundle.getBundle("i18n/sqlbutcher_exception/sqlbutcher_exception_en").getString("error_message"));
	}
	
}
