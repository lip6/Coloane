package fr.lip6.move.coloane.interfaces.model.command;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.IGraph;

/**
 * Commande de création de noeud.<br>
 * Le noeud doit être créé avec son identifiant.
 *
 * @author Jean-Baptiste Voron
 */
public class CreateNodeCommand implements ICommand {
	/** Identifiant de l'objet */
	private int id;

	/** Type de l'objet à créer */
	private String type;

	/**
	 * Constructeur
	 * @param id L'identifiant de l'objet à créer
	 * @param type Le type de l'objet à créer
	 */
	public CreateNodeCommand(int id, String type) {
		this.id = id;
		this.type = type;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void execute(IGraph graph) throws ModelException {
		if (id == 1) { return; }
		graph.createNode(type, id);
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
	public final void undo(IGraph graph) throws ModelException {
		graph.deleteNode(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {
		return "Creation Noeud " + id + " (type: " + type + ")";
	}
}
