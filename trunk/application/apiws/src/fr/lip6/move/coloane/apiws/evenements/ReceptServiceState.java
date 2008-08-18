package fr.lip6.move.coloane.apiws.evenements;

import fr.lip6.move.coloane.apiws.stubs.WrapperStub.QuestionState;
import fr.lip6.move.coloane.interfaces.api.evenements.IReceptServiceState;

import java.util.logging.Logger;

/**
 * Cette classe représent l'objet (qui définie une information sur un service)
 * à envoyer aux observateurs d'événements: récéption d'informations le service en cours d'exécution.
 *
 * @author Monir CHAOUKI
 */
public class ReceptServiceState implements IReceptServiceState {

	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apiws");

	private String message;

	private String serviceName;

	private int state;

	/**
	 * Constructeur
	 * @param infoService l'information sur le service reçu de la part du wrapper
	 */
	public ReceptServiceState(QuestionState infoService) {
		LOGGER.finest("Construction d'une information sur l'execution d'un service");
		this.message = infoService.getMessage();
		this.serviceName = infoService.getNameQuestion();
		this.state = 1; /* TODO Integer.parseInt(infoService.getEtat());*/
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getMessage() {
		return message;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getServiceName() {
		return serviceName;
	}

	/**
	 * {@inheritDoc}
	 */
	public final int getState() {
		return state;
	}

}
