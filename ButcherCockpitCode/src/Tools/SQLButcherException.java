package Tools;

public class SQLButcherException extends AbstractButcherException {
	
	public SQLButcherException(Exception e) {
		super(e, "Datenbankfehler", "Bitte wenden Sie sich an einen Mitarbeiter");
	}
	
}
