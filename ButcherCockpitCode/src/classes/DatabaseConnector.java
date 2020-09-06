package classes;

import java.sql.*;
import java.util.Observable;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Der {@link DatabaseConnector} stellt die Schnittstelle zur Datenbank zur
 * Verfügung. Die Klasse kann Requests an die Datenbank senden und Daten
 * empfangen.
 * 
 * @author I518232
 *
 */
public class DatabaseConnector {
	/**
	 * Verbindung zur Datenbank
	 */
	static Connection conn = DatabaseConnection.getDBConnection();

	/**
	 * Eine Anfrage an die in {@link DatabaseConnection} angebundene Datenbank kann
	 * gesendet werden.
	 * 
	 * @param select_statement SQL-SELECT-Statement
	 * @return die angefragten Daten in der Form eines {@link DefaultTableModel}
	 */
	public static JTable executeDBQuery(String select_statement) {
		JTable table = null;
		ResultSet result = null;
		try {
			result = conn.createStatement().executeQuery(select_statement);
		} catch (SQLException sql) {
			System.err.print("Error while executing statement: ");
			System.err.println(select_statement);
		}
		try {
			table = buildJTable(result);
		} catch (SQLException sql) {
			System.err.print("Table Model could not be built.");
		}
		return table;
	}

	/**
	 * Hilfsmethode, welche ein <code>ResultSet</code> in ein
	 * <code>DefaultTableModel</code> umwandelt. Diese Methode erlaubt es, das
	 * Ergebnis einer Datenbankabfrage mithilfe von <code>JTables</code> zu
	 * visualisieren.
	 * 
	 * @param rs ein ResultSet, das Ergebnis einer Datenbankabfrage
	 * @return ein {@link DefaultTableModel} welches Daten aus der Datenbank
	 *         beinhaltet
	 * @throws SQLException beim auslesen der Daten kam es zu einem Fehler
	 */
	public static JTable buildJTable(ResultSet rs) throws SQLException {
		
		/**
		 * Metadaten des ResultSets
		 */
		/**
		 * Bezeichner der Spalten des Tabellen-Modells
		 */
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();
		Vector<String> columnLabels = new Vector<String>();

		for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
			columnLabels.add(metaData.getColumnLabel(columnIndex));
		}

		Vector<Vector<String>> rows = new Vector<Vector<String>>();
		Vector<String> singleRow;
		while (rs.next()) {
			singleRow = new Vector<String>();
			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				singleRow.add(""+rs.getObject(columnIndex));
			}
			rows.add(singleRow);
		}
		return new JTable(new DefaultTableModel(rows, columnLabels));
	}
}