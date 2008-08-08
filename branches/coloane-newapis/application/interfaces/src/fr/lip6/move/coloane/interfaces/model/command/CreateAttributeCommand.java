package fr.lip6.move.coloane.interfaces.model.command;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
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
	/** Indicateur de concaténation : Doit-on concaténer les valeurs ? */
	private boolean concat;

	private String oldValue = "";

	/**
	 * Constructeur
	 * @param name Le nom de l'attribut à créer
	 * @param referenceId Le noeud de référence de l'attribut qui doit être créé
	 * @param value La valeur de l'attribut à créer
	 */
	public CreateAttributeCommand(String name, int referenceId, String value) {
		new CreateAttributeCommand(name, referenceId, value, false);
	}

	/**
	 * Constructeur
	 * @param name Le nom de l'attribut à créer
	 * @param referenceId Le noeud de référence de l'attribut qui doit être créé
	 * @param value La valeur de l'attribut à créer
	 * @param concat Indique si la valeur doit être concatenee a la valeur existante de l'attribut
	 */
	public CreateAttributeCommand(String name, int referenceId, String value, boolean concat) {
		this.name = name;
		this.referenceId = referenceId;
		this.value = value;
		this.concat = concat;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void execute(IGraph graph) throws ModelException {
		if (referenceId == 1) {
			if (graph.getAttribute(name) != null) {
				this.oldValue = graph.getAttribute(name).getValue();
				if (concat) { value = oldValue + "\n" + value; }
				graph.getAttribute(name).setValue(value);
			} else {
				// Attribut du graphe inexistant pour le formalisme
				throw new ModelException("The attribute " + name + " cannot be found for the graph element");
			}
		} else if (graph.getObject(referenceId) != null) {
			IAttribute attribute = graph.getObject(referenceId).getAttribute(name);
			if (attribute != null) {
				this.oldValue = attribute.getValue();
				if (concat) { value = oldValue + "\n" + value; }
				attribute.setValue(value);
			} else {
				// Attribut introuvable
				throw new ModelException("The attribute " + name + " cannot be found for the element" + referenceId);
			}
		} else {
			// L'element n'existe meme pas
			throw new ModelException("The element " + referenceId + " connot be retrived from the graph");
		}
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
		if (referenceId == 1) {
			if (graph.getAttribute(name) != null) {
				graph.getAttribute(name).setValue(this.oldValue);
			} else {
				// Attribut du graphe inexistant pour le formalisme
				throw new ModelException("The attribute " + name + " cannot be found for the graph element");
			}
		} else if (graph.getObject(referenceId) != null) {
			IAttribute attribute = graph.getObject(referenceId).getAttribute(name);
			if (attribute != null) {
				attribute.setValue(this.oldValue);
			} else {
				// Attribut introuvable
				throw new ModelException("The attribute " + name + " cannot be found for the element" + referenceId);
			}
		} else {
			// L'element n'existe meme pas
			throw new ModelException("The element " + referenceId + " connot be retrived from the graph");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {
		return "Creation attribut " + name + " (objet: " + referenceId + ") : value=" + value;
	}
}
