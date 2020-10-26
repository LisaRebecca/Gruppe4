package data;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.login.LoginController;
import errorhandling.AbstractButcherException;
import errorhandling.ButcherException;
import errorhandling.SQLButcherException;
import view.Panel_Selection;
import view.Portion;

public class Select_StatementTest {

	@BeforeAll
	public static void initialize() throws AbstractButcherException {
		Database.set(new RealDatabase());
		LoginController.set(new LoginControllerForTesting());
		LoginController.get().giveControl();
	}

	@Test
	public void statementSyntaxTest() throws SQLButcherException {
		for (Select_Statements stmt : Select_Statements.values()) {
			Database.get().executeDBQuery(stmt);
		}
	}

	@Test
	public void statementReturnTest() throws SQLButcherException {
		for (Select_Statements stmt : Select_Statements.values()) {
			ResultSet result = Database.get().executeDBQuery(stmt);
			assertNotNull(result);
		}
	}

	// Testet, ob die Statements welche Portionen zurück geben, dies auch tun
	// Fehlerfall: Es tritt eine Number-Format exception auf oder eine Spalte existiert nicht
	@Test
	public void selectReturnsPortion() throws SQLButcherException {

		for (Select_Statements stmt : Select_Statements.values()) {
			if (stmt.toString().contains("PRODUCTS")) {
				ResultSet rs_products = null;
				rs_products = Database.get().executeDBQuery(Select_Statements.AUTOMAT_PRODUCTS);

				try {
					while (rs_products.next()) {
						Portion portion = new Portion();
						portion.setName(rs_products.getString("name"));
						portion.setLagermenge(rs_products.getString("portionen"));
						portion.setHaltbarBis(rs_products.getString("haltbar_bis"));
						portion.setKilopreis(rs_products.getString("kilopreis"));
						portion.setPortionsgewichtKG(rs_products.getString("gewicht_portion"));
						System.out.println(portion.toString());
					}
				} catch (SQLException e) {
					assertTrue(false);
				}
				System.out.println(stmt.toString() + " tested.");
			}
		}
	}
}