package errorhandling;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Der ExceptionHandler dient als Singleton dazu, festzulegen ob der
 * ExceptionHandlerUser oder - Debug ausgewählt ist. Je nachdem welcher Handler
 * gewählt ist, wird anders mit den auftretenden Exceptions verfahren. Das
 * unterschiedliche Verhalten wird im jeweiligen Handler implementiert. Außerdem
 * wird im Konstruktor ein Log-File angelegt, in dem die geworfenen Exceptions
 * dokumentiert werden.
 * 
 * @author a-sch
 *
 */
public abstract class ExceptionHandler {

	private static ExceptionHandler exceptionHandler = new ExceptionHandlerUser();
	private static Logger logger;
	private static Handler handler;
	private static File file;

	public ExceptionHandler() {
		logger = Logger.getLogger(ExceptionHandlerUser.class.getName());
		file = new File("logFile.log");
		try {
			if (!file.exists())
				file.createNewFile();
			handler = new FileHandler(file.getAbsolutePath());
		} catch (SecurityException e) {
			logException(e);
			System.out.println(e);
		} catch (IOException e) {
			logException(e);
			System.out.println(e);
		}
		logger.setUseParentHandlers(false); // only print to file not to console
		handler.setFormatter(new SimpleFormatter());
		logger.addHandler(handler);
	}

	public static ExceptionHandler get() {
		return exceptionHandler;
	}

	/**
	 * Setter
	 * @param exceptionHandler
	 */
	public static void set(ExceptionHandler exceptionHandler) {
		ExceptionHandler.exceptionHandler = exceptionHandler;
	}

	/**
	 * Methode zum Speichern der auftretenden Exceptions in der Log-File.
	 * 
	 * @param Exception e
	 */
	protected void logException(Exception e) {
		logger.log(Level.INFO, "Exception geworfen", e);
	}

	/**
	 * abstrakte Methode die im ExceptionHandlerUser bzw. - Debug überschrieben wird
	 * um die geworfene Exception auf eine bestimmte Weise anzuzeigen.
	 * 
	 * @param AbstractButcherException e
	 */
	public abstract void showException(AbstractButcherException e);

}
