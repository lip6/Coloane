package fr.lip6.move.coloane.interfaces.model.command;

import fr.lip6.move.coloane.interfaces.model.IGraph;

/**
 * Commande de création d'un arc
 *
 * @author Jean-Baptiste Voron
 */
public class CreateArcCommand implements ICommand {
	private int id;
	private String type;
	private int sourceId;
	private int targetId;

	/**
	 * Constructeur
	 * @param id L'identifiant de l'arc qui doit être crée
	 * @param type Le type de l'arc qui doit être crée
	 * @param sourceId La source de l'arc qui doit être créé
	 * @param targetId La cible de l'arc qui doit être créé
	 */
	public CreateArcCommand(int id, String type, int sourceId, int targetId) {
		this.id = id;
		this.type = type;
		this.sourceId = sourceId;
		this.targetId = targetId;
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
		return "Creation de l'arc" + id + " (type: " + type + ") : " + sourceId + " -> " + targetId;
	}
}
