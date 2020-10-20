package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

import controller.Select_Statements;
import errorhandling.AbstractButcherException;

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

	public Cockpit(){
		super("ButcherCockpit", 500, 550);

		c.setLayout(new BorderLayout());
		c.add(tabbedPane, BorderLayout.CENTER);
	}
	
	public void addTile(Tile tile) {
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.add(new JLabel(""+tile.getTilename()));
		panel.add(tile);
		tabbedPane.add(panel);
		tabbedPane.add(""+tile.getTilename(), panel);
	}
}