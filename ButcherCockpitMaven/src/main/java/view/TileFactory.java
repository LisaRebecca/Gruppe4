package view;

import javax.swing.JTable;

import Tools.MyTools;
import data.Database;
import data.Select_Statements;
import errorhandling.AbstractButcherException;
import errorhandling.ButcherException;

public class TileFactory {
	public static Tile getTile(Select_Statements stmt, String name) {
		/**
		 * Ein <code>JTable</code> wird mit Daten aus der Datenbank gemäß des
		 * <code>select_statement</code>s gefüllt.
		 */
		JTable jt = null;

		try {
			jt = MyTools.resultSetToTable(Database.get().executeDBQuery(stmt));
		} catch (ButcherException e) {
			e.printStackTrace();
		} catch (AbstractButcherException e) {
			e.printStackTrace();
		}
		return new Tile(jt, name);
	}
}