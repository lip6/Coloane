package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import org.eclipse.gef.commands.Command;

/**
 * Commande de suppression d'un arc
 */
public class ArcDeleteCmd extends Command {
	/** Graphe contenant l'arc à supprimer */
	private IGraph graph;

	/** L'arc adapte */
	private final IArc arc;

	/**
	 * Effacer un arc
	 * @param toDelete arc a effacer
	 */
	public ArcDeleteCmd(IArc toDelete) {
		super(Messages.ArcDeleteCmd_0);
		this.graph = (IGraph) toDelete.getParent();
		this.arc = toDelete;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		this.redo();
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		graph.deleteArc(arc);
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		graph.addArc(arc);
	}
}
