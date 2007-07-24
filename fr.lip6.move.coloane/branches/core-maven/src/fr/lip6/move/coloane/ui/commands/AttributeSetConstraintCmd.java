package fr.lip6.move.coloane.ui.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import fr.lip6.move.coloane.ui.model.IAttributeImpl;

public class AttributeSetConstraintCmd extends Command {

	/** Enregistre la nouvelle taille et le nouvel endroit */
	private final Rectangle newBounds;
   
	/** Enregistre l'ancienne taille et le nouvel endroit */
	private Point oldBounds;

	/** Noeud ˆ manipuler */
	private final IAttributeImpl attribute;

    /**
     * Constructeur
     * @param node noeud
     * @param newBounds Nouvelles limites
     */
    public AttributeSetConstraintCmd(IAttributeImpl attribute, Rectangle newBounds) {
		if (attribute == null || newBounds == null) {
			throw new IllegalArgumentException();
		}
		this.attribute = attribute;
		this.newBounds = newBounds.getCopy();
	}

    /**
     * On peut toujours deplacer un noeud.
     * Le redimensionnement est bloque automatiquement par les EditPolicy
     * @return booleen
     */
	public boolean canExecute() {
		return true;
	}

	/**
     * Executer
	 */
	public void execute() {
		oldBounds = attribute.getGraphicInfo().getLocation();
		this.redo();
	}

	/**
     * Refaire
	 */
	public void redo() {
		attribute.getGraphicInfo().setLocation(newBounds.getLocation().x,newBounds.getLocation().y);
	}

	/**
     * Annuler
	 */
	public void undo() {
		attribute.getGraphicInfo().setLocation(oldBounds.x,oldBounds.y);
	}

}
