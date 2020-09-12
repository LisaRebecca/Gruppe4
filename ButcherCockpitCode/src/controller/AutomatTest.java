package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.junit.Assert;
import org.junit.Test;


import Tools.MyTools;
import view.Automat;
import view.Panel_Selection;

public class AutomatTest {
	Automat automat = new Automat();
	
	@Test(expected = MalformedURLException.class)
	public void readImageThrowsException() throws MalformedURLException, IOException {
		final BufferedImage image = ImageIO.read(new URL(
				"https://encsomethingsomethingrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQuzBtedlLeHnfd8uGFz57BYsRIej7Op8mJLA&usqp=CAU"));
		automat.setIconImage(image);
	}
	
	@Test
	public void testGetGesamtpreis() {
		//
		Assert.assertEquals(automat.getGesamtpreis(), 5.0);
		
	}
	
	@Test
	public void testBerechneGesamtpreis() {
		//list_productSelection festlegen
		automat.berechneGesamtpreis();
		Assert.assertEquals(Double.parseDouble(automat.jlbl_sum.getText()), Double.parseDouble("5.00"));
		
	}
}
