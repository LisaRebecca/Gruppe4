package view;

import java.sql.ResultSet;

import javax.swing.JTable;

import Tools.Converter;
import data.Database;
import data.Select_Statements;
import errorhandling.AbstractButcherException;
import errorhandling.ExceptionHandler;

/**
 * Factory Klasse zur Erstellung von {@link Tile}s.
 * 
 * @author I518232
 *
 */
public class TileFactory {
	/**
	 * Ein <code>JTable</code> wird mit Daten aus der Datenbank gemäß des
	 * {@link Select_Statements} gefüllt.
	 */
	public static Tile getTile(Select_Statements stmt, String name) {
		JTable table = null;
		ResultSet result = null;
		try {
			result = Database.get().executeDBQuery(stmt);
			table = Converter.resultSetToTable(result);
		} catch (AbstractButcherException e) {
			ExceptionHandler.get().showException(e);
		}

		return new Tile(table, name);
	}
}