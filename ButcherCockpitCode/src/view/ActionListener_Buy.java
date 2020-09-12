package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import controller.DatabaseConnector;

public class ActionListener_Buy implements ActionListener {
	/**
	 * Wenn der Button gedrückt wird, sollen eine OptionPane angezeigt werden, mit
	 * zwei Buttons die jeweils die Texte "Ja, bezahlen" oder "Nein, zurück"
	 * enthalten. Sowie darüber die Frage: "Möchten Sie den Kaufvorgang
	 * abschließen und bezahlen". Wird der Ja-Button gedrückt, soll der Benutzer
	 * eine Bestätigung über die Bestellung erhalten. Wird der Nein-Button
	 * gedrückt, soll das Fenster geschlossen werden.
	 * 
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		String[] options = { "Ja, bezahlen", "Nein, zurück" };
		int eingabe = JOptionPane.showOptionDialog(null, "Möchten Sie den Kaufvorgang abschließen und bezahlen?",
				"Bestätigung", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if (eingabe == 0) {
			JOptionPane.showMessageDialog(null,
					"Danke für Ihren Einkauf, der Kassenbetrag wurde von ihrer Gutscheinkarte abgezogen.", "Danke!",
					JOptionPane.INFORMATION_MESSAGE);
			Date date = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
			simpleDateFormat.format(date);
			DatabaseConnector.executeDBQuery(
					"(INSERT INTO Verkaeufe(verkauf_id, datum, uhrzeit, gesamtpreis) VALUES ( 1,'2020-09-10', '16:33:10', 10.23);");
			System.exit(0);
		}

	}
}
