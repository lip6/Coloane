package fr.lip6.move.coloane.projects.its.antlrutil;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ErrorReporter implements IErrorReporter, Iterable<String> {

	List<String> errors = new LinkedList<String>();
	
	public void reportError(String msg) {
		errors.add(msg);
	}

	public Iterator<String> iterator() {
		return errors.iterator();
	}

}
