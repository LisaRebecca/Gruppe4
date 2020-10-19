package models;

import java.awt.*;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import Tools.MyTools;
import data.Database;
import errorhandling.AbstractButcherException;

/**
 * Die Klasse <code>Tile</code> stellt die Komponenten des {@link Cockpit} dar.
 * Sie ist ein <code>JScrollPane</code>, um die vollständige Darstellung der
 * Tabellen auf den Kacheln zu gewährleisten.
 */

@SuppressWarnings("serial")
public class Tile extends JScrollPane {
	
	private String tilename;
	
	public String getTilename() {
		return tilename;
	}

	public void setTilename(String tilename) {
		this.tilename = tilename;
	}

	/** Instanz der Klasse Border um den Kachelrahmen festzulegen
	 */
	Border margin = new LineBorder(Color.gray, 1);

	/** JViewports zur korrekten Anzeige der Tabellentitel und -inhalte
	 */
	JViewport jvp_title;
	JViewport jvp_table;
	
	/**
	 * Erzeugt eine Kachel mit Tabelleninhalt und dazugeh�riger überschrift, sowie
	 * festgelegter Hintergrundfarbe und Rahmen
	 * 
	 * @param select_statement MySQL-Statement, welches den Tabelleninhalt dieser
	 * Kachel bestimmt
	 */
	public Tile(String select_statement, String name){
		this.tilename = name;
		/**
		 * Ein <code>JTable</code> wird mit Daten aus der Datenbank gemäß des
		 * <code>select_statement</code>s gefüllt.
		 */
		JTable jt = null;
		try {
			jt = MyTools.resultSetToTable(Database.get().executeDBQuery(select_statement));
		} catch (AbstractButcherException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/**
		 * Optische Details: Tabellenrasterfarbe, Kachelhintergrund und
		 * Rahmenfarbe/-stärke der Kachel festgelegt
		 */
		jt.setGridColor(Color.orange);
		this.setBackground(Color.WHITE);
		this.setBorder(margin);

		/**
		 * <code>JViewPort</code> um die Spaltenbezeichner der Tabelle darzustellen.
		 * Hinweis: Da sich die Kopfzeile und Inhalt der Tabelle in unterschiedlichen
		 * ViewPorts befinden, bleibt die Kopfzeile beim vertikalen Scrollen unbewegt.
		 */
		jvp_title = new JViewport();
		jvp_title.add(jt.getTableHeader());
		this.setColumnHeader(jvp_title);

		/**
		 * <code>JViewPort</code> für den Inhalt der Tabelle.
		 */
		jvp_table = new JViewport();
		jvp_table.add(jt);
		this.setViewportView(jvp_table);
	}
}