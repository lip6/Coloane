package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.command.ICommand;

import java.util.List;

import org.eclipse.gef.commands.Command;

/**
 * Commande GEF qui permet d'executer un set de commandes en provenance d'une plate-forme
 *
 * @author Jean-Baptiste Voron
 */
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
			// Parcours de toutes les commandes et execution de celles-ci
			for (ICommand command : commands) {
				if (command == null) { continue; }
				command.execute(currentModel);
			}
		} catch (ModelException me) {
			System.err.println("Erreur lors de l'application des modifications " + me.getMessage()); //$NON-NLS-1$
			me.printStackTrace();
			// TODO : Undo ?
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void redo() {
		this.execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void undo() {
		try {
			for (int i = this.commands.size() - 1; i >= 0; i--) {
				if (this.commands.get(i) == null) { continue; }
				this.commands.get(i).undo(this.currentModel);
			}
		} catch (ModelException me) {
			System.err.println("Erreur lors de l'annulation des modifications " + me.getMessage()); //$NON-NLS-1$
			me.printStackTrace();
			// TODO : Undo ?
		}
	}
}
