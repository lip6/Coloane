package fr.lip6.move.coloane.apiws.interfaces.observables;

public interface IObservables {
	/**
	 * Cette interface permet d'identifier les differents evenements observables
	 */
	
	final static Integer RECEPT_DIALOG = 0;
	final static Integer RECEPT_MENU = 1;
	final static Integer RECEPT_MESSAGE = 2;
	final static Integer RECEPT_RESULT = 3;
	final static Integer RECEPT_ERROR = 4;
	final static Integer DISCONNECT = 5;
}