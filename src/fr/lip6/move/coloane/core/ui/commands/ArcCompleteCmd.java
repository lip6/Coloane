package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.formalism.IArcFormalism;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.logging.Logger;

import org.eclipse.gef.commands.Command;

/**
 * Deuxieme étape de la création d'un lien entre deux noeuds !<br>
 * Cette commande est créée lors du second clic (donc sur l'element d'arrivee).
 * @see ArcCreateCmd
 */
public class ArcCompleteCmd extends Command {
	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** Graphe */
	private IGraph graph;

	/** Noeud source */
	private final INode source;

	/** Noeud cible */
	private final INode target;

	/** Element de base du formalisme (arc) */
	private final IArcFormalism arcFormalism;

	/** L'arc */
	private IArc arc;

	/**
	 * Connexion de l'arc
	 * @param arcSource Noeud source de l'arc
	 * @param arcTarget Noeud cible de l'arc
	 * @param arcFormalism Le formalisme de l'arc
	 */
	public ArcCompleteCmd(INode arcSource, INode arcTarget, IArcFormalism arcFormalism) {
		this.graph = (IGraph) arcSource.getParent();
		this.source = arcSource;
		this.target = arcTarget;
		this.arcFormalism = arcFormalism;
	}

	/** {@inheritDoc} */
	@Override
	public final boolean canExecute() {

		// La connexion est-elle autorisee par le formalisme ?
		if (!arcFormalism.getFormalism().isLinkAllowed(this.source, this.target, this.arcFormalism)) {
			return false;
		}

		return true;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		// Construction de l'arc
		try {
			arc = graph.createArc(arcFormalism.getName(), source, target);
		} catch (ModelException e) {
			LOGGER.warning("Impossible de construire l'arc: " + e.toString()); //$NON-NLS-1$
			e.printStackTrace();
		}
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		graph.addArc(arc);
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		graph.deleteArc(arc);
	}
}
