package fr.lip6.move.coloane.interfaces.utils;

import java.io.IOException;
import java.util.logging.FileHandler;

/**
 * Log handler dedicated to file export<br>
 * All messages are dumped in a file (in the default temporary folder)
 */
public final class ColoaneLogHandler extends FileHandler {
	private static ColoaneLogHandler instance = null;

	/**
	 * Build the handle (inside the <b>default temporary folder</b>)
	 */
	private ColoaneLogHandler() throws IOException, SecurityException {
		super("%t/coloane.log", true); //$NON-NLS-1$
	}

	/**
	 * @return the handler
	 */
	public static ColoaneLogHandler getInstance() throws IOException, SecurityException {
		if (instance == null) { instance = new ColoaneLogHandler(); }
		return instance;
	}
}
