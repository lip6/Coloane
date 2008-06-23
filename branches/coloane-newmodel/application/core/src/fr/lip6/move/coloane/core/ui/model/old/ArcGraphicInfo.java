package fr.lip6.move.coloane.core.ui.model.old;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;

public class ArcGraphicInfo implements IArcGraphicInfo {

	/** Il faut conserver le dernier middle point */
	private Point oldMiddlePoint = null;

	private Color color = ColorConstants.black;

	private IArcImpl arc;

	public ArcGraphicInfo(IArcImpl a) {
		this.arc = a;
		this.oldMiddlePoint = this.findMiddlePoint();
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IArcGraphicInfo#findMiddlePoint()
	 */
	public final Point findMiddlePoint() {
		int x = (this.arc.getSource().getGraphicInfo().getLocation().x + this.arc.getTarget().getGraphicInfo().getLocation().x) / 2;
		int y = (this.arc.getSource().getGraphicInfo().getLocation().y + this.arc.getTarget().getGraphicInfo().getLocation().y) / 2;
		return new Point(x, y);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IArcGraphicInfo#updateMiddlePoint()
	 */
	public final void updateMiddlePoint() {
		this.oldMiddlePoint = this.findMiddlePoint();
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IArcGraphicInfo#getMiddlePoint()
	 */
	public final Point getMiddlePoint() {
		return this.oldMiddlePoint;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IArcGraphicInfo#getFigureStyle()
	 */
	public final int getFigureStyle() {
		return this.arc.getElementBase().getNumFigure();
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
		((ArcImplAdapter) arc).firePropertyChange(IArcImpl.COLOR_PROP, oldValue, color);
	}
}
