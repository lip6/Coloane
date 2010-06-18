package fr.lip6.move.coloane.core.model;

import fr.lip6.move.coloane.core.model.interfaces.ICoreTip;
import fr.lip6.move.coloane.core.model.interfaces.ILocatedElement;
import fr.lip6.move.coloane.core.ui.rulers.EditorGuide;
import fr.lip6.move.coloane.interfaces.model.ILocationInfo;
import fr.lip6.move.coloane.interfaces.objects.result.ITip;

import org.eclipse.draw2d.geometry.Point;

/**
 * Modèle d'un tip utilisé par les editPart.<br>
 * Cette permet à un tip d'être déplacé et d'être collé à un guide.
 */
public class CoreTipModel extends AbstractPropertyChange implements ILocatedElement, ILocationInfo, ICoreTip {
	private EditorGuide horizontalGuide;
	private EditorGuide verticalGuide;
	private Point location;
	private ITip tip;
	private ArcTipModel arcModel;

	/**
	 * Modèle d'un arc reliant un IElement avec un CoreTipModel.<br>
	 * Seul les méthodes d'Object sont utilisée donc la classe est vide.
	 */
	public static class ArcTipModel { }

	/**
	 * Construction d'un ITip amélioré pour une gestion graphique
	 * @param tip tip d'origine
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
	
	/**
	 * {@inheritDoc}
	 */
	public void resetLocation() {
		setLocation(new Point(0,0));
	}
}
