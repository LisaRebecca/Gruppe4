package classes;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

import javax.swing.*;

public class Produktauswahl extends JPanel {
	static int nextIndex = 0;
	JLabel jlbl_amount, jlbl_preis, jlbl_portion;
	JButton jb_more, jb_less;
	Portion portion;

	public Produktauswahl(Portion portion, HashMap<Portion, Integer> warenkorb) {
		this.portion = portion;
		Font arial = new Font("Arial", Font.PLAIN, 18);
		this.setLayout(new GridLayout(1, 0));
		this.add(new JLabel(portion.name));
		this.add(new JLabel(""+portion.portionspreis+"€/Portion"));
		this.add(new JLabel("haltbar bis "+portion.haltbar));

		jlbl_amount = new JLabel("0", SwingConstants.CENTER);
		jlbl_amount.setFont(arial);
		jlbl_amount.setBackground(Color.white);
		jlbl_amount.setSize(20, 20);
		this.add(jlbl_amount);

		// Buttons zum erhöhen/mindern der Anzahl
		ActionListener bl = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// in dieser Methode auch bitte den Warenkorb verändern
				JButton jb = (JButton) e.getSource();
				int amount = 0;
				if (jb.getText() == "+") {
					amount = Integer.parseInt(jlbl_amount.getText()) + 1;
				} else {
					amount = Integer.parseInt(jlbl_amount.getText()) - 1;
				}
				jlbl_amount.setText("" + amount);
				aktualisierePreise();
			}
		};

		jb_more = new JButton("+");
		jb_more.setSize(20, 20);
		jb_more.setBackground(Color.white);
		jb_more.addActionListener(bl);
		this.add(jb_more);

		jb_less = new JButton("-");
		jb_less.setSize(20, 20);
		jb_less.setBackground(Color.white);
		jb_less.addActionListener(bl);
		this.add(jb_less);

		jlbl_preis = new JLabel("", SwingConstants.CENTER);
		this.add(jlbl_preis);
		// Preisanzeigetest -- später Preis aus Datenbank und number*preis
		// to do : richtigen Preis anzeigen
		this.setBackground(Color.LIGHT_GRAY);
	}

	public void aktualisierePreise() {
		jlbl_preis.setText("" + portion.portionspreis * Integer.parseInt(jlbl_amount.getText()) + " €");
	}
}