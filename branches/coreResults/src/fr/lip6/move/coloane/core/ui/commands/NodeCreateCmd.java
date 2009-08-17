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
	 * @param graph graphe sur lequel doit être créé le noeud
	 * @param nodeFormalismName nom du formalisme du noeud
	 * @param b Rectangle
	 */
	public NodeCreateCmd(IGraph graph, String nodeFormalismName, Rectangle b) {
		super(Messages.NodeCreateCmd_0);
		this.graph = graph;
		this.nodeFormalismName = nodeFormalismName;
		this.location = b.getLocation();
	}

	/** {@inheritDoc} */
	@Override
	public final boolean canExecute() {
		return graph != null;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		try {
			node = graph.createNode(nodeFormalismName);
			node.getGraphicInfo().setLocation(location);
		} catch (ModelException e) {
			logger.warning(e.toString());
			e.printStackTrace();
		}
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		if (node != null) {
			graph.addNode(node);
		}
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		if (node != null) {
			graph.deleteNode(node);
		}
	}

	public INode getNode() {
		return node;
	}
}
