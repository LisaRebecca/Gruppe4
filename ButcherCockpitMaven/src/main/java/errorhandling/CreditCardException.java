package errorhandling;

public class CreditCardException extends Exception{
	CreditCardException() {
		super("Ihre Kreditkartenzahlung ist leider fehlgeschlagen." 
				+ "H�ufigste Ursache: Eingabe eines falschen Pincodes."
				+ "Bitte versuchen Sie es erneut oder w�hlen Sie eine andere Zahlungsmethode." 
				+ "Wir entschuldigen uns f�r die Unannehmlichkeit.");
	}
}