package errorhandling;

public class GiftCardException extends Exception{
	public GiftCardException() {
		super("Die Zahlung mit Ihrer Gutscheinkarte konnte nicht abgeschlossen werden." 
				+ "Häufigste Ursache: Nicht genügend Guthaben"
				+ "Bitte fragen Sie bei Gelegenheit einen verfügbaren Mitarbeiter oder wählen Sie eine andere Zahlungsmethode." 
				+ "Wir entschuldigen uns für die Unannehmlichkeit.");
	}

}