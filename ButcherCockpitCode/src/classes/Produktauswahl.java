package classes;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

import javax.swing.*;

public class Produktauswahl extends JPanel {
	Font font = new Font("Arial", Font.PLAIN, 18);
	JPanel jpcount;
	JComboBox<String> selection;
	JLabel amount;
	JLabel preis;
	JButton more;
	JButton less;
	int number = 1;
	int index = 0;
	

	public Produktauswahl(JTable jt_produkte) {
		
		
		class einePortion{
			String name;
			double portionspreis;
			String haltbar;
			public einePortion(String name, String haltbar_bis, String kilopreis, String gewicht_portion) {
				this.name = name;
				this.portionspreis = Double.parseDouble(kilopreis)*Double.parseDouble(gewicht_portion);
				this.haltbar = haltbar_bis;
			}
			@Override
			public String toString() {
				return name+", "+portionspreis+"€/pckg, "+haltbar;
			}
		}
//		HashMap<einePortion, Integer>
		this.setLayout(new GridLayout(0, 3));
		String[] AutomatFleisch = new String[jt_produkte.getRowCount()];
		
		// Produkte im Dropdown-Menü anzeigen
		for (int row = 0; row < jt_produkte.getRowCount(); row++) {
			einePortion ep = new einePortion(""+jt_produkte.getValueAt(row, 0), ""+jt_produkte.getValueAt(row, 2), ""+jt_produkte.getValueAt(row, 3), ""+jt_produkte.getValueAt(row, 4));
			System.out.println(ep);
			AutomatFleisch[row] = ep.toString();
		}
		System.out.println(AutomatFleisch);

		// Produkt auswählen, das herausgenommen werden soll
		selection = new JComboBox<>(AutomatFleisch);
		selection.setSelectedIndex(index);
		selection.setBackground(Color.white);
		index++;
		this.add(selection);

		// JLabel für Anzahl des Produktes
		jpcount = new JPanel(new GridLayout(1, 3));
		amount = new JLabel("" + number);
		amount.setFont(font);
		jpcount.add(amount);

		// Buttons zum erhöhen/mindern der Anzahl
		ButtonListener bl = new ButtonListener();
		more = new JButton("+");
		more.setBackground(Color.white);
		more.addActionListener(bl);
		jpcount.add(more);
		less = new JButton("-");
		less.setBackground(Color.white);
		less.addActionListener(bl);
		jpcount.add(less);
		this.add(jpcount);

		// Preisanzeigetest -- später Preis aus Datenbank und number*preis
		preis = new JLabel("8,99" + "€");
		this.add(preis);

	}

	class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JButton jb = (JButton) e.getSource();
			if (jb.getText() == "+") {
				number++;
			} else {
				number--;
			}
			amount.setText("" + number);
		}

	}

}
