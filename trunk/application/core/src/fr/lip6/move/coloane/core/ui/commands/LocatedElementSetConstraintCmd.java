package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.core.model.interfaces.ILocatedElement;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

/**
 * Commande pour deplacer un ILocatedElement
 */
public class LocatedElementSetConstraintCmd extends Command {

	/** Enregistre la nouvelle taille et le nouvel endroit */
	private final Point newLocation;
	/** Enregistre l'ancienne taille et le nouvel endroit */
	private Point oldLocation;

	/** Noeud a manipuler */
	private final ILocatedElement element;

	/**
	 * Constructeur
	 * @param element élément à déplacer
	 * @param newBounds Nouvelles limites
	 */
	public LocatedElementSetConstraintCmd(ILocatedElement element, Rectangle newBounds) {
		super(Messages.NodeSetConstraintCmd_0);
		if (element == null || newBounds == null) {
			throw new NullPointerException();
		}
		this.element = element;
		this.newLocation = newBounds.getLocation().getCopy();
		this.newLocation.x = Math.max(this.newLocation.x, 0);
		this.newLocation.y = Math.max(this.newLocation.y, 0);
	}

	/**
	 * On peut toujours deplacer un noeud.
	 * Le redimensionnement est bloque automatiquement par les EditPolicy
	 * @return true
	 */
	@Override
	public final boolean canExecute() {
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public final void execute() {
		oldLocation = new Point(element.getLocationInfo().getLocation());
		redo();
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		element.getLocationInfo().setLocation(newLocation);
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		element.getLocationInfo().setLocation(oldLocation);
	}

}
