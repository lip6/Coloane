package fr.lip6.move.coloane.interfaces.model.command;

import fr.lip6.move.coloane.interfaces.model.IGraph;

/**
 * Commande de suppression d'un objet<br>
 * Cet objet peut-être un arc ou un noeud au choix.
 *
 * @author Jean-Baptiste Voron
 */
public class DeleteObjectCommand implements ICommand {
	/** Identifiant de l'objet à supprimer */
	private int id;

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
		return "Suppression de l'objet id=" + id;
	}
}
