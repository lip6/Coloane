package fr.lip6.move.coloane.core.model;

import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IArcGraphicInfo;

import java.util.logging.Logger;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;

/**
 * Description graphique d'un arcs
 */
public class ArcGraphicInfo implements IArcGraphicInfo {
	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** Il faut conserver le dernier middle point */
	private Point oldMiddlePoint = null;

	private Color color = ColorConstants.black;

	private IArc arc;

	/**
	 * Constructeur
	 * @param arc L'arc à considérer
	 */
	public ArcGraphicInfo(IArc arc) {
		this.arc = arc;
		this.oldMiddlePoint = this.findMiddlePoint();
	}

	/** {@inheritDoc} */
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

	/** {@inheritDoc} */
	public final void updateMiddlePoint() {
		LOGGER.finest("updateMiddlePoint"); //$NON-NLS-1$
		this.oldMiddlePoint = findMiddlePoint();
	}

	/** {@inheritDoc} */
	public final Point getMiddlePoint() {
		return this.oldMiddlePoint;
	}

	/** {@inheritDoc} */
	public final Color getColor() {
		return color;
	}

	/** {@inheritDoc} */
	public final void setColor(Color color) {
		LOGGER.finest("setColor(" + color + ")"); //$NON-NLS-1$ //$NON-NLS-2$
		Color oldValue = this.color;
		this.color = color;
		((ArcModel) arc).firePropertyChange(IArc.COLOR_PROP, oldValue, color);
	}
}
