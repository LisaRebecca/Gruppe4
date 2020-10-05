package Tools;

import java.awt.Frame;
import java.sql.SQLException;

public class DatabaseExceptionHandler extends ExceptionHandler {
	
	public DatabaseExceptionHandler(SQLException exception, Frame owner) {
		super(exception, owner);
		showException();
	}

	@Override
	public void showException() {
		buildPopUp();
		System.out.println("Fehler");
		
	}
	
}
