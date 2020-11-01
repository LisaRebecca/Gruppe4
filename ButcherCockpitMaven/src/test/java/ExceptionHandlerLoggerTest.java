import errorhandling.AbstractButcherException;
import errorhandling.ButcherException;
import errorhandling.ExceptionHandler;
import errorhandling.ExceptionHandlerUser;

public class ExceptionHandlerLoggerTest {
	public static void main(String[] args) {
		ExceptionHandler.set(new ExceptionHandlerUser());
		ExceptionHandler.get().showException(new ButcherException(new Exception(), "erste Exception", "message"));
		ExceptionHandler.get().showException(new ButcherException(new Exception(), "zweite Exception", "message"));
		ExceptionHandler.get().showException(new ButcherException(new Exception(), "dritte Exception", "message"));
	}
}
