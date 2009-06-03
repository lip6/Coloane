package fr.lip6.move.coloane.core.ui.figures.arcs;

import fr.lip6.move.coloane.core.ui.figures.IArcFigure;
import fr.lip6.move.coloane.core.ui.figures.RoundedPolylineConnection;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.geometry.PointList;
/**
 * Inhibitor arc definition.<br>
 * This arc defines is graphically terminated by a small round.
 *
 * @author Clément Demoulins
 * @author Jean-Baptiste Voron
 */
public class InhibitorArc extends RoundedPolylineConnection implements IArcFigure {

	// Scale factor to make the round figure smaller
	private static final double SCALE1 = 0.8;
	private static final double SCALE2 = 0.8;

	/**
	 * Constructor
	 */
	public InhibitorArc() {
		super();
		setTargetDecoration(buildDecoration());
	}

	/**
	 * Create the decoration that will be used for the target-side of the arc
	 * @return the decoration
	 */
	private PolygonDecoration buildDecoration() {
		PolygonDecoration decoration = new PolygonDecoration();

		PointList decorationPointList = new PointList();
		decorationPointList.addPoint(-1, 3);
		decorationPointList.addPoint(0, 1);
		decorationPointList.addPoint(0, -1);
		decorationPointList.addPoint(-1, -3);
		decorationPointList.addPoint(-3, -4);
		decorationPointList.addPoint(-5, -4);
		decorationPointList.addPoint(-7, -3);
		decorationPointList.addPoint(-8, -1);
		decorationPointList.addPoint(-8, 1);
		decorationPointList.addPoint(-7, 3);
		decorationPointList.addPoint(-5, 4);
		decorationPointList.addPoint(-3, 4);

		decoration.setTemplate(decorationPointList);
		decoration.setFill(true);
		decoration.setBackgroundColor(ColorConstants.white);
		decoration.setScale(SCALE1, SCALE2);
		return decoration;
	}
}
