package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.ArrayList;
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
	private List<IArc> outArcs = null;

	/** Garder une copie des connexions entrantes vers le noeud */
	private List<IArc> inArcs = null;

	/**
	 * Constructeur
	 * @param graph
	 * @param node
	 */
	public NodeDeleteCmd(IGraph graph, INode n) {
		super(Messages.NodeDeleteCmd_0);
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
		outArcs = new ArrayList<IArc>(node.getOutcomingArcs());
		inArcs = new ArrayList<IArc>(node.getIncomingArcs());
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
		for (IArc arc : inArcs) {
			graph.addArc(arc);
		}

		// Ajout des arcs sortants
		for (IArc arc : outArcs) {
			graph.addArc(arc);
		}
	}
}
