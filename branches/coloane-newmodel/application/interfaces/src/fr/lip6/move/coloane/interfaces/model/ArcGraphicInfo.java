package fr.lip6.move.coloane.interfaces.model;

import fr.lip6.move.coloane.interfaces.model.core.ICoreArcGraphicInfo;
import fr.lip6.move.coloane.interfaces.model.interfaces.IArc;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;

public class ArcGraphicInfo implements ICoreArcGraphicInfo {

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
		int x = (this.arc.getSource().getGraphicInfo().getLocation().x + this.arc.getTarget().getGraphicInfo().getLocation().x) / 2;
		int y = (this.arc.getSource().getGraphicInfo().getLocation().y + this.arc.getTarget().getGraphicInfo().getLocation().y) / 2;
		return new Point(x, y);
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

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IArcGraphicInfo#getFigureStyle()
	 */
	public final int getFigureStyle() {
		return this.arc.getArcFormalism().getNumFigure();
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
