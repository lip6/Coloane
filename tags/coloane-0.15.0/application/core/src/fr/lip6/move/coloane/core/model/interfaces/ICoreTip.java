package fr.lip6.move.coloane.core.model.interfaces;

import fr.lip6.move.coloane.core.model.CoreTipModel.ArcTipModel;
import fr.lip6.move.coloane.core.ui.rulers.EditorGuide;
import fr.lip6.move.coloane.interfaces.model.ILocationInfo;

import org.eclipse.draw2d.geometry.Point;

/**
 * Interface pour le modèle d'un tip graphique (ITip adapté)
 */
public interface ICoreTip {

	/**
	 * @return modèle pour l'arc reliant ce tip à l'élément concerné
	 */
	ArcTipModel getArcModel();

	/** {@inheritDoc} */
	ILocationInfo getLocationInfo();

	/** {@inheritDoc} */
	int getIdObject();

	/** {@inheritDoc} */
	String getName();

	/** {@inheritDoc} */
	String getValue();

	/** {@inheritDoc} */
	EditorGuide getHorizontalGuide();

	/** {@inheritDoc} */
	EditorGuide getVerticalGuide();

	/** {@inheritDoc} */
	void setHorizontalGuide(EditorGuide guide);

	/** {@inheritDoc} */
	void setVerticalGuide(EditorGuide guide);

	/** {@inheritDoc} */
	Point getLocation();

	/** {@inheritDoc} */
	void setLocation(Point newLocation);

}
