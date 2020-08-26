package classes;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//TO DO
//Automatbestand: Datenbank abfragen was von welchen Produkt vorhanden und zu welchem Preis

public class Automat extends JFrame {
	Font font = new Font("Arial", Font.PLAIN, 18);
	JPanel mainPanel, auswahlPanel, barPanel;
	JLabel descr, sum;
	JButton anotherItem, eraseItem, buy;

	public Automat() {

		Container c = getContentPane();

		mainPanel = new JPanel(new FlowLayout());
		c.add(mainPanel);

		descr = new JLabel("Was möchtest du aus dem Automaten entnehmen?");
		descr.setFont(font);
		mainPanel.add(new Tile("Zur Auswahl stehen: ", "Lagerbestand", true));
		mainPanel.add(descr);

		auswahlPanel = new JPanel(new GridLayout(0, 1));

		Produktauswahl p = new Produktauswahl();
		auswahlPanel.add(p);
		mainPanel.add(auswahlPanel);

		barPanel = new JPanel(new GridLayout(3, 1));
		mainPanel.add(barPanel);

		// Button um ein weiteres Produkt herauszunehmen
		anotherItem = new JButton("Add another item");
		anotherItem.setBackground(Color.white);
		AddListener al = new AddListener();
		anotherItem.addActionListener(al);
		barPanel.add(anotherItem);

		// Button um Produkt zurückzulegen
//		eraseItem = new JButton("Delete selected item");
//		eraseItem.setBackground(Color.white);
//		eraseItem.addActionListener(al);
//		barPanel.add(eraseItem);

		// Anzeige der Gesamtsumme
		sum = new JLabel("Gesamtpreis: ____€");
		barPanel.add(sum);

		// Kaufen Button
		buy = new JButton("Buy");
		buy.setBackground(Color.white);
		BuyButtonListener bl = new BuyButtonListener();
		buy.addActionListener(bl);
		barPanel.add(buy);
	}

	public class AddListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JButton jb = (JButton) e.getSource();
			if (jb == anotherItem) {
				auswahlPanel.add(new Produktauswahl());
			}
		}
	}

	//Ausgeführte Vorgänge an Kasse und Bestand weiterleiten, sodass Kassenbestand erhöht und Lager im Automat gemindert
	public class BuyButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	}

	public static void main(String[] args) {
		Automat anzeige = new Automat();
		anzeige.setTitle("Kühlautomat");
		anzeige.setVisible(true);
		anzeige.setSize(1500, 500);
		anzeige.setLocation(800, 100);
		anzeige.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}