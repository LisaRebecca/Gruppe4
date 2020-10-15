package controller;

import Tools.AbstractButcherException;

public abstract class ExceptionHandler {
	
	private static ExceptionHandler exceptionHandler;
	
	public static ExceptionHandler get() {
		return exceptionHandler;
	}
	
	public static void set(ExceptionHandler exceptionHandler) {
		ExceptionHandler.exceptionHandler = exceptionHandler;
	}
	
	public abstract void showException(AbstractButcherException e);
	
}
