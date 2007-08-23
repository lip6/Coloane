package fr.lip6.move.coloane.ui.commands;

import fr.lip6.move.coloane.exceptions.BuildException;
import fr.lip6.move.coloane.main.Translate;
import fr.lip6.move.coloane.ui.model.IArcImpl;

import org.eclipse.gef.commands.Command;

/**
 * Groupe d'actions a entreprendre lors de la suppression d'un arc
 */
public class ArcDeleteCmd extends Command {

	/** L'arc adapte */
	private final IArcImpl arc;


	/**
	 * Effacer un arc
	 * @param toDelete arc a effacer
	 */
	public ArcDeleteCmd(IArcImpl toDelete) {
		setLabel(Translate.getString("ui.commands.ArcDeleteCmd.0")); //$NON-NLS-1$
		this.arc = toDelete;
	}

	/**
	 * Executer
	 */
	@Override
	public final void execute() {
		this.redo();
	}

	/**
	 * Executer (ou Refaire)
	 */
	@Override
	public final void redo() {
		this.arc.getModelAdapter().removeArc(this.arc);
	}

	/**
	 * Annuler
	 */
	@Override
	public final void undo() {
		try {
			this.arc.getModelAdapter().addArc(this.arc);
		} catch (BuildException e) {
			e.getStackTrace();
			System.err.println("Echec : Impossible d'annuler ! : " + e.getMessage()); //$NON-NLS-1$
		}
	}

}
