package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.command.ICommand;

import java.util.List;

import org.eclipse.gef.commands.Command;

public class ModificationResultCommand extends Command {
	/** La liste des commandes de modification du modèle courant */
	private List<ICommand> commands;

	/** Le modèle courant */
	private IGraph currentModel;

	/**
	 * Constructeur
	 * @param currentModel Le modele courant
	 * @param commands La liste des commandes qui doivent être executées
	 */
	public ModificationResultCommand(IGraph currentModel, List<ICommand> commands) {
		this.commands = commands;
		this.currentModel = currentModel;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean canExecute() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void execute() {
		try {
			for (ICommand command : commands) {
				command.execute(currentModel);
			}
		} catch (ModelException me) {
			me.printStackTrace();
			// TODO : Undo ?
		}
	}
}
