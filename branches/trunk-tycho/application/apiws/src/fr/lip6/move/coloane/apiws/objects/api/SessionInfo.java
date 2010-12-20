package fr.lip6.move.coloane.apiws.objects.api;

import fr.lip6.move.coloane.apiws.stubs.WrapperStub.Session;
import fr.lip6.move.coloane.interfaces.api.objects.ISessionInfo;

import java.util.logging.Logger;

/**
 * Cette classe représentent les informations liée à la session ouverte:<br>
 * <ul>
 * 	<li> aboutService: non retourné par le wrapper </li>
 * 	<li> incremental: non retourné par le wrapper </li>
 * 	<li> nameService: non retourné par le wrapper </li>
 * 	<li> resultatCalcule: non retourné par le wrapper </li>
 * </ul>
 *
 * @author Monir CHAOUKI
 */
public class SessionInfo implements ISessionInfo {

	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apiws");

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
		LOGGER.finer("Construction de l'objet SessionInfo");
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
