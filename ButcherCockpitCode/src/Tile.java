import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Tile extends JButton {
	Font font = new Font("Arial",Font.PLAIN, 18);
    
    public Tile (String tiletext) {
    	this.setText(tiletext);
    	this.setSize(200, 100);
    	this.setBackground(Color.WHITE);
    	this.setFont(font);

    }

}


