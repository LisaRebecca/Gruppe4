package errorhandling;

import java.util.ResourceBundle;

public class GiftCardException extends AbstractButcherException{
	public GiftCardException() {
		super(null, ResourceBundle.getBundle("i18n/giftcard_exception/giftcard_exception_en").getString("title"), 
				ResourceBundle.getBundle("i18n/giftcard_exception/giftcard_exception_en").getString("error_text"));
		
//		super("Die Zahlung mit Ihrer Gutscheinkarte konnte nicht abgeschlossen werden." 
//				+ "Häufigste Ursache: Nicht genügend Guthaben"
//				+ "Bitte fragen Sie bei Gelegenheit einen verfügbaren Mitarbeiter oder wählen Sie eine andere Zahlungsmethode." 
//				+ "Wir entschuldigen uns für die Unannehmlichkeit.");
	}

}