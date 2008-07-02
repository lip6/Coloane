package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.formalism.IArcFormalism;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.logging.Logger;

import org.eclipse.gef.commands.Command;

/**
 * Deuxieme etape de la creation d'un lien entre deux noeuds !<br>
 * Cette commande est creee lors du second clic (donc sur l'element d'arrivee).
 */
public class ArcCompleteCmd extends Command {
	private final Logger logger = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

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
	 * @param source noeud source
	 * @param target noeud cible
	 * @param base elementBase
	 */
	public ArcCompleteCmd(INode arcSource, INode arcTarget, IArcFormalism arcFormalism) {
		this.graph = (IGraph) arcSource.getParent();
		this.source = arcSource;
		this.target = arcTarget;
		this.arcFormalism = arcFormalism;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	@Override
	public final boolean canExecute() {

		// La connexion est-elle autorisee par le formalisme ?
		if (!arcFormalism.getFormalism().isLinkAllowed(source.getNodeFormalism(), target.getNodeFormalism())) {
			return false;
		}

		// Evite les doublons en renvoyant faux si le lien existe deja
		for (IArc a : source.getSourceArcs()) {
			if (a.getTarget().equals(target)) {
				return false;
			}
		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public final void execute() {
		// Construction de l'arc
		try {
			arc = graph.createArc(arcFormalism.getName(), source, target);
		} catch (ModelException e) {
			logger.warning(e.toString());
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	@Override
	public final void redo() {
		graph.addArc(arc);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public final void undo() {
		graph.deleteArc(arc);
	}
}
