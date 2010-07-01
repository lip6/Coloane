package fr.lip6.move.coloane.interfaces.model.command;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import org.eclipse.draw2d.geometry.Point;

/**
 * Commande de changment de position d'un arc
 *
 * @author Jean-Baptiste Voron
 */
public class AttributePositionCommand implements ICommand {
	/** L'identifiant de l'objet référence */
	private int referenceId;
	/** Le nom de l'attribut */
	private String name;
	/** Nouvelle position en X */
	private int x;
	/** Nouvelle position en Y */
	private int y;

	private IAttribute attribute;
	private int oldX;
	private int oldY;

	/**
	 * Constructeur
	 * @param refId Le noeud ou l'arc auquel l'attribut est attaché
	 * @param name Le nom de l'attribut
	 * @param x La position en x
	 * @param y La position en y
	 */
	public AttributePositionCommand(int refId, String name, int x, int y) {
		this.referenceId = refId;
		this.name = name;
		this.x = x;
		this.y = y;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void execute(IGraph graph) throws ModelException {
		// Recherche de l'attribut concerne
		for (IAttribute attribute : graph.getNode(referenceId).getDrawableAttributes()) {
			if (attribute.getName().equals(name)) {
				this.oldX = attribute.getGraphicInfo().getLocation().x;
				this.oldY = attribute.getGraphicInfo().getLocation().y;
				attribute.getGraphicInfo().setLocation(new Point(x, y));
				return;
			}			
		} ;
		throw new ModelException("The attribute " + name + " cannot be found for element " + referenceId);
		//attribute = graph.getObject(referenceId).getAttribute(name);
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
		if (attribute != null) {
			attribute.getGraphicInfo().setLocation(new Point(oldX, oldY));
		} else {
			throw new ModelException("The attribute " + name + " cannot be found for element " + referenceId);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {
		return "Attribut " + name + " (objet: " + referenceId + ") -> x=" + x + "; y=" + y;
	}
}
