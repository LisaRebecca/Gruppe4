package view;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.Database;
import data.Select_Statements;
import errorhandling.AbstractButcherException;

public class AutomatFactory extends Factory {

	@Override
	public void construct() {
		Automat automat = null;
		try {
			automat = new Automat();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ResultSet rs_products = null;
		try {
			rs_products = Database.get().executeDBQuery(Select_Statements.AUTOMAT_PRODUCTS);
		} catch (AbstractButcherException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			e.printStackTrace();
		}
	}
}