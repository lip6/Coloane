package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.List;

import org.eclipse.gef.commands.Command;

/**
 * Commande de suppression d'un noeud du modele
 */
public class NodeDeleteCmd extends Command {

	/** Noeud a retirer */
	private final INode node;

	/** Graphe contenant le noeud */
	private final IGraph graph;

	/** Garder une copie des connexions sortantes du noeud */
	private List<IArc> sourceConnections = null;

	/** Garder une copie des connexions entrantes vers le noeud */
	private List<IArc> targetConnections = null;

	/**
	 * Constructeur
	 * @param graph
	 * @param node
	 */
	public NodeDeleteCmd(IGraph graph, INode n) {
		this.graph = graph;
		this.node = n;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public final void execute() {
		// Sauvegarde une copie des listes d'arcs entrants et sortant en cas d'annulation
		sourceConnections = node.getSourceArcs();
		targetConnections = node.getTargetArcs();
		this.redo(); // Execute
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	@Override
	public final void redo() {
		graph.deleteNode(node);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public final void undo() {
		graph.addNode(node);

		// Ajout des arcs entrants
		for (IArc arcIn : targetConnections) {
			graph.addArc(arcIn);
		}

		// Ajout des arcs sortants
		for (IArc arcOut : sourceConnections) {
			graph.addArc(arcOut);
		}
	}
}
