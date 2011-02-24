package fr.lip6.move.coloane.core.formalisms.checkers;

import fr.lip6.move.coloane.interfaces.formalism.ICheckerResult;


/**
 * Basic implementation of an @see{ICheckerResult}.
 * @author Yann.
 *
 */
public class CheckerResult implements ICheckerResult {

	/** 
	 * Verdict, true if the test detected a problem. 
	 */
	private boolean isFailed;
	/**
	 * The message explaining the nature of the error.
	 */
	private String message;
	
	/**
	 * Basic ctor : pass verdict and message.
	 * @param isFailed true if test detected an issue
	 * @param message should be "" if (! isFailed).
	 */
	public CheckerResult(boolean isFailed, String message) {
		this.isFailed = isFailed;
		this.message = message;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean hasFailed() {
		return isFailed;
	}

}
