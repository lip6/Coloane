package fr.lip6.move.coloane.apiws.objects.api;

import fr.lip6.move.coloane.interfaces.api.objects.ISessionInfo;
import fr.lip6.move.wrapper.ws.WrapperStub.Session;

public class SessionInfo implements ISessionInfo {

	private String aboutService;

	private String incremental;

	private String nameService;

	private int resultatCalcule;

	/**
	 * Constructeur
	 * @param s L'objet session reçu de la part du wrapper
	 */
	public SessionInfo(Session s) {
		// TODO Voir avec J-B et Silien comment recuperer ces elements.
		this.aboutService = null;
		this.incremental = null;
		this.nameService = null;
		this.resultatCalcule = 0;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getAboutService() {
		return aboutService;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getIncremental() {
		return incremental;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getNameService() {
		return nameService;
	}

	/**
	 * {@inheritDoc}
	 */
	public final int getResultatCalcule() {
		return resultatCalcule;
	}

}
