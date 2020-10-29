package view;

import java.awt.Container;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Abstrakte Klasse von der unsere beiden UIs Automat und Cockpit erben. 
 * Dient dem setzen allgemeiner Parameter, die beide Klassen verwenden, wie Schriftart, JFrame-Eigenschaften und Icon.
 *
 */
public abstract class DefaultFrame extends JFrame {

	/**
	 * Schriftart für die Überschrift
	 */
	protected final Font headerfont = new Font("Arial", Font.BOLD, 20);
	protected Container c;
	protected String errorMessage;
	protected String errorTitle;

	/**
	 * Konstruktur legt Konfiguration des JFrames fest
	 * 
	 * @param title
	 * @param width
	 * @param height
	 */
	public DefaultFrame(String title, int width, int height) {

		//Hier werden Titel, Sichtbarkeit, Groesse, Position und Close-Operation des
		//Default-Windows festgelegt
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
 //Currency_Symbol_Test&Anpassungen_in_Panel_Selection
		
		ImageIcon icon = new ImageIcon("src/main/resources/kuh.jpg");
		this.setIconImage(icon.getImage());

	}
}
