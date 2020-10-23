package errorhandling;

public class GiftCardException extends Exception{
	GiftCardException() {
		super("Die Zahlung mit Ihrer Gutscheinkarte konnte nicht abgeschlossen werden." 
				+ "H�ufigste Ursache: Nicht gen�gend Guthaben"
				+ "Bitte fragen Sie bei Gelegenheit einen verf�gbaren Mitarbeiter oder w�hlen Sie eine andere Zahlungsmethode." 
				+ "Wir entschuldigen uns f�r die Unannehmlichkeit.");
	}

}