package fr.lip6.move.coloane.core.ui.figures.arcs;

import fr.lip6.move.coloane.core.ui.figures.RoundedPolylineConnection;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.geometry.PointList;
/**
 * Description d'un arc avec une pointe arrondie (en forme de rond).<br>
 * Cette figure peut être utilisée pour représenter un arc inhibiteur sur un modèle.
 *
 * @author Clément Demoulins
 */
public class InhibitorArc extends RoundedPolylineConnection {
	// On réduit légerement la taille du cercle
	private static final double SCALE1 = 0.8;
	private static final double SCALE2 = 0.8;

	/**
	 * Constructeur
	 */
	public InhibitorArc() {
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

		setTargetDecoration(decoration);
	}

}
