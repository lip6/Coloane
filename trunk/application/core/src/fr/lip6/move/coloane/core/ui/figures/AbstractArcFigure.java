package fr.lip6.move.coloane.core.ui.figures;

import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.PolylineConnection;

public abstract class AbstractArcFigure extends PolylineConnection implements IArcFigure {

	public AbstractArcFigure() {
		// La liste des points d'inflexion de l'arc
		this.setConnectionRouter(new BendpointConnectionRouter());

		// Epaisseur de la ligne
		setLineWidth(1);
	}
}
