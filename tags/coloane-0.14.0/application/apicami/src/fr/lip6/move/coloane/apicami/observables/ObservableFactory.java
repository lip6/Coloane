package fr.lip6.move.coloane.apicami.observables;

import java.util.Map;

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
	 * @return L'observable fraîchement créé
	 */
	public static ReceptMenuObservable getNewSessionObservable() {
		return (ReceptMenuObservable) new ReceptMenuObservable();
	}

	/**
	 * Création d'un observable pour la déconnexion brutale de la part de FrameKit.<br>
	 * @return L'observable fraîchement créé
	 */
	public static BrutalInterruptObservable getNewBrutalInterruptObservable() {
		return new BrutalInterruptObservable();
	}

	/**
	 * Création d'un observable pour la déconnexion de la plate-forme
	 * @return L'observable fraîchement créé
	 */
	public static DisconnectObservable getNewCloseConnectionObservable() {
		return new DisconnectObservable();
	}

	/**
	 * Création d'un observable pour la connexion à la plate-forme
	 * @param hash Le point de synchronisation
	 * @return L'observable fraîchement créé
	 */
	public static ConnectionObservable getNewOpenConnectionObservable(Map< String, Object> hash) {
		return new ConnectionObservable(hash);
	}

	/**
	 * Création d'un observable pour la réception d'un message en provenance de FrameKit
	 * @return L'observable fraîchement créé
	 */
	public static ReceptMessageObservable getNewSpecialMessageObservable() {
		return new ReceptMessageObservable();
	}

	/**
	 * Création d'un observable pour la réception d'une boite de dialog en provenance de FrameKit
	 * @return L'observable fraîchement créé
	 */
	public static ReceptDialogObservable getNewReceptDialogObservable() {
		return new ReceptDialogObservable();
	}

	/**
	 * Création d'un observable pour la réception des resultats en provenance de FrameKit
	 * @return L'observable fraîchement créé
	 */
	public static ReceptResultObservable getNewReceptResultObservable() {
		return new ReceptResultObservable();
	}

	/**
	 * Création d'un observable pour les services states en provenance de FrameKit
	 * @return L'observable fraîchement créé
	 */
	public static ReceptServiceStateObservable getNewReceptServiceStateObservable() {
		return new ReceptServiceStateObservable();
	}
}
