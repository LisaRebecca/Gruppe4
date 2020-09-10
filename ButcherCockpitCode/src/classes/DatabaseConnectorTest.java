package classes;

import java.awt.*;
import javax.swing.*;

public class DatabaseConnectorTest extends JFrame{
	public DatabaseConnectorTest() {
		Container c = getContentPane();
		Tile tile = new Tile("test", "SELECT name as Produktname, produkt_id as 'Produkt-ID', kilopreis as 'Kilopreis [�/kg]', gewicht_portion as 'Gewicht [kg/Portion]' FROM Produkte;");
		c.add(tile);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(400, 200);
		this.setLayout(new FlowLayout());
		this.revalidate();
	}
	public static void main(String[] args) {
		new DatabaseConnectorTest();
	}
}
