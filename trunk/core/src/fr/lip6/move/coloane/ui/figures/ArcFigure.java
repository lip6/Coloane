package fr.lip6.move.coloane.ui.figures;

import fr.lip6.move.coloane.ui.model.IArcGraphicInfo;
import fr.lip6.move.coloane.ui.model.IArcImpl;

import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.PointList;

public class ArcFigure extends PolylineConnection implements IArcFigure {

	private static final double SCALE1 = 0.8;
	private static final double SCALE2 = 0.8;

	public ArcFigure(IArcImpl arc) {
		PolygonDecoration decoration = null;

		// Choix de la decoration de l'arc
		if (arc.getGraphicInfo().getFigureStyle() == IArcGraphicInfo.FIG_ARC_INHIBITOR) {
			decoration = new PolygonDecoration();

			PointList decorationPointList = new PointList();
			decorationPointList.addPoint(3, 3);
			decorationPointList.addPoint(4, 1);
			decorationPointList.addPoint(4, -1);
			decorationPointList.addPoint(3, -3);
			decorationPointList.addPoint(1, -4);
			decorationPointList.addPoint(-1, -4);
			decorationPointList.addPoint(-3, -3);
			decorationPointList.addPoint(-4, -1);
			decorationPointList.addPoint(-4, 1);
			decorationPointList.addPoint(-3, 3);
			decorationPointList.addPoint(-1, 4);
			decorationPointList.addPoint(1, 4);

			decoration.setTemplate(decorationPointList);
			decoration.setFill(true);
			decoration.setBackgroundColor(ColorConstants.white);
			decoration.setScale(SCALE1, SCALE2);
		} else {
			decoration = new PolygonDecoration();
			decoration.setTemplate(PolygonDecoration.TRIANGLE_TIP);
			decoration.setScale(3, 3);
		}
		this.setTargetDecoration(decoration);

		// La liste des points d'inflexion de l'arc
		this.setConnectionRouter(new BendpointConnectionRouter());

		// Epaisseur de la ligne
		setLineWidth(1);
	}
}
