package payment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import data.Database;
import errorhandling.PaymentButcherException;
import errorhandling.SQLButcherException;

public abstract class Payment {

//**ActionListener Zahlungsmethode auswählen 
//Errorhandling und richtige Ausgabe je nach implementierender Klasse

	private static Payment payment;

	public static Payment get() {
		return Payment.payment;
	}

	public static void set(Payment payment) {
		Payment.payment = payment;
	}

	// **throws neue Exception?
	public abstract String processPurchase(double gesamtpreis) throws PaymentButcherException, SQLButcherException;

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