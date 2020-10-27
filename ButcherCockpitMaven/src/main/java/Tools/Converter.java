package Tools;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import errorhandling.ExceptionHandler;
import errorhandling.SQLButcherException;

public class Converter {
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
			ExceptionHandler.get().showException(new SQLButcherException(e));
			columnLabels.add(ResourceBundle.getBundle("i18n/sqlbutcher_exception/sqlbutcher_exception_en")
					.getString("loading_error"));
		}
		return new JTable(new DefaultTableModel(rows, columnLabels));
	}
}
