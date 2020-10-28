package view;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ResourceBundle;

import data.Database;
import data.Select_Statements;
import errorhandling.AbstractButcherException;
import errorhandling.ButcherException;
import errorhandling.SQLButcherException;

public class AutomatFactory extends Factory {
	
	private final ResourceBundle language;
	
	public AutomatFactory() {
		this.language = ResourceBundle.getBundle("i18n/sqlbutcher_exception/sqlbutcher_exception_en");		
	}

	@Override
	public void construct() throws AbstractButcherException{
		Automat automat = new Automat();
		
		ResultSet rs_products = null;

		
		try {
			rs_products = Database.get().executeDBQuery(Select_Statements.AUTOMAT_PRODUCTS);
		} catch (AbstractButcherException e) {
			throw new ButcherException(e, this.language.getString("loading_error"), this.language.getString("error_message"));
		}

		try {
			while (rs_products.next()) {
				Portion portion = new Portion();
				portion.setName(rs_products.getString("name"));
				portion.setLagermenge(rs_products.getString("portionen"));
				portion.setHaltbarBis(rs_products.getString("haltbar_bis"));
				portion.setKilopreis(rs_products.getString("kilopreis"));
				portion.setPortionsgewichtKG(rs_products.getString("gewicht_portion"));
				
				Panel_Selection ps = new Panel_Selection(portion);
				ps.addPropertyChangeListener(automat);
				automat.addPanel(ps);
			}
		} catch (SQLException e) {
			throw new SQLButcherException(e);
		}
		automat.revalidate();
	}
}