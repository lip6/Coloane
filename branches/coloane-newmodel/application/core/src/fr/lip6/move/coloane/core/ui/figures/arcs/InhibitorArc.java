package fr.lip6.move.coloane.core.ui.figures.arcs;

import fr.lip6.move.coloane.core.ui.figures.AbstractArcFigure;
import fr.lip6.move.coloane.interfaces.model.IArcGraphicInfo;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.geometry.PointList;

public class InhibitorArc extends AbstractArcFigure {
	// TODO : à documenter, pourquoi 0.8 ?
	private static final double SCALE1 = 0.8;
	private static final double SCALE2 = 0.8;

	public InhibitorArc(IArcGraphicInfo graphicInfo) {
		super(graphicInfo);

		PolygonDecoration decoration = new PolygonDecoration();

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

		setTargetDecoration(decoration);
	}

}
