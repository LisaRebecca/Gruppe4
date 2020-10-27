package Tools;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import errorhandling.ButcherException;
import errorhandling.ExceptionHandler;
import errorhandling.SQLButcherException;

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
		return currencyFormatter.format(d) + Currency_Symbol.getCurrency_Symbol();
	}

	public static JTable resultSetToTable(ResultSet result) {
		Vector<Vector<String>> rows = new Vector<Vector<String>>();
		Vector<String> columnLabels = new Vector<String>();

		ResultSetMetaData metaData;
		try {
			metaData = result.getMetaData();

			int columnCount = metaData.getColumnCount();

			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				columnLabels.add(metaData.getColumnLabel(columnIndex));
			}

			Vector<String> singleRow;
			while (result.next()) {
				singleRow = new Vector<String>();
				for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
					singleRow.add("" + result.getObject(columnIndex));
				}
				rows.add(singleRow);
			}
		} catch (SQLException e) {
			ExceptionHandler.get().showException(
					new SQLButcherException(e));
			columnLabels.add(ResourceBundle.getBundle("i18n/sqlbutcher_exception/sqlbutcher_exception_en").getString("loading_error"));
		}
		return new JTable(new DefaultTableModel(rows, columnLabels));
	}
}
