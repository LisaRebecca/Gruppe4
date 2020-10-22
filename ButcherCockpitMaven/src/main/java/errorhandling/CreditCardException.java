package errorhandling;

public class CreditCardException extends Exception{
	CreditCardException() {
		super("Ihre Kreditkartenzahlung ist leider fehlgeschlagen." 
				+ "Häufigste Ursache: Eingabe eines falschen Pincodes."
				+ "Bitte versuchen Sie es erneut oder wählen Sie eine andere Zahlungsmethode." 
				+ "Wir entschuldigen uns für die Unannehmlichkeit.");
	}
}