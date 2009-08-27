package fr.lip6.move.coloane.interfaces.model;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;

/**
 * This interface defines the properties and the graphical behavior associated to an arc {@link IArc}.
 */
public interface IArcGraphicInfo {
	/**
	 * @return The point located in the middle of the arc
	 * TODO: Tourver la différence avec la méthode {@link #getMiddlePoint()}
	 */
	Point findMiddlePoint();

	/**
	 * Update the location of the middle point.<br>
	 * This method must be called every time you add, delete or modifiy an existing bendpoint.
	 */
	void updateMiddlePoint();

	/**
	 * @return The point located in the middle of the arc.
	 */
	Point getMiddlePoint();

	/**
	 * @return The arc color
	 */
	Color getColor();

	/**
	 * Set the arc color (front color)
	 * @param color The arc color
	 */
	void setColor(Color color);

	/**
	 * @return The current arc curvature status
	 */
	boolean getCurve();

	/**
	 * Set the arc curvature status
	 * @param flag <code>true</code> if the arc has to be curved; <code>false</code> if straight.
	 */
	void setCurve(boolean flag);
}
