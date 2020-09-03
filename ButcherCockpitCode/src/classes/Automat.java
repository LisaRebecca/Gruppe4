package classes;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

import java.util.ArrayList;
import javax.swing.*;

public class Automat extends JFrame {

	Font headerfont = new Font("Arial", Font.BOLD, 20);
	JPanel mainPanel, selectionPanel, barPanel;
	JLabel descr_lbl, sum_lbl;
	JButton buy_btn;
	ArrayList<Produktauswahl> list_productSelection = new ArrayList<Produktauswahl>();

	public Automat() {

		// Design Initializations
		try {
			BufferedImage image = ImageIO.read(new URL(
					"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQuzBtedlLeHnfd8uGFz57BYsRIej7Op8mJLA&usqp=CAU"));
			this.setIconImage(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.setTitle("Kühlautomat");
		this.setVisible(true);
		this.setSize(1200, 400);
		this.setLocation(200, 100);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		Container c = getContentPane();
		mainPanel = new JPanel(new FlowLayout());
		c.add(mainPanel);

		descr_lbl = new JLabel("Bitte wählen Sie Ihre Produkte aus:");
		descr_lbl.setFont(headerfont);
		
		selectionPanel = new JPanel(new GridLayout(0, 1));
		selectionPanel.add(descr_lbl);
		mainPanel.add(selectionPanel);

		String cus_sql = "select name, portionen, haltbar_bis, kilopreis, gewicht_portion from lagerbestand "
				+ "left join produkte on lagerbestand.produkt = produkte.produkt_id " + "WHERE lagerort='automat1';";
		JTable jt_obtainableProducts = new JTable(DatabaseConnector.executeDBQuery(cus_sql));

		for (int row = 0; row < jt_obtainableProducts.getRowCount(); row++) {
			Portion portion = new Portion("" + jt_obtainableProducts.getValueAt(row, 0),
					"" + jt_obtainableProducts.getValueAt(row, 2), "" + jt_obtainableProducts.getValueAt(row, 3),
					"" + jt_obtainableProducts.getValueAt(row, 4));
			Produktauswahl productSelection = new Produktauswahl(portion);
			list_productSelection.add(productSelection); // Warenkorb
			selectionPanel.add(productSelection);
		}

		barPanel = new JPanel(new GridLayout(3, 1));

		// Anzeige der Gesamtsumme
		sum_lbl = new JLabel("Gesamtpreis: ____€");
		barPanel.add(sum_lbl);

		// Kaufen Button
		buy_btn = new JButton("Kaufen");
		buy_btn.setBackground(Color.white);
		BuyButtonListener bl = new BuyButtonListener();
		buy_btn.addActionListener(bl);
		barPanel.add(buy_btn);
		mainPanel.add(barPanel);
		
		this.revalidate();
	}

	public class BuyButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// hier vielleicht nur eine Message-Box?
			// AusgefÃ¼hrte VorgÃ¤nge an Kasse und Bestand weiterleiten, sodass
			// Kassenbestand
			// erhÃ¶ht und Lager im Automat gemindert
		}
	}

	public static void main(String[] args) {
		new Automat();
	}
}