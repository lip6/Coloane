package fr.lip6.move.coloane.ui.figures;

import fr.lip6.move.coloane.ui.model.IArcImpl;

import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;

public class ArcFigure extends PolylineConnection implements IArcFigure {

	public ArcFigure(IArcImpl arc) {

		// La decoration de l'arc
		PolygonDecoration dec = new PolygonDecoration();
		dec.setScale(4, 2);
		this.setTargetDecoration(dec);

		// La liste des points d'inflexion de l'arc
		this.setConnectionRouter(new BendpointConnectionRouter());

		// Epaisseur de la ligne
		setLineWidth(1);
	}
}
