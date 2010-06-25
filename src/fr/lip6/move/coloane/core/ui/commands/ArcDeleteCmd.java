package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.core.model.GraphModel;
import fr.lip6.move.coloane.core.model.interfaces.ICoreTip;
import fr.lip6.move.coloane.core.model.interfaces.ILink;
import fr.lip6.move.coloane.core.model.interfaces.ILinkableElement;
import fr.lip6.move.coloane.core.motor.session.ISession;
import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.Command;

/**
 * Commande de suppression d'un arc
 */
public class ArcDeleteCmd extends Command {
	/** Graphe contenant l'arc à supprimer */
	private IGraph graph;

	/** L'arc adapte */
	private final IArc arc;

	/** Garder une copie des liens */
	private List<ILink> links = new ArrayList<ILink>();

	private ISession session;

	private List<ICoreTip> tips;

	/**
	 * Effacer un arc
	 * @param toDelete arc a effacer
	 */
	public ArcDeleteCmd(IArc toDelete) {
		super(Messages.ArcDeleteCmd_0);
		this.graph = (IGraph) toDelete.getParent();
		this.arc = toDelete;
		this.session = SessionManager.getInstance().getCurrentSession();
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		tips = new ArrayList<ICoreTip>(session.getTip(arc.getId()));
		if (arc instanceof ILinkableElement) {
			links.addAll(((ILinkableElement) arc).getLinks());
		}
		redo();
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		session.removeAllTips(tips);
		graph.deleteArc(arc);
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		graph.addArc(arc);

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
