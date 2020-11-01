package view;

import java.awt.*;
import java.util.ResourceBundle;

import javax.swing.*;

/**
 * Die Klasse Cockpit dient der Darstellung des ButcherCockpits, also der
 * internen Benutzeroberflaeche fuer den Metzger.
 */
@SuppressWarnings("serial")
class Cockpit extends DefaultFrame {

	/**
	 * Menuzeile unten im Cockpit-UI um zwischen den Tabellen/Kacheln zu wechseln
	 */
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);

	
	/**
	 * im Konstruktor werden Titel und Layout gesetzt, sowie die Tabs zum JFrame hinzugefügt
	 */
	public Cockpit() {
		//internationalisierter Titel und Maße des JFrames
		super(ResourceBundle.getBundle("i18n/cockpit/cockpit_en").getString("title"), 500, 550);

		c.setLayout(new BorderLayout());
		c.add(tabbedPane, BorderLayout.CENTER);
	}

	/**
	 * Hinzufügen der entsprechenden Tabellen-Kacheln je Tab
	 * @param tile übergibt als Instanz der Klasse Tile den Namen der anzuzeigenden Kachel
	 */
	public void addTile(Tile tile) {
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.add(new JLabel("" + tile.getTilename()));
		panel.add(tile);
		tabbedPane.add(tile.getTilename(), panel);
	}
}
