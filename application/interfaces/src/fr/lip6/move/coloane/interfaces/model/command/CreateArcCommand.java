package fr.lip6.move.coloane.interfaces.model.command;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

/**
 * Commande de création d'un arc
 *
 * @author Jean-Baptiste Voron
 */
public class CreateArcCommand implements ICommand {
	/** L'identifiant de l'arc qui doit être créé */
	private int id;
	/** Le type de l'arc qui doit être créé */
	private String type;
	/** L'identifiant de la source de l'arc */
	private int sourceId;
	/** L'identifiant de la cible de l'arc */
	private int targetId;

	private INode source;
	private INode target;

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
	public final void execute(IGraph graph) throws ModelException {
		source = graph.getNode(sourceId);
		target = graph.getNode(targetId);
		graph.createArc(type, source, target, id);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void redo(IGraph graph) throws ModelException {
		graph.createArc(type, source, target, id);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void undo(IGraph graph) throws ModelException {
		graph.deleteArc(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {
		return "Creation de l'arc" + id + " (type: " + type + ") : " + sourceId + " -> " + targetId;
	}
}
