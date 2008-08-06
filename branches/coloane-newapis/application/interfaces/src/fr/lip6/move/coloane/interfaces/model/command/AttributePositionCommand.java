package fr.lip6.move.coloane.interfaces.model.command;

import fr.lip6.move.coloane.interfaces.model.IGraph;

/**
 * Commande de changment de position d'un arc
 *
 * @author Jean-Baptiste Voron
 */
public class AttributePositionCommand implements ICommand {
	private int refId;
	private String name;
	private int x;
	private int y;

	/**
	 * Constructeur
	 * @param refId Le noeud ou l'arc auquel l'attribut est attaché
	 * @param name Le nom de l'attribut
	 * @param x La position en x
	 * @param y La position en y
	 */
	public AttributePositionCommand(int refId, String name, int x, int y) {
		this.refId = refId;
		this.name = name;
		this.x = x;
		this.y = y;
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
		return "Attribut " + name + " (objet: " + refId + ") -> x=" + x + "; y=" + y;
	}
}
