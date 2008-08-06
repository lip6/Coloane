package fr.lip6.move.coloane.interfaces.model.command;
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
