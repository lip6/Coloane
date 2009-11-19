package fr.lip6.move.coloane.its.expression.parser;

/**
 * Report errors from a parser.
 * @author Yann
 *
 */
public interface IErrorReporter {
	/**
	 * Listener for parse errors.
	 * @param msg the antlr message
	 */
	void reportError(String msg);
}
