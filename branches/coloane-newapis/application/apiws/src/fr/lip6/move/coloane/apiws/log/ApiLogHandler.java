package fr.lip6.move.coloane.apiws.log;

import java.io.IOException;
import java.util.logging.FileHandler;

public class ApiLogHandler extends FileHandler {

	private static ApiLogHandler instance;

	public ApiLogHandler() throws IOException, SecurityException {
		super("%t/coloane.log", true); //$NON-NLS-1$
	}

	public static ApiLogHandler getInstance() throws IOException, SecurityException {
		if (instance == null) { instance = new ApiLogHandler(); }
		return instance;
	}

}
