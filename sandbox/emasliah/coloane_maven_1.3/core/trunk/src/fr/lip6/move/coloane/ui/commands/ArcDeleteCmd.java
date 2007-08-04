package fr.lip6.move.coloane.ui.commands;

import org.eclipse.gef.commands.Command;

import fr.lip6.move.coloane.exceptions.BuildException;
import fr.lip6.move.coloane.main.Coloane;
import fr.lip6.move.coloane.ui.model.IArcImpl;

/**
 * @author yutao
 *
 */
public class ArcDeleteCmd extends Command {

	/** L'arc adapte */
	private final IArcImpl connection;


	/**
	 * Effacer un arc
	 * @param arc arc � effacer
	 */
	public ArcDeleteCmd(IArcImpl arc) {

		if (arc == null) {
			throw new IllegalArgumentException();
		}
		setLabel(Coloane.traduction.getString("ui.commands.ArcDeleteCmd.0")); //$NON-NLS-1$
		this.connection = arc;
	}

	/**
	 * Executer
	 *
	 */
	public void execute() {
		this.connection.getModelAdapter().removeArc(this.connection);
		this.connection.disconnect();
	}

	/**
	 * Annuler
	 *
	 */
	public void undo() {
		try {
			this.connection.reconnect();
		} catch (BuildException e) {
			e.getStackTrace();
			System.err.println("Echec : Impossible d'annuler ! : "+e.getMessage()); //$NON-NLS-1$
		}
	}

}