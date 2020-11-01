package payment;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.UUID;

import data.Database;
import errorhandling.AbstractButcherException;
import errorhandling.PaymentButcherException;
import errorhandling.SQLButcherException;

/**
 * Die abstrakte Klasse Payment beschreibt die Kauf- und Zahlungsvorgänge, beim Kauf von Produkten aus dem Automaten. 
 *
 */

public abstract class Payment {

//**ActionListener Zahlungsmethode auswählen 
//Errorhandling und richtige Ausgabe je nach implementierender Klasse

	private static Payment payment = new GiftCardPayment();

	public static Payment get() {
		return Payment.payment;
	}

	public static void set(Payment payment) {
		Payment.payment = payment;
	}
	
	/**
	 * Methode zur Zahlungsabwicklung
	 * 
	 * @param gesamtpreis Summe der Einzelpreise der beim Automaten ausgewählten Produkte
	 * @return gibt in den erbenden Klassen einen entsprechenden Text zur Bestätigung zurück
	 * @throws AbstractButcherException bzw. eine Sonderform dieser: je nach Zahlungsart eine CreditCardException oder GiftCardException
	 * @throws SQLButcherException wenn das Schreiben in die Verkaufstabelle der Datenbank fehlschlägt
	 */

	public abstract String processPurchase(double gesamtpreis) throws AbstractButcherException, SQLButcherException;

	protected void saveIntoDatabase(double gesamtpreis) throws SQLButcherException {
		// generieren eines Universally Unique Identifiers für jeden Einkauf
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replace("-", "");

		// Speichern des aktuellen Zeitstempels
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String sql_date = simpleDateFormat.format(date);

		Date time = new Date();
		SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");
		String sql_time = simpleTimeFormat.format(time);

		// Der Einkauf wird als Statistik in der Datenbank hinterlegt.

		Database.get()
				.executeDBInsert("INSERT INTO Verkaeufe( verkauf_id, datum, uhrzeit, gesamtpreis) VALUES ( UNHEX('"
						+ uuid + "'), '" + sql_date + "', '" + sql_time + "', " + gesamtpreis + ");");
	}
}