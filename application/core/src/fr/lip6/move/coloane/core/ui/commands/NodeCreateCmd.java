package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.logging.Logger;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

/**
 * Commande pour ajouter un nouveau noeud
 */
public class NodeCreateCmd extends Command {
	private final Logger logger = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** Nouveau noeud */
	private INode node;

	/** Graphe */
	private final IGraph graph;

	/** Limite */
	private Point location;

	/** Nom du formalisme pour ce noeud. */
	private String nodeFormalismName;

	/**
	 * Creer une commande qui ajoutera le noeud au graphe
	 *
	 * @param node Le nouveau noeud à ajouter
	 * @param m Le modèle qui contiendra le noeud
	 * @param bound Les limites du noeud; (la taille peut être (-1, -1))
	 */
	public NodeCreateCmd(IGraph graph, String nodeFormalismName, Rectangle b) {
		this.graph = graph;
		this.nodeFormalismName = nodeFormalismName;
		this.location = b.getLocation();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	@Override
	public final boolean canExecute() {
		return graph != null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public final void execute() {
		try {
			node = graph.createNode(nodeFormalismName);
		} catch (ModelException e) {
			logger.warning(e.toString());
			e.printStackTrace();
		}
		node.getGraphicInfo().setLocation(location);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	@Override
	public final void redo() {
		graph.addNode(node);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public final void undo() {
		graph.deleteNode(node);
	}
}
