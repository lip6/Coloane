package fr.lip6.move.coloane.api.observables;

/**
 * Fabrique des objets observables de l'API
 *
 * @author Kahina Bouarab
 * @author Youcef Belattaf
 */
public final class ObservableFactory {

	/** Constructeur privé */
	private ObservableFactory() { }

	/**
	 * Création d'un observable pour l'ouverture de session.<br>
	 * Responsable du passage des menus et des modifications sur ceux-ci
	 * @return L'observeur
	 */
	public static ReceptMenuObservable getNewSessionObservable() {
		return (ReceptMenuObservable) new ReceptMenuObservable();
	}

	public static BrutalInterruptObservable getNewBrutalInterruptObservable() {
		return new BrutalInterruptObservable();
	}

	public static ReceptDialogObservable getNewreceptDialogObservable() {
		return new ReceptDialogObservable();
	}

	public static DisconnectObservable getNewCloseConnectionObservable() {
		return new DisconnectObservable();
	}

	public static ReceptMessageObservable getNewSpecialMessageObservable() {
		return new ReceptMessageObservable();
	}

	public static ReceptResultObservable getNewReceptResultObservable() {
		return new ReceptResultObservable();
	}

	public static ReceptDialogObservable getNewReceptDialogObservable() {
		return new ReceptDialogObservable();
	}
}
