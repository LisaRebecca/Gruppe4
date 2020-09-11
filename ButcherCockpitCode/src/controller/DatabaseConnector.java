package controller;

import java.sql.*;
import java.util.Observable;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Der {@link DatabaseConnector} stellt die Schnittstelle zur Datenbank zur
 * Verfügung. Die Klasse kann Requests an die Datenbank senden um Daten
 * auszulesen. Zusätzlich kann diese Klasse auch Tupel in Tabellen einfügen.
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
	 * @param select_statement Eine Datenbankanfrage im MySQL-Syntax
	 * @return die angefragten Daten in der Form eines {@link JTable}
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
	 * Hilfsmethode, welche ein <code>ResultSet</code> in einen <code>JTable</code>
	 * umwandelt. Diese Methode erlaubt es, das Ergebnis einer Datenbankabfrage
	 * mithilfe von <code>JTables</code> für den Anwender zu visualisieren. Die
	 * Spalten des JTables werden mit dem jeweiligen Bezeichner des Attributs der
	 * Datenbanktabelle benannt. Falls ein Aliasname für ein Attribut gesetzt wurde
	 * wird jedoch dieser bevorzugt.
	 * 
	 * @param result das Ergebnis einer Datenbankabfrage
	 * @return ein {@link DefaultTableModel} welches Daten aus der Datenbank
	 *         beinhaltet
	 * @throws SQLException beim auslesen der Daten kam es zu einem Fehler
	 */
	public static JTable buildJTable(ResultSet result) throws SQLException {

		/**
		 * Metadaten des ResultSets
		 */
		ResultSetMetaData metaData = result.getMetaData();
		/**
		 * Bezeichner der Spalten des Tabellen-Modells
		 */
		int columnCount = metaData.getColumnCount();
		Vector<String> columnLabels = new Vector<String>();

		for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
			columnLabels.add(metaData.getColumnLabel(columnIndex));
		}

		Vector<Vector<String>> rows = new Vector<Vector<String>>();
		Vector<String> singleRow;
		while (result.next()) {
			singleRow = new Vector<String>();
			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				singleRow.add("" + result.getObject(columnIndex));
			}
			rows.add(singleRow);
		}
		return new JTable(new DefaultTableModel(rows, columnLabels));
	}
}