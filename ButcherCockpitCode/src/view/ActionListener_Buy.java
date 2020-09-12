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
		JButton jb_source = (JButton) e.getSource();
		Automat at = (Automat) SwingUtilities.getRoot(jb_source);
//		Object parent;
//		do {
//			parent = jb_source.getParent();
//			System.out.println(parent.getClass());
//		} while ( !(parent instanceof JFrame));
//		System.out.println("Parent found");
//		Automat at = (Automat) parent;
		double gesamtpreis = at.getGesamtpreis();
		String[] options = { "Ja, bezahlen", "Nein, zurück" };
		int eingabe = JOptionPane.showOptionDialog(null, "Möchten Sie den Kaufvorgang abschließen und bezahlen?",
				"Bestätigung", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if (eingabe == 0) {
			JOptionPane.showMessageDialog(null,
					"Danke für Ihren Einkauf, der Kassenbetrag wurde von ihrer Gutscheinkarte abgezogen.", "Danke!",
					JOptionPane.INFORMATION_MESSAGE);
			Date date = new Date();
			Date time = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String sql_date = simpleDateFormat.format(date);
			SimpleDateFormat simpleTimeFormat = new SimpleDateFormat ("HH:mm:ss");
			String sql_time = simpleTimeFormat.format(time);
			DatabaseConnector.executeDBInsert(
					"INSERT INTO Verkaeufe( datum, uhrzeit, gesamtpreis) VALUES ('"+sql_date+"' , '"+sql_time+"',"+ gesamtpreis+");");
			System.out.println("Insert complete");
			System.exit(0);
		}

	}
}
