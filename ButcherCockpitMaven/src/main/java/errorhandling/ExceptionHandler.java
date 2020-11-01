package errorhandling;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public abstract class ExceptionHandler {

	private static ExceptionHandler exceptionHandler;
	private static Logger logger;
	private static Handler handler;

	public ExceptionHandler() {
		logger = Logger.getLogger(ExceptionHandlerUser.class.getName());
		try {
			handler = new FileHandler("/logs/exception_logging.log");
		} catch (SecurityException e) {
			exceptionHandler.showException(new ButcherException(e, "Sicherheitsfehler beim Logging",
					"Bitte wenden Sie sich an den IT-Support"));
		} catch (IOException e) {
			exceptionHandler.showException(new ButcherException(e, "Log-File Zugriff fehlgeschlagen",
					"Bitte wenden Sie sich an den IT-Support"));
		}
		logger.setUseParentHandlers(false); // only print to file not to console
		handler.setFormatter(new SimpleFormatter());
		logger.addHandler(handler);
	}

	public static ExceptionHandler get() {
		return exceptionHandler;
	}

	public static void set(ExceptionHandler exceptionHandler) {
		ExceptionHandler.exceptionHandler = exceptionHandler;
	}

	protected void logException(Exception e) {
		logger.log(Level.INFO, "Exception geworfen", e);
	}

	public abstract void showException(AbstractButcherException e);

}
