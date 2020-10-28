package view;

import java.util.ResourceBundle;

import errorhandling.AbstractButcherException;
import errorhandling.ButcherException;

public abstract class Factory {

	
	private static Factory factory;
	
	public static Factory get() throws ButcherException {
		try {
			return factory;}
		catch(NullPointerException e) {
			throw new ButcherException(e, ResourceBundle.getBundle("i18n/butcher_exception/butcher_exception_en").getString("error"), 
					ResourceBundle.getBundle("i18n/butcher_exception/butcher_exception_en").getString("error_text")); }
		
		}
	
	
	public static void set(Factory factory) {
		Factory.factory = factory;
	}
	
	public abstract void construct() throws AbstractButcherException;
}
