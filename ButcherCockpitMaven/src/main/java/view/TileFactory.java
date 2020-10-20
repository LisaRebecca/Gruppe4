package view;

import javax.swing.JTable;

import Tools.MyTools;
import data.Database;
import errorhandling.AbstractButcherException;
import errorhandling.ButcherException;

public class TileFactory {
	public static Tile getTile(String select_statement, String name) {
		/**
		 * Ein <code>JTable</code> wird mit Daten aus der Datenbank gemäß des
		 * <code>select_statement</code>s gefüllt.
		 */
		JTable jt = null;

		try {
			jt = MyTools.resultSetToTable(Database.get().executeDBQuery(select_statement));
		} catch (ButcherException e) {
			e.printStackTrace();
		} catch (AbstractButcherException e) {
			e.printStackTrace();
		}
		return new Tile(jt, name);
	}
}