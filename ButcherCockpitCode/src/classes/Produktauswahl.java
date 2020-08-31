package classes;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

import javax.swing.*;

public class Produktauswahl extends JPanel {
	static int cb_selectedindex = 0;
	JPanel jp_count;
	JComboBox<String> jcb_selection;
	JLabel jlbl_amount;
	JLabel jlbl_preis;
	JButton jb_more;
	JButton jb_less;
	Portion[] portionen;

	public Produktauswahl(JTable jt_produkte, HashMap<Portion, Integer> warenkorb) {
		Font arial = new Font("Arial", Font.PLAIN, 18);

		this.setLayout(new GridLayout(0, 3));
		portionen = new Portion[jt_produkte.getRowCount()];
		// Produkte im Dropdown-Menü anzeigen
		jcb_selection = new JComboBox<>();
		for (int row = 0; row < jt_produkte.getRowCount(); row++) {
			Portion p = new Portion("" + jt_produkte.getValueAt(row, 0), "" + jt_produkte.getValueAt(row, 2),
					"" + jt_produkte.getValueAt(row, 3), "" + jt_produkte.getValueAt(row, 4));
			portionen[row] = p;
			jcb_selection.addItem(p.toString());
		}

		// Produkt auswählen, das herausgenommen werden soll
		jcb_selection.setSelectedIndex(cb_selectedindex);
		jcb_selection.setBackground(Color.white);
		cb_selectedindex++;
		this.add(jcb_selection);

		// JLabel für Anzahl des Produktes
		jp_count = new JPanel(new GridLayout(1, 3));
		jlbl_amount = new JLabel("0");
		jlbl_amount.setFont(arial);
		jp_count.add(jlbl_amount);
		
		
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
		jb_more.setBackground(Color.white);
		jb_more.addActionListener(bl);
		jp_count.add(jb_more);
		jb_less = new JButton("-");
		jb_less.setBackground(Color.white);
		jb_less.addActionListener(bl);
		jp_count.add(jb_less);
		this.add(jp_count);
		jlbl_preis = new JLabel("");
		this.add(jlbl_preis);
		// Preisanzeigetest -- später Preis aus Datenbank und number*preis
		// to do : richtigen Preis anzeigen
		jb_more.doClick();
		this.setBackground(Color.LIGHT_GRAY);
	}

	public void aktualisierePreise() {
		jlbl_preis.setText("" + (portionen[jcb_selection.getSelectedIndex()].portionspreis
				* Integer.parseInt(jlbl_amount.getText())));

	}
}