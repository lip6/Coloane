package fr.lip6.move.coloane.apiws.evenements;

import fr.lip6.move.coloane.interfaces.api.evenements.IReceptServiceState;
import fr.lip6.move.wrapper.ws.WrapperStub.QuestionState;

/**
 * Cette classe représent l'objet (qui définie une information sur un service)
 * à envoyer aux observateurs d'événements: récéption d'informations le service en cours d'exécution.
 */
public class ReceptServiceState implements IReceptServiceState {

	private String message;

	private String serviceName;

	private int state;

	/**
	 * Constructeur
	 * @param infoService l'information sur le service reçu de la part du wrapper
	 */
	public ReceptServiceState(QuestionState infoService) {
		this.message = infoService.getMessage();
		this.serviceName = infoService.getNameQuestion();
		this.state = Integer.parseInt(infoService.getEtat());
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
