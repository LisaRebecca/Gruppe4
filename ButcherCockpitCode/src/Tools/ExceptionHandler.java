package Tools;

import java.awt.Frame;

public abstract class ExceptionHandler {
	protected Exception originException;
	protected Frame owner;
	
	public ExceptionHandler(Exception e, Frame o) {
		originException = e;
		owner = o;
	}
	
	public abstract void showException();
	
	public void buildPopUp() {
		
	}

}
