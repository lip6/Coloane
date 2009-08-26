

import fr.lip6.move.coloane.core.ui.figures.IArcFigure;
import fr.lip6.move.coloane.core.ui.figures.RoundedPolylineConnection;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.geometry.PointList;

/**
 * Test arc definition.<br>
 * This arc has a black losange at its end.
 *
 * @author yann based on jbv
 */
public class ResetArc extends RoundedPolylineConnection implements IArcFigure {
	/**
	 * Constructor
	 */
	public ResetArc() {
		setTargetDecoration(buildDecoration());
	}

	/**
	 * Create the decoration that will be used for the target-side of the arc
	 * @return the decoration
	 */
	private PolygonDecoration buildDecoration() {
		PolygonDecoration decoration = new PolygonDecoration();

		PointList decorationPointList = new PointList();
		decorationPointList.addPoint(0, 0);
		decorationPointList.addPoint(-4, 4);
		decorationPointList.addPoint(-8, 0);
		decorationPointList.addPoint(-4, -4);

		decoration.setTemplate(decorationPointList);
		decoration.setFill(true);
		decoration.setBackgroundColor(ColorConstants.black);
		

		return decoration;
	}
}
