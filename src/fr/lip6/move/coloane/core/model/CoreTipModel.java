package fr.lip6.move.coloane.core.model;

import fr.lip6.move.coloane.core.model.interfaces.ICoreTip;
import fr.lip6.move.coloane.core.model.interfaces.ILocatedElement;
import fr.lip6.move.coloane.core.ui.rulers.EditorGuide;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.ILocationInfo;
import fr.lip6.move.coloane.interfaces.objects.result.ITip;

import org.eclipse.draw2d.geometry.Point;

/**
 * Describe a special element : TIP.<br>
 * A tip is like a bubble and is often sticked to an element.
 * 
 * @author Clément Démoulins
 */
public class CoreTipModel extends AbstractPropertyChange implements ILocatedElement, ILocationInfo, ICoreTip {

	/** Guides */
	private EditorGuide horizontalGuide;
	private EditorGuide verticalGuide;
	
	/** Current position */
	private Point location;
	/** The description of the tip */
	private ITip tip;
	private ArcTipModel arcModel;

	/**
	 * Class that describes an arc between an {@link IElement} and a {@link CoreTipModel}.<br>
	 * Only {@link Object} methods are used here.
	 */
	public static class ArcTipModel { }

	/**
	 * Constructor
	 * @param tip The tip
	 */
	public CoreTipModel(ITip tip) {
		this.tip = tip;
		this.arcModel = new ArcTipModel();
	}

	/** {@inheritDoc} */
	public final ArcTipModel getArcModel() {
		return arcModel;
	}

	/** {@inheritDoc} */
	public final ILocationInfo getLocationInfo() {
		return this;
	}

	/** {@inheritDoc} */
	public final int getIdObject() {
		return tip.getIdObject();
	}

	/** {@inheritDoc} */
	public final String getName() {
		return tip.getName();
	}

	/** {@inheritDoc} */
	public final String getValue() {
		return tip.getValue();
	}

	/** {@inheritDoc} */
	public final EditorGuide getHorizontalGuide() {
		return this.horizontalGuide;
	}

	/** {@inheritDoc} */
	public final EditorGuide getVerticalGuide() {
		return this.verticalGuide;
	}

	/** {@inheritDoc} */
	public final void setHorizontalGuide(EditorGuide guide) {
		this.horizontalGuide = guide;
	}

	/** {@inheritDoc} */
	public final void setVerticalGuide(EditorGuide guide) {
		this.verticalGuide = guide;
	}

	/** {@inheritDoc} */
	public final Point getLocation() {
		return location;
	}

	/** {@inheritDoc} */
	public final void setLocation(Point newLocation) {
		Point oldLocation = this.location;
		location = newLocation;
		firePropertyChange(LOCATION_PROP, oldLocation, location);
	}
	
	/** {@inheritDoc} */
	public final void resetLocation() {
		setLocation(new Point(0, 0));
	}
}
