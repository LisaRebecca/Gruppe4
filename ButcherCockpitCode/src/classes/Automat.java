package classes;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.IIOException;
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
		} catch (IIOException e) {
			System.err.println("Icon des Automaten konnte nicht geladen werden.");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
		JTable jt_obtainableProducts = DatabaseConnector.executeDBQuery(cus_sql);

		for (int row = 0; row < jt_obtainableProducts.getRowCount(); row++) {
			Portion portion = new Portion("" + jt_obtainableProducts.getValueAt(row, 0),
					"" + jt_obtainableProducts.getValueAt(row, 1), "" + jt_obtainableProducts.getValueAt(row, 2),
					"" + jt_obtainableProducts.getValueAt(row, 3), "" + jt_obtainableProducts.getValueAt(row, 4));
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
		ActionListener listener_buy_btn = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] options = { "Ja, bezahlen", "Nein, zurück" };
				int eingabe = JOptionPane.showOptionDialog(null,
						"Möchten Sie den Kaufvorgang abschließen und bezahlen?", "Bestätigung",
						JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				System.out.println(eingabe);
				if (eingabe == 0) {
					JOptionPane.showMessageDialog(null,
							"Danke für Ihren Einkauf, der Kassenbetrag wurde von ihrer Gutscheinkarte abgezogen.",
							"Danke!", JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);
				}

			}
		};
		buy_btn.addActionListener(listener_buy_btn);
		barPanel.add(buy_btn);
		mainPanel.add(barPanel);

		this.revalidate();
	}

	public static void main(String[] args) {
		new Automat();
	}
}