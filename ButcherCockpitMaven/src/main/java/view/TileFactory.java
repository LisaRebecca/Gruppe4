package view;

import java.sql.ResultSet;

import javax.swing.JTable;

import Tools.MyTools;
import data.Database;
import data.Select_Statements;
import errorhandling.AbstractButcherException;
import errorhandling.ButcherException;
import errorhandling.ExceptionHandler;

public class TileFactory {
	public static Tile getTile(Select_Statements stmt, String name) {
		/**
		 * Ein <code>JTable</code> wird mit Daten aus der Datenbank gemäß des
		 * <code>select_statement</code>s gefüllt.
		 */
		JTable table = null;

			ResultSet result = null;
			try {
				result = Database.get().executeDBQuery(stmt);
				table = MyTools.resultSetToTable(result);
			} catch (AbstractButcherException e) {
				ExceptionHandler.get().showException(e);
			}
		
		return new Tile(table, name);
	}
}