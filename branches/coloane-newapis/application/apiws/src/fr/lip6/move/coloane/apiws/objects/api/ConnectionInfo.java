package fr.lip6.move.coloane.apiws.objects.api;

import fr.lip6.move.coloane.apiws.stubs.WrapperStub.Authentification;
import fr.lip6.move.coloane.interfaces.api.objects.IConnectionInfo;

import java.util.logging.Logger;

/**
 * Cette classe représent les informations liée a la connection ouverte: <br>
 * <ul>
 * 	<li> le nom du FrameKit </li>
 * 	<li> le major </li>
 * 	<li> le minor </li>
 * </ul>
 *
 * @author Monir CHAOUKI
 */
public class ConnectionInfo implements IConnectionInfo {

	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apiws");

	private String fkName;

	private int major;

	private int minor;

	/**
	 * Constructeur
	 * @param a l'objet Authentification reçu de la part du wrapper
	 */
	public ConnectionInfo(Authentification a) {
		LOGGER.finer("Construction de l'objet ConnectionInfo");
		this.fkName = a.getAckSC().getHostInformation();
		this.major = a.getAckOC().getMajor();
		this.minor = a.getAckOC().getMinor();
	}

	/**
	 * {@inheritDoc}
	 */
	public final int getFkMajor() {
		return major;
	}

	/**
	 * {@inheritDoc}
	 */
	public final int getFkMinor() {
		return minor;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getFkName() {
		return  fkName;
	}

}
