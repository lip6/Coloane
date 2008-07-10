package fr.lip6.move.coloane.core.ui.figures.arcs;

import fr.lip6.move.coloane.core.ui.figures.AbstractArcFigure;
import fr.lip6.move.coloane.interfaces.model.IArcGraphicInfo;

import org.eclipse.draw2d.PolygonDecoration;

public class SimpleArc extends AbstractArcFigure {

	public SimpleArc(IArcGraphicInfo graphicInfo) {
		super(graphicInfo);

		PolygonDecoration decoration = new PolygonDecoration();
		decoration.setTemplate(PolygonDecoration.TRIANGLE_TIP);
		decoration.setScale(3, 3);

		setTargetDecoration(decoration);
	}

}
