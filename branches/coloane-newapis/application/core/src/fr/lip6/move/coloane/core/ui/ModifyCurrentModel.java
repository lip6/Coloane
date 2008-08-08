package fr.lip6.move.coloane.core.ui;

import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.core.ui.commands.ModificationResultCommand;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.command.ICommand;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.PlatformUI;

/**
 * Classe responsable de la création de l'action GEF en charge de modifier le modèle courant.
 * Les modifications sont envoyées par la plate-forme.
 * @author jbvoron
 *
 */
public class ModifyCurrentModel implements Runnable {
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

	/**
	 * {@inheritDoc}
	 */
	public final void run() {
		Command modifyCommand = new ModificationResultCommand(this.currentModel, this.commands);
		((ColoaneEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor()).executeCommand(modifyCommand);
	}
}
