package view;

import java.awt.*;

import javax.swing.*;

/**
 * Die Klasse UserInterface dient der Darstellung des ButcherCockpits, also der
 * internen Benutzeroberflaeche fuer den Metzger.
 */
@SuppressWarnings("serial")
class Cockpit extends DefaultFrame {

	/**
	 * Menuzeile unten im Cockpit-UI um zwischen den Tabellen/Kacheln zu wechseln
	 */
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);

	public Cockpit() {
		super("ButcherCockpit", 500, 550);
		//716 x 677
		/**
		 * Konstruktor erzeugt alle anzuzeigenden Objekte
		 * 
		 * @throws AbstractButcherException
		 *

		/**
		 * Tabs werden hinzugef�gt
		 */

		c.setLayout(new BorderLayout());
		c.add(tabbedPane, BorderLayout.CENTER);
	}

	public void addTile(Tile tile) {
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.add(new JLabel("" + tile.getTilename()));
		panel.add(tile);
		tabbedPane.add(panel);
		tabbedPane.add("" + tile.getTilename(), panel);
	}
}
