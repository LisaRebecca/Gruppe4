package view;

import java.awt.*;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import controller.DatabaseConnector;

/**
 * Die Klasse Tile dient der einheitlichen Darstellungweise der im UserInterface
 * erzeugten Kachelinstanzen. Sie erbt von der Klasse JScrollPane, um die
 * �bersichtlichkeit der Tabellen auf den Kacheln zu gew�hrleisten.
 */
public class Tile extends JScrollPane {

	/**
	 * Instanz der Klasse Font um die Titelschriftart auf Arial und Schriftgr��e 18
	 * zu setzen
	 */
	Font font = new Font("Arial", Font.PLAIN, 18);

	/**
	 * Erzeugt eine Kachel mit Tabelleninhalt und dazugeh�riger �berschrift, sowie
	 * festgelegter Hintergrundfarbe und Rahmen
	 * 
	 * @param tileheader       definiert die dem Konstruktor zu �bergebene
	 *                         Kachel�berschrift
	 * @param select_statement legt das SQL-Statement fest, das den Tabelleninhalt
	 *                         der Kachel bestimmt
	 */

	public Tile(String select_statement) {

		/**
		 * der header Variablen wird der zugeh�rige String aus dem Konstruktoraufruf
		 * �bergeben
		 */

		/**
		 * Erzeugen der JTable-Instanz inklusive Datenbankabfrage �ber die Klasse
		 * DatabaseConnector
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
		 * Erzeugen eines JLabels jlbl_title, welches die Kachel�berschrift aus dem
		 * Konstruktor �bergeben bekommt Title wird die vorher definierte Schriftart
		 * font zugewiesen
		 */

		/**
		 * Jeweils zwei JViewport Instanzen f�r das Titellabel und die Tabelle der
		 * Kachel um beides in einer Kachel anzeigen zu k�nnen
		 */
		JViewport jvp_title = new JViewport();
		jvp_title.add(jt.getTableHeader());
		JViewport jvp_table = new JViewport();
		jvp_table.add(jt);

		/**
		 * Der JViewport jvp_title wird nun dem ColumnHeader hinzugef�gt und das
		 * jvp_table der View. So bewegt die vertikale Scollbar lediglich die Tabelle,
		 * w�hrend der Titel oben stehen bleibt.
		 */
		this.setColumnHeader(jvp_title);
		this.setViewportView(jvp_table);
	}
}