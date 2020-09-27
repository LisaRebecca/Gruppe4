package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Der {@link DatabaseConnector} stellt die Schnittstelle zur Datenbank zur
 * Verfügung. Die Klasse kann Requests an die Datenbank senden um Daten
 * auszulesen. Zusätzlich kann diese Klasse auch Tupel in Tabellen einf�gen.
 */
public class RealDatabase extends Database {
	/**
	 * Verbindung zur Datenbank
	 */
	private static Connection conn = DatabaseConnection.getDBConnection();

	/**
	 * Eine Anfrage an die in {@link DatabaseConnection} angebundene Datenbank kann
	 * gesendet werden.
	 * 
	 * @param select_statement Eine Datenbankanfrage im MySQL-Syntax
	 * @return die angefragten Daten in der Form eines {@link JTable}
	 * @throws SQLException
	 */
	@Override
	public JTable executeDBQuery(String select_statement) throws SQLException {
		ResultSet result = null;
		result = conn.createStatement().executeQuery(select_statement);
		return buildJTable(result);
	}

	/**
	 * Die Datenbank wird bef�llt.
	 * 
	 * @param insert_statement beschreibt, welche Daten in die Tabelle geschrieben
	 *                         werden
	 * @throws SQLException 
	 */
	@Override
	public void executeDBInsert(String insert_statement) throws SQLException {
		conn.createStatement().execute(insert_statement);

	}

	/**
	 * Hilfsmethode, welche ein <code>ResultSet</code> in einen <code>JTable</code>
	 * umwandelt. Diese Methode erlaubt es, das Ergebnis einer Datenbankabfrage
	 * mithilfe von <code>JTables</code> für Anwender zu visualisieren. Die Spalten
	 * des JTables werden mit dem jeweiligen Bezeichner des Attributs der
	 * Datenbanktabelle benannt. Falls ein Aliasname für ein Attribut gesetzt wurde
	 * wird jedoch dieser bevorzugt.
	 * 
	 * @param result das Ergebnis einer Datenbankabfrage
	 * @return ein {@link DefaultTableModel} welches Daten aus der Datenbank
	 *         beinhaltet
	 * @throws SQLException beim auslesen der Daten kam es zu einem Fehler
	 */
	public static JTable buildJTable(ResultSet result) throws SQLException {
		Vector<Vector<String>> rows;
		Vector<String> columnLabels;

		ResultSetMetaData metaData = result.getMetaData();

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
		return new JTable(new DefaultTableModel(rows, columnLabels));

	}
}
