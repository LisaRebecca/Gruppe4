package controller;

public class ExceptionHandlerDebug extends ExceptionHandler {

	@Override
	public void showException(Exception e) {
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
	
}
