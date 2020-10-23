package view;

import java.awt.Container;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public abstract class DefaultFrame extends JFrame {

	/**
	 * Schriftart für die Überschrift
	 */
	protected final Font headerfont = new Font("Arial", Font.BOLD, 20);
	protected Container c;
	protected String errorMessage;
	protected String errorTitle;

	public DefaultFrame(String title, int width, int height) {
		/**
		 * ------------------------------- Konfiguration JFrame
		 * -------------------------------
		 */
		/**
		 * Hier werden Titel, Sichtbarkeit, Groesse, Position und Close-Operation des
		 * Default-Windows festgelegt
		 */
		c = getContentPane();
		this.setTitle(title);
		this.setVisible(true);
		this.setSize(width, height);
		this.setLocation(50, 20);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setIcon();
	}

	/**
	 * Setzen des Fenster-Icons. <br>
	 * Hinweis: Falls das Bild nicht gesetzt werden kann erscheint lediglich eine
	 * Warnung, da das Bild nicht nötig für das Funktionieren der Anwendung ist.
	 */

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
