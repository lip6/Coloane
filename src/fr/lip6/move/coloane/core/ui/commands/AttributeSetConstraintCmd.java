package fr.lip6.move.coloane.core.ui.commands;

import fr.lip6.move.coloane.interfaces.model.IAttribute;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

/**
 * Commande de deplacement d'un attribut
 */
public class AttributeSetConstraintCmd extends Command {

	/** Enregistre la nouvelle taille et le nouvel endroit */
	private final Rectangle newBounds;

	/** Enregistre l'ancienne taille et le nouvel endroit */
	private Point oldBounds;

	/** Noeud à manipuler */
	private final IAttribute attribute;

	/**
	 * Constructeur
	 * @param a attribut
	 * @param newBounds Nouvelles limites
	 */
	public AttributeSetConstraintCmd(IAttribute a, Rectangle newBounds) {
		super(Messages.AttributeSetConstraintCmd_0);
		if (a == null || newBounds == null) {
			throw new IllegalArgumentException();
		}
		this.attribute = a;
		this.newBounds = newBounds.getCopy();
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
		oldBounds = attribute.getGraphicInfo().getLocation();
		this.redo();
	}

	/** {@inheritDoc} */
	@Override
	public final void redo() {
		attribute.getGraphicInfo().setLocation(newBounds.getLocation());
	}

	/** {@inheritDoc} */
	@Override
	public final void undo() {
		attribute.getGraphicInfo().setLocation(oldBounds);
	}

}
