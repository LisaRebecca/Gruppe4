package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

import controller.Select_Statements;
import errorhandling.AbstractButcherException;
import models.Tile;

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

	/**
	 * Setzen des Fenster-Icons. <br>
	 * Hinweis: Falls das Bild nicht gesetzt werden kann erscheint lediglich eine
	 * Warnung, da das Bild nicht nötig für das Funktionieren der Anwendung ist.
	 */
	@Override
	protected void setIcon() {
		try {
			BufferedImage image = ImageIO.read(new URL(
					"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQuzBtedlLeHnfd8uGFz57BYsRIej7Op8mJLA&usqp=CAU"));
			this.setIconImage(image);
		} catch (IOException e) {
			System.err.println("Icon des Automaten konnte nicht geladen werden.");
		}
	}
}