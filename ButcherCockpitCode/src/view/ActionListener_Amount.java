package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

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

		ps.jlbl_amount.setText("" + amount);
		ps.aktualisierePreise();
		ps.automat.berechneGesamtpreis();

		if (amount <= 0) {
			ps.jb_less.setVisible(false);
		} else if (0 < amount & amount < ps.portion.getLagermenge()) {
			ps.jb_less.setVisible(true);
			ps.jb_more.setVisible(true);
		} else if (amount >= ps.portion.getLagermenge()) {
			ps.jb_more.setVisible(false);
		}

	}
}
