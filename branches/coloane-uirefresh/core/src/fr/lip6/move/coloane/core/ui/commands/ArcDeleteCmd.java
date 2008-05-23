package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.core.exceptions.BuildException;
import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.ui.model.IArcImpl;

import org.eclipse.gef.commands.Command;

/**
 * Commande de suppression d'un arc
 */
public class ArcDeleteCmd extends Command {

	/** L'arc adapte */
	private final IArcImpl arc;

	/**
	 * Effacer un arc
	 * @param toDelete arc a effacer
	 */
	public ArcDeleteCmd(IArcImpl toDelete) {
		this.arc = toDelete;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public final void execute() {
		this.redo();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	@Override
	public final void redo() {
		try {
			arc.getModelAdapter().removeArc(arc);
		} catch (BuildException e) {
			Coloane.getLogger().warning("Impossible de supprimer l'arc du modele " + e.getMessage()); //$NON-NLS-1$
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public final void undo() {
		try {
			arc.getModelAdapter().addArc(arc);
		} catch (BuildException e) {
			Coloane.getLogger().warning("Impossible d'annuler la suppression de l'arc un arc (" + arc.getId() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

}
