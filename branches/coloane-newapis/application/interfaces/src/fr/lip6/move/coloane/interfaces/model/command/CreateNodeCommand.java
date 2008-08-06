package fr.lip6.move.coloane.interfaces.model.command;

import fr.lip6.move.coloane.interfaces.model.IGraph;

/**
 * Commande de création de noeud
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
	public void execute(IGraph graph) { }

	/**
	 * {@inheritDoc}
	 */
	public void redo(IGraph graph) { }

	/**
	 * {@inheritDoc}
	 */
	public void undo(IGraph graph) { }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {
		return "Creation Noeud " + id + " (type: " + type + ")";
	}
}
