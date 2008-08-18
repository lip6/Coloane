package fr.lip6.move.coloane.apiws.observables;

import fr.lip6.move.coloane.apiws.interfaces.observables.IBrutalInterruptObservable;
import fr.lip6.move.coloane.apiws.interfaces.observables.IDisconnectObservable;
import fr.lip6.move.coloane.apiws.interfaces.observables.IReceptDialogObservable;
import fr.lip6.move.coloane.apiws.interfaces.observables.IReceptMenuObservable;
import fr.lip6.move.coloane.apiws.interfaces.observables.IReceptMessageObservable;
import fr.lip6.move.coloane.apiws.interfaces.observables.IReceptResultObservable;
import fr.lip6.move.coloane.apiws.interfaces.observables.IReceptServiceStateObservable;

/**
 * Cette classe permet de créer les différents observables possibles.
 *
 * @author Monir CHAOUKI
 */
public final class ObservableFactory {

	/**
	 * Constructeur vide
	 */
	private ObservableFactory() { }

	/**
	 * Crée un observable pour l'événement: récéption d'une boîte de dialogue
	 * @return un observable pour l'événement: récéption d'une boîte de dialogue
	 */
	public static IReceptDialogObservable getNewReceptDialogObservable() {
		return (IReceptDialogObservable) new ReceptDialogObservable();
	}

	/**
	 * Crée un observable pour l'événement: récéption de menus
	 * @return un observable pour l'événement: récéption de menus
	 */
	public static IReceptMenuObservable getNewReceptMenuObservable() {
		return (IReceptMenuObservable) new ReceptMenuObservable();
	}

	/**
	 * Crée un observable pour l'événement: récéption de messages
	 * @return un observable pour l'événement: récéption de messages
	 */
	public static IReceptMessageObservable getNewReceptMessageObservable() {
		return (IReceptMessageObservable) new ReceptMessageObservable();
	}

	/**
	 * Crée un observable pour l'événement: récéption de résultats
	 * @return un observable pour l'événement: récéption de résultats
	 */
	public static IReceptResultObservable getNewReceptResultObservable() {
		return (IReceptResultObservable) new ReceptResultObservable();
	}

	/**
	 * Crée un observable pour l'événement: récéption d'une erreur
	 * @return un observable pour l'événement: récéption d'une erreur
	 */
	public static IBrutalInterruptObservable getNewBrutalInterruptObservable() {
		return (IBrutalInterruptObservable) new BrutalInterruptObservable();
	}

	/**
	 * Crée un observable pour l'événement: deconnexion ordonnée
	 * @return un observable pour l'événement: deconnexion ordonnée
	 */
	public static IDisconnectObservable getNewDisconnectObservable() {
		return (IDisconnectObservable) new DisconnectObservable();
	}

	/**
	 * Crée un observable pour l'événement: récéption d'une information sur un service en cours d'exécution.
	 * @return un observable pour l'événement: récéption d'une information sur un service en cours d'exécution.
	 */
	public static IReceptServiceStateObservable getNewReceptServiceStateObservable() {
		return (IReceptServiceStateObservable) new ReceptServiceStateObservable();
	}
}
