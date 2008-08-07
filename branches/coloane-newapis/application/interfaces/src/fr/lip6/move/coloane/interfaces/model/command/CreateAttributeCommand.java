package fr.lip6.move.coloane.interfaces.model.command;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.IGraph;

/**
 * Commande de création d'un attribut
 *
 * @author Jean-Baptiste Voron
 */
public class CreateAttributeCommand implements ICommand {
	/** Le nom de l'attribut à créer */
	private String name;
	/** Le noeud de référence de l'attribut qui doit être créé */
	private int referenceId;
	/** La valeur de l'attribut à créer */
	private String value;

	/**
	 * Constructeur
	 * @param name Le nom de l'attribut à créer
	 * @param referenceId Le noeud de référence de l'attribut qui doit être créé
	 * @param value La valeur de l'attribut à créer
	 */
	public CreateAttributeCommand(String name, int referenceId, String value) {
		this.name = name;
		this.referenceId = referenceId;
		this.value = value;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void execute(IGraph graph) throws ModelException {
		IElement ref = graph.getObject(referenceId);
		ref.getAttribute(name).setValue(value);
	}

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
		return "Creation attribut " + name + " (objet: " + referenceId + ") : value=" + value;
	}
}
