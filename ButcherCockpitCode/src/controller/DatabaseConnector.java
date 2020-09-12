package controller;

import java.sql.*;
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
	private static Connection conn = DatabaseConnection.getDBConnection();
	private static Object[][] emptyRowData = {{"###"}};
	private static Object[] emptyColumnNames = {"###"};
	private static JTable emptyTable = new JTable(emptyRowData, emptyColumnNames);

	/**
	 * Eine Anfrage an die in {@link DatabaseConnection} angebundene Datenbank kann
	 * gesendet werden.
	 * 
	 * @param select_statement Eine Datenbankanfrage im MySQL-Syntax
	 * @return die angefragten Daten in der Form eines {@link JTable}
	 */
	public static JTable executeDBQuery(String select_statement) {
		ResultSet result = null;
		try {
			result = conn.createStatement().executeQuery(select_statement);
			return buildJTable(result);
		} catch (SQLException sql) {
			sql.printStackTrace();
			System.err.println("Error in MySQL Synax: "+select_statement);
			return emptyTable;
		}
	}
	
	public static void executeDBInsert (String insert_statement) {
		try {
			conn.createStatement().executeQuery(insert_statement);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Error while executing statement: '"+insert_statement+"'");
		}
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
	public static JTable buildJTable(ResultSet result) {
		Vector<Vector<String>> rows;
		Vector<String> columnLabels;
		
		try {

			/**
			 * Metadaten des ResultSets
			 */
			ResultSetMetaData metaData = result.getMetaData();
			/**
			 * Bezeichner der Spalten des Tabellen-Modells
			 */
			int columnCount = metaData.getColumnCount();
			columnLabels = new Vector<String>();

			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				columnLabels.add(metaData.getColumnLabel(columnIndex));
			}

			rows = new Vector<Vector<String>>();
			Vector<String> singleRow;
			while (result.next()) {
				singleRow = new Vector<String>();
				for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
					singleRow.add("" + result.getObject(columnIndex));
				}
				rows.add(singleRow);
			}
		} catch (SQLException e) {
			System.err.print("Table could not be built from empty ResultSet.");
			return emptyTable;
		}
		return new JTable(new DefaultTableModel(rows, columnLabels));
	}
}