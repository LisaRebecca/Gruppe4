package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * ActionListener, der das Event-Handlung für die +/- Buttons übernimmt. Dies
 * beinhaltet die Mengenanzeige, das Anpassen der Preise an die Produktanzahl
 * und das .
 */
public class ActionListener_Amount implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		JButton jb_source = (JButton) e.getSource();

		 // Finden des Panels <code>Panel_Selection</code> in welchem der zugehörige
		 // Button liegt.
		Object parent;
		do {
			parent = jb_source.getParent();
		} while (!(parent instanceof Panel_Selection));
		Panel_Selection ps = (Panel_Selection) parent;

		// Mengenanzeige im Label der <code>Panel_Selection</code> dem Button entsprechend 
		// erhöhen oder vermindern
		int amount = ps.getAmount();
		if (jb_source.getText().equals("+")) {
			amount++;
		} else if (jb_source.getText().equals("-")) {
			amount--;
		}

		ps.getJlbl_amount().setText("" + amount);

		 // Verändern des Preises dieser einzelnen Produktauswahl.
		ps.aktualisierePreise();
		
		// Aktualisieren des Gesamtpreises dieses Einkaufs.
		ps.getAutomat().berechneGesamtpreis();

		// Ein-/Ausblenden der Buttons je nachdem ob weitere Portionen des Produktes
		// vorhanden sind. Verhindert auch die Auswahl einer negativen Anzahl.
		if (amount <= 0) {
			ps.getJb_less().setVisible(false);
		} else if (0 < amount & amount < ps.getPortion().getLagermenge()) {
			ps.getJb_less().setVisible(true);
			ps.getJb_more().setVisible(true);
		} else if (amount >= ps.getPortion().getLagermenge()) {
			ps.getJb_more().setVisible(false);
		}

	}
}
