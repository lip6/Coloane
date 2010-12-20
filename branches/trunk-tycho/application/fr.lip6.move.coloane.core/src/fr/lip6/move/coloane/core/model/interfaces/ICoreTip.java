package fr.lip6.move.coloane.core.model.interfaces;

import fr.lip6.move.coloane.core.model.CoreTipModel.ArcTipModel;

/**
 * Interface for a graphical tip
 * 
 * @author Clément Démoulins
 * @author Jean-Baptiste Voron
 */
public interface ICoreTip extends ILocatedElement {

	/**
	 * @return The link between the tip and the element
	 */
	ArcTipModel getArcModel();

	/**
	 * @return The ID of the object designated by the tip
	 */
	int getIdObject();

	/**
	 * @return The name of the tip
	 */
	String getName();

	/**
	 * @return The value of the tip 
	 */
	String getValue();
}
