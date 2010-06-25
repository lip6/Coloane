package fr.lip6.move.coloane.interfaces.model.command;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.ArrayList;
import java.util.List;

/**
 * Commande de suppression d'un objet<br>
 * Cet objet peut-être un arc ou un noeud au choix.
 *
 * @author Jean-Baptiste Voron
 */
public class DeleteObjectCommand implements ICommand {
	/** Identifiant de l'objet à supprimer */
	private int id;

	/** L'element a sauvegarder */
	private IElement element;
	private List<IArc> outputArcs;
	private List<IArc> inputArcs;

	/**
	 * Constructeur
	 * @param id Identifiant de l'objet à supprimer
	 */
	public DeleteObjectCommand(int id) {
		this.id = id;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void execute(IGraph graph) throws ModelException {
		// Sauvegarde
		element = graph.getObject(id);
		if (element == null) { return; }
		if (element instanceof INode) {
			this.inputArcs = new ArrayList<IArc>(((INode) element).getIncomingArcs());
			this.outputArcs = new ArrayList<IArc>(((INode) element).getIncomingArcs());
		}
		graph.deleteObject(id);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void redo(IGraph graph) throws ModelException {
		this.execute(graph);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void undo(IGraph graph) {
		if (element == null) { return; }
		if (this.element instanceof INode) {
			graph.addNode((INode) element);
			for (IArc arc : this.inputArcs) { graph.addArc(arc); }
			for (IArc arc : this.outputArcs) { graph.addArc(arc); }
		} else {
			graph.addArc((IArc) element);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {
		return "Suppression de l'objet id=" + id;
	}
}
