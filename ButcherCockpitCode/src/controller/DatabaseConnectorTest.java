package controller;

import static org.junit.Assert.assertNotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import org.junit.Test;

@SuppressWarnings("serial")
public class DatabaseConnectorTest extends JFrame {

	@Test
	public void executeDBQueryTest() {
		assertNotNull(DatabaseConnector.executeDBQuery(""));
	}

	@Test
	public void buildJTableTest() throws SQLException {
		ResultSet rs = DatabaseConnection.getDBConnection().createStatement().executeQuery("use metzgerei;");
		assertNotNull(DatabaseConnector.buildJTable(rs));
	}
}
