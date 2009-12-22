package fr.lip6.move.coloane.projects.its.expression.parser;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ErrorReporter implements IErrorReporter, Iterable<String> {

	List<String> errors = new LinkedList<String>();
	
	@Override
	public void reportError(String msg) {
		errors.add(msg);
	}

	@Override
	public Iterator<String> iterator() {
		return errors.iterator();
	}

}
