package fr.lip6.move.coloane.core.ui;

import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.command.ICommand;

import java.util.List;
import java.util.logging.Logger;

public class ModifyCurrentModel implements Runnable {
	/** Le logger pour la classe */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** La liste des commandes de modification du modèle courant */
	private List<ICommand> commands;

	/** Le modèle courant */
	private IGraph currentModel;

	/**
	 * Constructeur
	 * @param commands La liste des commandes décrivant les modifications à apporter au modèle
	 */
	public ModifyCurrentModel(List<ICommand> commands) {
		this.commands = commands;
		this.currentModel =	SessionManager.getInstance().getCurrentSession().getGraph();
	}

	public void run() {
		try {
			for (ICommand command : commands) {
				command.execute(currentModel);
			}
		} catch (ModelException me) {
			LOGGER.warning("Erreur lors de la construction (modification) du modele sur resultat de la plate-forme"); //$NON-NLS-1$
			// TODO : Undo ?
		}

	}

}
