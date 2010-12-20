package fr.lip6.move.coloane.apicami.objects;

import fr.lip6.move.coloane.interfaces.api.objects.ISessionInfo;

/**
 * Définition de toutes les informations renvoyées par la plate-forme à propos d'une session
 *
 * @author Kahina Bouarab
 * @author Youcef Belattaf
 *
 */

public class SessionInfo implements ISessionInfo {

	/** Informations relatives au service*/
	private String aboutService;

	/** L'incremental*/
	private String incremental;

	/** Le nom du service*/
	private String nameService;

	/** Le resultat calculé*/
	private int resultatCalcule;

	/**
	 * Constructeur
	 * @param aboutService TODO A documenter
	 * @param incremental TODO A documenter
	 * @param nameService TODO A documenter
	 * @param resultatCalcule TODO A documenter
	 */
	public SessionInfo(String nameService, String aboutService, String incremental, int resultatCalcule) {
		this.aboutService = aboutService;
		this.incremental = incremental;
		this.nameService = nameService;
		this.resultatCalcule = resultatCalcule;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getAboutService() {
		return this.aboutService;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getIncremental() {
		return this.incremental;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getNameService() {
		return this.nameService;
	}

	/**
	 * {@inheritDoc}
	 */
	public final int getResultatCalcule() {
		return this.resultatCalcule;
	}
}
