package view;
import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Window {

	public Image getImage (String bild) throws IOException {
		Image img;
		bild = "target" + bild;
		img = ImageIO.read(getClass().getResource("/src/img/kuh.jpg"));
	
		

// in einer anderen Klasse die Methode aufrufen (Evtl. static)
	
		return img;
	}
	public static void main(String[] args) {
		new getImage(img);
		
	}
}
