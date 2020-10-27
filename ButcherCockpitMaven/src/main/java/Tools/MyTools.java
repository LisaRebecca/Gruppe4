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
}
