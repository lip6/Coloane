package fr.lip6.move.coloane.interfaces.formalism;

import com.google.inject.Injector;

/**
 * Interface that must be implemented to give access to the various classes used
 * to define an xtext-based editor.
 * @author Elodie Banel
 */
public interface IXtextProvider {

	/**
	 * This function must return a guice injector that allows access to all the
	 * classes of an xtext project, both UI and language oriented.
	 * @return The injector.
	 */
	Injector getInjector();
	
}
