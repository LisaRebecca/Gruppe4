package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import controller.DatabaseConnector;

/**
 * ActionListener, der das Event-Handlung f�r den KaufButton �bernimmt. Hierbei
 * wird ein neues Fenster mit weiteren Buttons eingeblendet, um aus Kundensicht
 * entweder den Kaufvorgang abzuschlie�en oder abzubrechen.
 *
 */

public class ActionListener_Buy implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jb_source = (JButton) e.getSource();

		// Finden des Automaten, welcher den Kaufen-Button enth�lt.
		Automat at = (Automat) SwingUtilities.getRoot(jb_source);

		double gesamtpreis = at.getGesamtpreis();

		// Zuerst wird der Kunde nach Best�tigung gefragt.
		String[] options = { "Ja, bezahlen", "Nein, zurueck" };
		int eingabe = JOptionPane.showOptionDialog(null, "Moechten Sie den Kaufvorgang abschliessen und bezahlen?",
				"Best�tigung", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

		// Der Dialog schlie�t sich, der Kunde kann weiter einkaufen
		if (eingabe == 1) {
		}
		// Nur falls er den Vorgang abschlie�en will erscheint ein neuer Dialog.
		if (eingabe == 0) {
			JOptionPane.showMessageDialog(null,
					"Danke fuer Ihren Einkauf, der Kassenbetrag wurde von ihrer Gutscheinkarte abgezogen.", "Danke!",
					JOptionPane.INFORMATION_MESSAGE);

			// Speichern des aktuellen Zeitstempels
			Date date = new Date();
			Date time = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String sql_date = simpleDateFormat.format(date);
			SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");
			String sql_time = simpleTimeFormat.format(time);

			// Der Einkauf wird als Statistik in der Datenbank hinterlegt.
			DatabaseConnector.executeDBInsert("INSERT INTO Verkaeufe( datum, uhrzeit, gesamtpreis) VALUES ('" + sql_date
					+ "', '" + sql_time + "'," + gesamtpreis + ");");
			
			// Der Automat wird geschlossen, der Einkauf ist beendet.
			System.exit(0);
		}
	}

}
