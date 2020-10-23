package errorhandling;

import java.sql.SQLException;

public class SQLButcherException extends AbstractButcherException {
	
	public SQLButcherException(SQLException e) {
		super(e, "Datenbankfehler", "Bitte wenden Sie sich an einen Mitarbeiter");
	}
	
}
