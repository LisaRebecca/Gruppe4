package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * ActionListener, der das Event-Handlung für die +/- Buttons übernimmt.
 * Dies beinhaltet die Mengenanzeige, das Anpassen der Preise an die Produktanzahl und das Ein-/Ausblenden der Buttons 
 * je nachdem ob weitere Portionen des Produktes vorhanden sind. 
 */
public class ActionListener_Amount implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		JButton jb_source = (JButton) e.getSource();
		Object parent;
		do {
			parent = jb_source.getParent();
		} while ( !(parent instanceof Panel_Selection));
		Panel_Selection ps = (Panel_Selection) parent;
		int amount = ps.getAmount();
		if (jb_source.getText().equals("+")) {
			amount++;

		} else if (jb_source.getText().equals("-")) {
			amount--;
		}

		ps.getJlbl_amount().setText("" + amount);
		ps.aktualisierePreise();
		ps.automat.berechneGesamtpreis();

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
