package view;

import java.awt.*;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import controller.DatabaseConnector;

/**
 * Die Klasse <code>Tile</code> stellt die Komponenten des {@link Cockpit} dar.
 * Sie ist ein <code>JScrollPane</code>, um die vollst�ndige Darstellung der
 * Tabellen auf den Kacheln zu gew�hrleisten.
 */

@SuppressWarnings("serial")
public class Tile extends JScrollPane {

//	/**
//	 * Instanz der Klasse Font um die Titelschriftart auf Arial und Schriftgr��e 18
//	 * zu setzen
//	 */
//	Font font = new Font("Arial", Font.PLAIN, 18);

	/**
	 * Erzeugt eine Kachel mit Tabelleninhalt und dazugeh�riger �berschrift, sowie
	 * festgelegter Hintergrundfarbe und Rahmen
	 * 
	 * @param select_statement MySQL-Statement, welches den Tabelleninhalt dieser
	 *                         Kachel bestimmt
	 */
	public Tile(String select_statement) {
		/**
		 * Ein <code>JTable</code> wird mit Daten aus der Datenbank gem�� des
		 * <code>select_statement</code>s gef�llt.
		 */
		JTable jt = DatabaseConnector.executeDBQuery(select_statement);

		/**
		 * Optische Details: Tabellenrasterfarbe, Kachelhintergrund und
		 * Rahmenfarbe/-st�rke der Kachel festgelegt
		 */
		jt.setGridColor(Color.orange);
		this.setBackground(Color.WHITE);
		Border margin = new LineBorder(Color.gray, 1);
		this.setBorder(margin);

		/**
		 * Jeweils zwei JViewport Instanzen f�r das Titellabel und die Tabelle der
		 * Kachel um beides in einer Kachel anzeigen zu k�nnen
		 */
		/**
		 * <code>JViewPort</code> um die Spaltenbezeichner der Tabelle darzustellen.
		 * Hinweis: Da sich die Kopfzeile und Inhalt der Tabelle in unterschiedlichen
		 * ViewPorts befinden, bleibt die Kopfzeile beim vertikalen Scrollen unbewegt.
		 */
		JViewport jvp_title = new JViewport();
		jvp_title.add(jt.getTableHeader());
		this.setColumnHeader(jvp_title);

		/**
		 * <code>JViewPort</code> f�r den Inhalt der Tabelle.
		 */
		JViewport jvp_table = new JViewport();
		jvp_table.add(jt);
		this.setViewportView(jvp_table);
	}
}