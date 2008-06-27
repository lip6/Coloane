package fr.lip6.move.coloane.apiws.interfaces.observables;

public interface IObservables {
	
	/**
	 * Cette interface permet d'identifier les differents evenements observables
	 */
	
	final static Integer ASK_DIALOG = 0;
	final static Integer CHANGE_SESSION = 1;
	final static Integer CLOSE_CONNECTION = 2;
	final static Integer CLOSE_SESSION = 3;
	final static Integer ERROR_MESSAGE = 4;
	final static Integer EXECUT_SERVICE = 5;
	final static Integer OPEN_CONNECTION = 6;
	final static Integer OPEN_SESSION = 7;
	final static Integer TRACE_MESSAGE = 8;
	final static Integer WARNING_MESSAGE = 9;
	final static Integer SEND_DIALOG = 10;
}
