package classes;

import java.awt.*;
import java.awt.event.*;
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

		this.setLayout(new GridLayout(0, 3));
//				DatabaseConnector.getProductsByLocation("automat1");
		// Teststring statt späteren Objekten mit Name, ID und Preis aus der Datenbank
//				String [] AutomatFleisch = {"Salami", "Schweinelende"};
		String[] AutomatFleisch = new String[jt_produkte.getRowCount()];

		for (int row = 0; row < jt_produkte.getRowCount(); row++) {
			AutomatFleisch[row] = ""+jt_produkte.getValueAt(row, 0);
//			for (int column = 0; column < jt_produkte.getColumnCount(); column++) {
//				AutomatFleisch[row] += "" + jt_produkte.getValueAt(row, column);
//			}
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
