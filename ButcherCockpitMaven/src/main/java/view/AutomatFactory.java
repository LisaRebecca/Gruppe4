package view;

import java.sql.ResultSet;
import java.sql.SQLException;

import data.Database;
import errorhandling.AbstractButcherException;
import errorhandling.ButcherException;
import errorhandling.ExceptionHandler;
import errorhandling.SQLButcherException;

public class AutomatFactory extends Factory {

	private static final String select = "select name, portionen, haltbar_bis, kilopreis, gewicht_portion from lagerbestand left join produkte on lagerbestand.produkt = produkte.produkt_id "
			+ "WHERE lagerort='automat1';";

	@Override
	public void construct() throws AbstractButcherException{
		Automat automat = null;
		automat = new Automat();
		
		ResultSet rs_products = null;
		rs_products = Database.get().executeDBQuery(select);
		
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
	}
}