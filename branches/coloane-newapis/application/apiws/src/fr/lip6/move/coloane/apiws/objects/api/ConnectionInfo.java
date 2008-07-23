package fr.lip6.move.coloane.apiws.objects.api;

import fr.lip6.move.coloane.interfaces.api.objects.IConnectionInfo;
import fr.lip6.move.wrapper.ws.WrapperStub.Authentification;

/**
 * Cette classe représent les informations liée a la connection ouverte: <br>
 * <ul>
 * 	<li> le nom du FrameKit </li>
 * 	<li> le major </li>
 * 	<li> le minor </li>
 * </ul>
 */
public class ConnectionInfo implements IConnectionInfo {

	private String fkName;

	private int major;

	private int minor;

	/**
	 * Constructeur
	 * @param a l'objet Authentification reçu de la part du wrapper
	 */
	public ConnectionInfo(Authentification a) {
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
