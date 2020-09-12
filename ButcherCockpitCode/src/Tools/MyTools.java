package Tools;
import java.text.NumberFormat;

public class MyTools {
	public static final NumberFormat currencyFormatter = NumberFormat.getInstance(); //.setMaximumFractionDigits(2);
	public static String formatAsCurrency(double d) {
		currencyFormatter.setMaximumFractionDigits(2);
		currencyFormatter.setMinimumFractionDigits(2);
		return currencyFormatter.format(d)+"€";
	}
}
