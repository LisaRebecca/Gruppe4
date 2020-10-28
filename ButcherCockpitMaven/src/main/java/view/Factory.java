package view;

import errorhandling.AbstractButcherException;
import errorhandling.ButcherException;

public abstract class Factory {

	
	private static Factory factory;
	
	public static Factory get() throws ButcherException {
		try {
			return factory;}
		catch(NullPointerException e) {
			throw new ButcherException(e, "technische Probleme", "Bitte an IT-Support wenden"); }
		
		}
	
	
	public static void set(Factory factory) {
		Factory.factory = factory;
	}
	
	public abstract void construct() throws AbstractButcherException;
}
