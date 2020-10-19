package Tools;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import errorhandling.ButcherException;

public class MyTools {
	public static final NumberFormat currencyFormatter = NumberFormat.getInstance();

	/**
	 * Formatiert Zahlen in eine Währung
	 * 
	 * @param d Wert, der in Währung umgewandelt werden soll
	 * @return den Eingabewert formatiert als Währung
	 */
	public static String formatAsCurrency(double d) {
		currencyFormatter.setMaximumFractionDigits(2);
		currencyFormatter.setMinimumFractionDigits(2);
		return currencyFormatter.format(d) + "€";
	}

	public static JTable resultSetToTable(ResultSet result) throws ButcherException {
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
			throw new ButcherException(e, "Datenbankfehler", "Bitte wenden Sie sich an einen Mitarbeiter");
		}
	}
}
