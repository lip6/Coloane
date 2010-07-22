package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.core.model.GraphModel;
import fr.lip6.move.coloane.core.model.interfaces.ICoreTip;
import fr.lip6.move.coloane.core.model.interfaces.ILink;
import fr.lip6.move.coloane.core.model.interfaces.ILinkableElement;
import fr.lip6.move.coloane.core.session.ISession;
import fr.lip6.move.coloane.core.session.SessionManager;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.ArrayList;
import java.util.List;

/**
 * Delete a node from the model
 */
public class NodeDeleteCmd extends CheckableCmd {

	/** Graph Model that holds the node to delete */
	private final IGraph graph;

	/** Node to delete */
	private final INode node;

	/** List of outgoings arcs (backup in case of undo) */
	private List<IArc> outArcs = null;

	/** List of incoming arcs (backup in case of undo) */
	private List<IArc> inArcs = null;

	/** List of links (backup in case of undo) */
	private List<ILink> links = new ArrayList<ILink>();

	/** List of tips (backup in case of undo) */
	private List<ICoreTip> tips;

	/** The current session */
	private ISession session;

	/**
	 * Constructeur
	 * @param graph graphe contenant le noeud
	 * @param node noeud à supprimer
	 */
	public NodeDeleteCmd(IGraph graph, INode node) {
		super(Messages.NodeDeleteCmd_0);
		this.graph = graph;
		this.node = node;
		this.session = SessionManager.getInstance().getCurrentSession();
		
		//The node and its associated arcs must be locally checked after the changes 
		addCheckableElement(node);
		for (IArc arc : node.getIncomingArcs()) {
			addCheckableElement(arc);
		}
		for (IArc arc : node.getOutgoingArcs()) {
			addCheckableElement(arc);
		}
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		// Sauvegarde une copie des listes d'arcs entrants et sortant en cas d'annulation
		tips = new ArrayList<ICoreTip>(session.getTipForObject(node.getId()));
		if (node instanceof ILinkableElement) {
			links.addAll(((ILinkableElement) node).getLinks());
		}
		outArcs = new ArrayList<IArc>(node.getOutgoingArcs());
		for (IArc arc : outArcs) {
			tips.addAll(session.getTipForObject(arc.getId()));
			if (arc instanceof ILinkableElement) {
				links.addAll(((ILinkableElement) arc).getLinks());
			}
		}
		inArcs = new ArrayList<IArc>(node.getIncomingArcs());
		for (IArc arc : inArcs) {
			tips.addAll(session.getTipForObject(arc.getId()));
			if (arc instanceof ILinkableElement) {
				links.addAll(((ILinkableElement) arc).getLinks());
			}
		}
		this.redo(); // Execute
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		session.removeTips(tips);
		graph.deleteNode(node);
	}

	/** {@inheritDoc} */
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

		// Ajout des liens
		if (graph instanceof GraphModel) {
			GraphModel gm = (GraphModel) graph;
			for (ILink link : links) {
				gm.addLink(link);
			}
		}

		session.addAllTips(tips);
	}
}
