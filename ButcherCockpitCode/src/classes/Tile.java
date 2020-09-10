package classes;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

/** Die Klasse Tile dient der einheitlichen Darstellungweise der im UserInterface erzeugten Kachelinstanzen. 
 * Sie erbt von der Klasse JScrollPane, um die Übersichtlichkeit der Tabellen auf den Kacheln zu gewährleisten.
 */
public class Tile extends JScrollPane {
	
	/** Instanz der Klasse Font um die Titelschriftart auf Arial und Schriftgröße 18 zu setzen
	 */
	private Font font = new Font("Arial",Font.PLAIN, 18);
	
	/** Instanz der Klasse Border um den Rahmen für die Kacheln festzulegen
	 */
	private Border margin = new LineBorder(Color.gray,1);
	
	/** Titel-JLabel
	 */
	JLabel jlbl_title;
	
	/** Jeweils eine JViewport Instanz für das Titellabel und die Tabelle der Kachel um beides in einer Kachel anzeigen zu können
	 */
	JViewport jvp_title;
	JViewport jvp_table;
	
	
	/** Erzeugt eine Kachel mit Tabelleninhalt und dazugehöriger Überschrift, sowie festgelegter Hintergrundfarbe und Rahmen
	 * 
	 * @param tileheader definiert die dem Konstruktor zu übergebene Kachelüberschrift
	 * @param select_statement legt das SQL-Statement fest, das den Tabelleninhalt der Kachel bestimmt
	 */
	
    public Tile (String tileheader, String select_statement) {
    	
    	/** der header Variablen wird der zugehörige String aus dem Konstruktoraufruf übergeben
    	 */
    	
    	/** Erzeugen der JTable-Instanz inklusive Datenbankabfrage über die Klasse DatabaseConnector
    	 */
    	JTable jt = 
    				DatabaseConnector.executeDBQuery(select_statement); 
    	
    	/** Optische Details: Tabellenrasterfarbe, Kachelhintergrund und Rahmenfarbe/-stärke der Kachel festgelegt
    	 */
    	jt.setGridColor(Color.orange);
    	this.setBackground(Color.WHITE);
    	this.setBorder(margin);
    	
    	/** Titel-JLabel bekommt die Kachelüberschrift aus dem Konstruktor übergeben
    	 * Title wird die vorher definierte Schriftart font zugewiesen
    	 */
		jlbl_title = new JLabel(tileheader);
		jlbl_title.setFont(font);
    	
		/** Den JViewPorts werden die jeweiligen Elemente zugewiesen
		 */
    	jvp_title = new JViewport();
    	jvp_title.add(jlbl_title);
    	jvp_title.add(jt.getTableHeader());
    	jvp_table = new JViewport();
    	jvp_table.add(jt);

    	/**Der JViewport jvp_title wird nun dem ColumnHeader hinzugefügt und das jvp_table der View.
    	 * So bewegt die vertikale Scollbar lediglich die Tabelle, während der Titel oben stehen bleibt.
    	 */
    	this.setColumnHeader(jvp_title);
    	this.setViewportView(jvp_table);
    }
}