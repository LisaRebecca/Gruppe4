package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import controller.DatabaseConnector;

/**
 * ActionListener, der das Event-Handlung für den KaufButton übernimmt. Hierbei
 * wird ein neues Fenster mit weiteren Buttons eingeblendet, um aus Kundensicht
 * entweder den Kaufvorgang abzuschließen oder abzubrechen.
 *
 */

public class ActionListener_Buy implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jb_source = (JButton) e.getSource();

		/**
		 * Finden des Automaten, welcher den Kaufen-Button enthält.
		 */
		Automat at = (Automat) SwingUtilities.getRoot(jb_source);

		double gesamtpreis = at.getGesamtpreis();

		/**
		 * Zuerst wird der Kunde nach Bestätigung gefragt.
		 */
		String[] options = { "Ja, bezahlen", "Nein, zurÃ¼ck" };
		int eingabe = JOptionPane.showOptionDialog(null, "MÃ¶chten Sie den Kaufvorgang abschlieÃŸen und bezahlen?",
				"Bestätigung", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

		/**
		 * Der Dialog schließt sich, der Kunde kann weiter einkaufen
		 */
		if (eingabe == 1) {
		}
		/**
		 * Nur falls er den Vorgang abschließen will erscheint ein neuer Dialog.
		 */
		if (eingabe == 0) {
			JOptionPane.showMessageDialog(null,
					"Danke fÃ¼r Ihren Einkauf, der Kassenbetrag wurde von ihrer Gutscheinkarte abgezogen.", "Danke!",
					JOptionPane.INFORMATION_MESSAGE);

			// generieren eines Universally Unique Identifiers für jeden Einkauf
			String uuid = java.util.UUID.randomUUID().toString();
			uuid = uuid.replace("-", "");
			
			// Speichern des aktuellen Zeitstempels
			
			Date date = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String sql_date = simpleDateFormat.format(date);

			Date time = new Date();
			SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");
			String sql_time = simpleTimeFormat.format(time);

			// Der Einkauf wird als Statistik in der Datenbank hinterlegt.
			DatabaseConnector.executeDBInsert("INSERT INTO Verkaeufe( verkauf_id, datum, uhrzeit, gesamtpreis) VALUES ( UNHEX('"+uuid+"'), '" + sql_date
					+ "', '" + sql_time + "', " + gesamtpreis + ");");
			
			
			// Der Automat wird geschlossen, der Einkauf ist beendet
			System.exit(0);
		}
	}

}
