package fr.lip6.move.coloane.interfaces.model.command;
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
	public void execute() { }

	/**
	 * {@inheritDoc}
	 */
	public void redo() { }

	/**
	 * {@inheritDoc}
	 */
	public void undo() { }
}
