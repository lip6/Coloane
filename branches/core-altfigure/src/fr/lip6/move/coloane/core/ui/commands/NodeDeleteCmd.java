package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.core.model.GraphModel;
import fr.lip6.move.coloane.core.model.interfaces.ICoreTip;
import fr.lip6.move.coloane.core.model.interfaces.ILink;
import fr.lip6.move.coloane.core.model.interfaces.ILinkableElement;
import fr.lip6.move.coloane.core.motor.session.ISession;
import fr.lip6.move.coloane.core.motor.session.SessionManager;
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

	/** Garder une copie des liens */
	private List<ILink> links = new ArrayList<ILink>();

	private List<ICoreTip> tips;

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
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		// Sauvegarde une copie des listes d'arcs entrants et sortant en cas d'annulation
		tips = new ArrayList<ICoreTip>(session.getTip(node.getId()));
		if (node instanceof ILinkableElement) {
			links.addAll(((ILinkableElement) node).getLinks());
		}
		outArcs = new ArrayList<IArc>(node.getOutcomingArcs());
		for (IArc arc : outArcs) {
			tips.addAll(session.getTip(arc.getId()));
			if (arc instanceof ILinkableElement) {
				links.addAll(((ILinkableElement) arc).getLinks());
			}
		}
		inArcs = new ArrayList<IArc>(node.getIncomingArcs());
		for (IArc arc : inArcs) {
			tips.addAll(session.getTip(arc.getId()));
			if (arc instanceof ILinkableElement) {
				links.addAll(((ILinkableElement) arc).getLinks());
			}
		}
		this.redo(); // Execute
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		session.removeAllTips(tips);
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
