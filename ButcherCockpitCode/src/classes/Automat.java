package classes;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

import javax.swing.*;

//TO DO
//Automatbestand: Datenbank abfragen was von welchen Produkt vorhanden und zu welchem Preis

public class Automat extends JFrame {
	Font font = new Font("Arial", Font.PLAIN, 18);
	JPanel mainPanel, auswahlPanel, barPanel;
	JLabel descr_lbl, sum_lbl;
	JButton anotherItem_btn, eraseItem_btn, buy_btn;
	HashMap<Portion, Integer> warenkorb = new HashMap<Portion, Integer>();

	public Automat() {

		Container c = getContentPane();

		mainPanel = new JPanel(new FlowLayout());
		c.add(mainPanel);

		descr_lbl = new JLabel("Was m�chtest du aus dem Automaten entnehmen?");
		descr_lbl.setFont(font);
		String cus_sql = "select name, portionen, haltbar_bis, kilopreis from lagerbestand "
						+"left join produkte on lagerbestand.produkt = produkte.produkt_id "
						+"WHERE lagerort='automat1';";
		Tile verfuegbare_produkte = new Tile("Zur Auswahl stehen: ", 
				"", 
				cus_sql);
		mainPanel.add(verfuegbare_produkte);
		mainPanel.add(descr_lbl);

		auswahlPanel = new JPanel(new GridLayout(0, 1));

		mainPanel.add(auswahlPanel);

		barPanel = new JPanel(new GridLayout(3, 1));
		mainPanel.add(barPanel);
		
		Portion[] portionen = new Portion[verfuegbare_produkte.jt.getRowCount()];
		for (int row = 0; row < verfuegbare_produkte.jt.getRowCount(); row++) {
			Portion p = new Portion("" + verfuegbare_produkte.jt.getValueAt(row, 0), "" + verfuegbare_produkte.jt.getValueAt(row, 2),
					"" + verfuegbare_produkte.jt.getValueAt(row, 3), "" + verfuegbare_produkte.jt.getValueAt(row, 4));
			portionen[row] = p;
		}
		
		// Button um ein weiteres Produkt herauszunehmen
		anotherItem_btn = new JButton("Add another item");
		anotherItem_btn.setBackground(Color.white);
		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton jb = (JButton) e.getSource();
				if (jb == anotherItem_btn) {
					auswahlPanel.add(new Produktauswahl(portionen, warenkorb));
					auswahlPanel.revalidate(); // dont ask why but it works like a reload, refresh
				}
			}

		};
		anotherItem_btn.addActionListener(al);
		barPanel.add(anotherItem_btn);
		
		// Add first Produktauswahl
		anotherItem_btn.doClick();

		// Button um Produkt zur�ckzulegen
//		eraseItem_btn = new JButton("Delete selected item");
//		eraseItem_btn.setBackground(Color.white);
//		eraseItem_btn.addActionListener(al);
//		barPanel.add(eraseItem_btn);

		// Anzeige der Gesamtsumme
		sum_lbl = new JLabel("Gesamtpreis: ____�");
		barPanel.add(sum_lbl);

		// Kaufen Button
		buy_btn = new JButton("Buy");
		buy_btn.setBackground(Color.white);
		BuyButtonListener bl = new BuyButtonListener();
		buy_btn.addActionListener(bl);
		barPanel.add(buy_btn);
	}

	// Ausgef�hrte Vorg�nge an Kasse und Bestand weiterleiten, sodass Kassenbestand
	// erh�ht und Lager im Automat gemindert
	public class BuyButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	}

	public static void main(String[] args) {
		Automat anzeige = new Automat();
		anzeige.setTitle("K�hlautomat");
		anzeige.setVisible(true);
		anzeige.setSize(1500, 500);
		anzeige.setLocation(800, 100);
		anzeige.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}