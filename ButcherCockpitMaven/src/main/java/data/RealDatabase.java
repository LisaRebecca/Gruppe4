package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import errorhandling.AbstractButcherException;
import errorhandling.ButcherException;
import errorhandling.ExceptionHandler;
import controller.login.LoginController;
import data.Database;
import models.Credentials;

/**
 * Der {@link DatabaseConnector} stellt die Schnittstelle zur Datenbank zur
 * Verfügung. Die Klasse kann Requests an die Datenbank senden um Daten
 * auszulesen. Zusätzlich kann diese Klasse auch Tupel in Tabellen einf�gen.
 */
public class RealDatabase extends Database {

	private final ResourceBundle language;

	/**
	 * Verbindung zur Datenbank
	 */
	private Connection conn;

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public RealDatabase() {
		this.language = ResourceBundle.getBundle("i18n/real_database/real_database_de");

		isConnected = false;
	}

	/**
	 * Eine Anfrage an die in {@link DatabaseConnection} angebundene Datenbank kann
	 * gesendet werden.
	 * 
	 * @param select_statement Eine Datenbankanfrage im MySQL-Syntax
	 * @return die angefragten Daten in der Form eines {@link JTable}
	 * @throws SQLException
	 */

	@Override
	public ResultSet executeDBQuery(Select_Statements stmt) throws AbstractButcherException {
		try {
			return conn.createStatement().executeQuery(stmt.getStatement());
		} catch (SQLException e) {
			throw new ButcherException(e, this.language.getString("error"), this.language.getString("error_message"));
		}

	}

	/**
	 * Die Datenbank wird bef�llt.
	 * 
	 * @param insert_statement beschreibt, welche Daten in die Tabelle geschrieben
	 *                         werden
	 * @throws SQLException
	 */
	@Override
	public void executeDBInsert(String insert_statement) throws AbstractButcherException {
		try {
			conn.createStatement().execute(insert_statement);
		} catch (SQLException e) {
			throw new ButcherException(e, this.language.getString("error"), this.language.getString("error_message"));
		}

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
	public JTable buildJTable(ResultSet result) throws AbstractButcherException {
		Vector<Vector<String>> rows;
		Vector<String> columnLabels;

		ResultSetMetaData metaData;
		try {
			metaData = result.getMetaData();

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
		} catch (SQLException e) {
			throw new ButcherException(e, this.language.getString("error"), this.language.getString("error_message"));
		}
	}

	public void establishConnection() throws AbstractButcherException {
		String userName = Credentials.getUsername();
		String password = Credentials.getPassword();

		try {
			((RealDatabase) Database.get()).setConn(DriverManager
					.getConnection("jdbc:mysql://localhost:3306/metzgerei?user=" + userName + "&password=" + password));
		} catch (SQLException e) {
			ExceptionHandler.get().showException(new ButcherException(e, this.language.getString("error"),
					this.language.getString("error_message")));
		}
		isConnected = true;
		LoginController.get().giveControl();
	}
}