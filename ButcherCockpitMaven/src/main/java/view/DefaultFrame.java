package view;

import java.awt.Container;
import java.awt.Font;

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
	 * Warnung, da das Bild nicht nÃ¶tig fÃ¼r das Funktionieren der Anwendung ist.
	 */
	protected void setIcon() {
		return;
	}
}
