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

	Font arial = new Font("Arial", Font.PLAIN, 18);
	JPanel mainPanel, auswahlPanel, barPanel;
	JLabel descr_lbl, sum_lbl;
	JButton buy_btn;
	ArrayList<Produktauswahl> list_produktauswahl = new ArrayList<Produktauswahl>();

	public Automat() {

		// Design Initializations
		this.setTitle("Kühlautomat");
		try {
			BufferedImage image = ImageIO.read(new URL(
					"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQuzBtedlLeHnfd8uGFz57BYsRIej7Op8mJLA&usqp=CAU"));
			this.setIconImage(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setVisible(true);
		this.setSize(500, 400);
		this.setLocation(800, 100);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		Container c = getContentPane();
		mainPanel = new JPanel(new FlowLayout());
		c.add(mainPanel);

		descr_lbl = new JLabel("Bitte wählen Sie ihre Produkte aus:");
		descr_lbl.setFont(arial);

		String cus_sql = "select name, portionen, haltbar_bis, kilopreis, gewicht_portion from lagerbestand "
				+ "left join produkte on lagerbestand.produkt = produkte.produkt_id " + "WHERE lagerort='automat1';";
		JTable jt_verfuegbareProdukte = new JTable(DatabaseConnector.executeDBQuery(cus_sql));

		auswahlPanel = new JPanel(new GridLayout(0, 1));
		auswahlPanel.add(descr_lbl);

		mainPanel.add(auswahlPanel);

		for (int row = 0; row < jt_verfuegbareProdukte.getRowCount(); row++) {
			Portion p = new Portion("" + jt_verfuegbareProdukte.getValueAt(row, 0),
					"" + jt_verfuegbareProdukte.getValueAt(row, 2), "" + jt_verfuegbareProdukte.getValueAt(row, 3),
					"" + jt_verfuegbareProdukte.getValueAt(row, 4));
			Produktauswahl produktAuswahl = new Produktauswahl(p);
			list_produktauswahl.add(produktAuswahl); // Warenkorb
			auswahlPanel.add(produktAuswahl);
		}

		barPanel = new JPanel(new GridLayout(3, 1));

		// Anzeige der Gesamtsumme
		sum_lbl = new JLabel("Gesamtpreis: ____€");
		barPanel.add(sum_lbl);

		// Kaufen Button
		buy_btn = new JButton("Buy");
		buy_btn.setBackground(Color.white);
		BuyButtonListener bl = new BuyButtonListener();
		buy_btn.addActionListener(bl);
		barPanel.add(buy_btn);

		mainPanel.add(barPanel);
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