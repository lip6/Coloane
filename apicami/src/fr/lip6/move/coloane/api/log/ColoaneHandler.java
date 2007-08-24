package fr.lip6.move.coloane.api.log;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class ColoaneHandler extends Handler {

	@Override
	public void close() throws SecurityException {
		// TODO Auto-generated method stub

	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}

	@Override
	public final void publish(LogRecord log) {
		System.out.println("[" + log.getLevel() + "] " + log.getMessage());
	}

}
