package errorhandling;

public class PaymentButcherException extends AbstractButcherException{

	public PaymentButcherException (Exception e) {
		super (e, "Fehler beim Kaufvorgang", "Bitte wenden Sie sich an einen Mitarbeiter");
	}
}
