package fr.lip6.move.coloane.core.model;

import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IArcGraphicInfo;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;

public class ArcGraphicInfo implements IArcGraphicInfo {

	/** Il faut conserver le dernier middle point */
	private Point oldMiddlePoint = null;

	private Color color = ColorConstants.black;

	private IArc arc;

	public ArcGraphicInfo(IArc a) {
		this.arc = a;
		this.oldMiddlePoint = this.findMiddlePoint();
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IArcGraphicInfo#findMiddlePoint()
	 */
	public final Point findMiddlePoint() {
		Point source, target;
		int size = arc.getInflexPoints().size();
		if (size > 0) {
			if (size % 2 == 0) {
				source = arc.getInflexPoint(size / 2 - 1);
				target = arc.getInflexPoint(size / 2);
			} else {
				source = arc.getInflexPoint(size / 2);
				target = source;
			}
		} else {
			source = arc.getSource().getGraphicInfo().getLocation();
			target = arc.getTarget().getGraphicInfo().getLocation();
		}
		Point middle = source.getTranslated(target).scale(0.5);
		return middle;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IArcGraphicInfo#updateMiddlePoint()
	 */
	public final void updateMiddlePoint() {
		this.oldMiddlePoint = findMiddlePoint();
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IArcGraphicInfo#getMiddlePoint()
	 */
	public final Point getMiddlePoint() {
		return this.oldMiddlePoint;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.IArcGraphicInfo#getColor()
	 */
	public final Color getColor() {
		return color;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.IArcGraphicInfo#setColor(org.eclipse.swt.graphics.Color)
	 */
	public final void setColor(Color color) {
		Color oldValue = this.color;
		this.color = color;
		((ArcModel) arc).firePropertyChange(IArc.COLOR_PROP, oldValue, color);
	}
}
