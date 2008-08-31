package fr.lip6.move.coloane.apicami.objects;

import fr.lip6.move.coloane.interfaces.api.evenements.IReceptServiceState;

/**
 * cette classe represente un TQ 1,2 ,3 ,4 ,5 ,6
 * @author Kahina Bouarab
 * @author Youcef Belattaf
 *
 */
public class ReceptServiceState implements IReceptServiceState {

	/** le message */
	private String message;

	/** le nom du service */
	private String serviceName;

	/**letat*/
	private int state;

    /**
	 * le constructeur
	 * @param serviceName nom du service
	 * @param state letat
	 * @param message message contenu
	 */
	public ReceptServiceState(String serviceName, int state, String message) {
		this.message = message;
		this.serviceName = serviceName;
		this.state = state;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getMessage() {
        return this.message;
	}
	/**
	 * {@inheritDoc}
	 */
	public final String getServiceName() {
		return this.serviceName;
	}
	/**
	 * {@inheritDoc}
	 */
	public final int getState() {
		return this.state;
	}

}
