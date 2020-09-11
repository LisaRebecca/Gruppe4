package view;

import controller.Portion;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Tools.MyTools;

@SuppressWarnings("serial")
public class Panel_Selection extends JPanel {
	public static final int initialAmount = 0;
	/**
	 * Automat welcher dieses Panel enthält.
	 */
	public Automat automat;
	/**
	 * Label für die ausgewählte Anzahl an Portionen.
	 */
	private JLabel jlbl_amount;
	/**
	 * Der gesamte Preis für die ausgewählte Menge des Produkts.
	 */
	private JLabel jlbl_preis;
	/**
	 * Das Produkt kann als einzelne {@link Portion} ausgewählt werden.
	 */
	private Portion portion;
	/**
	 * Buttons zum Verändern der ausgewählten Menge des Produktes.
	 */
	private JButton jb_more, jb_less;

	/**
	 * Kontruktor, welcher alle Buttons erstellt und die Labels mit Initialwerten
	 * beschriftet.
	 * 
	 * @param portion das darzustellende Produkt als einzelne {@link Portion}
	 */
	public Panel_Selection(Portion portion, Automat automat) {
		this.portion = portion;
		this.automat = automat;

		this.setLayout(new GridLayout(1, 0));
		this.setBackground(Color.LIGHT_GRAY);

		/**
		 * Darstellen der Portion, welche zur Auswahl steht.
		 */
		this.add(new JLabel(portion.getName(), SwingConstants.LEFT));
		this.add(new JLabel("" + portion.getKilopreis() + " �/kg", SwingConstants.RIGHT));
//		this.add(new JLabel("  haltbar bis " + portion.haltbar, SwingConstants.RIGHT));
		this.add(new JLabel("" + portion.getLagermenge() + " Portionen á ", SwingConstants.RIGHT));
		this.add(new JLabel("" + this.portion.getPortionsgewichtGramm() + "g auf Lager", SwingConstants.LEFT));

		/**
		 * Ausgewählte Menge anzeigen
		 */
		jlbl_amount = new JLabel(""+initialAmount, SwingConstants.CENTER);
		jlbl_amount.setBackground(Color.white);
		this.add(jlbl_amount);

		/**
		 * ButtonsListener, welcher die Änderungen der Menge steuert
		 */
		ActionListener bl = new ActionListener_Amount();

		/**
		 * Button zum Erhöhen der Menge
		 */
		jb_more = new JButton("+");
		jb_more.setBackground(Color.white);
		jb_more.addActionListener(bl);
		this.add(jb_more);

		/**
		 * Button zum Vermindern der Menge
		 */
		jb_less = new JButton("-");
		jb_less.setBackground(Color.white);
		jb_less.addActionListener(bl);
		/**
		 * Anzahl ist zu Anfang 0, der Nutzer soll die Anzahl nur erhöhen können.
		 */
		jb_less.setVisible(false);
		this.add(jb_less);

		jlbl_preis = new JLabel(MyTools.formatAsCurrency(0), SwingConstants.LEFT);
		this.add(jlbl_preis);
	}

	/**
	 * der gesamte Preis der ausgewälten Portionen wird aktualisiert
	 */
	public void aktualisierePreise() {
		
		jlbl_preis.setText("" + MyTools.formatAsCurrency((portion.getPortionspreis() * getAmount())));
	}

	public double getPreis() {
		String preis = jlbl_preis.getText().replace(',','.');
		int index = preis.indexOf("�");
		if( index == -1 ) {
		}else {
			preis = preis.substring(0, index); 
		}
		return Double.parseDouble(preis);
	}
	public int getAmount() {
		return Integer.parseInt(jlbl_amount.getText());
	}
}