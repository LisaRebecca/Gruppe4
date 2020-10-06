package view;

import java.awt.Container;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public abstract class DefaultFrame extends JFrame {
	
	/**
	 * Schriftart f�r die �berschrift
	 */
	protected final Font headerfont = new Font("Arial", Font.BOLD, 20);
	protected Container c = getContentPane();
	protected String errorMessage;
	protected String errorTitle;
	
	public DefaultFrame(String title, int width, int height) {
		/**
		 * ------------------------------- Konfiguration JFrame -------------------------------
		 */
		/**
		 * Hier werden Titel, Sichtbarkeit, Groesse, Position und Close-Operation des
		 * Default-Windows festgelegt
		 */
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
		return;
	}
	
}