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
		this.setPortion(portion);
		this.automat = automat;

		this.setLayout(new GridLayout(1, 0));
		this.setBackground(Color.LIGHT_GRAY);

		/**
		 * Darstellen der Portion, welche zur Auswahl steht.
		 */
		this.add(new JLabel(portion.getName(), SwingConstants.LEFT));
		this.add(new JLabel("" + portion.getKilopreis() + " �/kg", SwingConstants.RIGHT));
//		this.add(new JLabel("  haltbar bis " + portion.haltbar, SwingConstants.RIGHT));
		this.add(new JLabel("" + portion.getLagermenge() + " Portionen � ", SwingConstants.RIGHT));
		this.add(new JLabel("" + this.getPortion().getPortionsgewichtGramm() + "g auf Lager", SwingConstants.LEFT));

		/**
		 * Ausgewählte Menge anzeigen
		 */
		setJlbl_amount(new JLabel(""+initialAmount, SwingConstants.CENTER));
		getJlbl_amount().setBackground(Color.white);
		this.add(getJlbl_amount());

		/**
		 * ButtonsListener, welcher die Änderungen der Menge steuert
		 */
		ActionListener bl = new ActionListener_Amount();

		/**
		 * Button zum Erhöhen der Menge
		 */
		setJb_more(new JButton("+"));
		getJb_more().setBackground(Color.white);
		getJb_more().addActionListener(bl);
		this.add(getJb_more());

		/**
		 * Button zum Vermindern der Menge
		 */
		setJb_less(new JButton("-"));
		getJb_less().setBackground(Color.white);
		getJb_less().addActionListener(bl);
		/**
		 * Anzahl ist zu Anfang 0, der Nutzer soll die Anzahl nur erhöhen können.
		 */
		getJb_less().setVisible(false);
		this.add(getJb_less());

		jlbl_preis = new JLabel(MyTools.formatAsCurrency(0), SwingConstants.RIGHT);
		this.add(jlbl_preis);
	}

	/**
	 * der gesamte Preis der ausgewälten Portionen wird aktualisiert
	 */
	public void aktualisierePreise() {
		jlbl_preis.setText("" + MyTools.formatAsCurrency((getPortion().getPortionspreis() * getAmount())));
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
		return Integer.parseInt(getJlbl_amount().getText());
	}

	public JLabel getJlbl_amount() {
		return jlbl_amount;
	}

	public void setJlbl_amount(JLabel jlbl_amount) {
		this.jlbl_amount = jlbl_amount;
	}

	public JButton getJb_less() {
		return jb_less;
	}

	public void setJb_less(JButton jb_less) {
		this.jb_less = jb_less;
	}

	public Portion getPortion() {
		return portion;
	}

	public void setPortion(Portion portion) {
		this.portion = portion;
	}

	public JButton getJb_more() {
		return jb_more;
	}

	public void setJb_more(JButton jb_more) {
		this.jb_more = jb_more;
	}
}