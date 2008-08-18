package fr.lip6.move.coloane.core.ui.figures.arcs;

import fr.lip6.move.coloane.core.ui.figures.RoundedPolylineConnection;

//import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.PolygonDecoration;
/**
 * Description d'un arc (figure).<br>
 * Cette figure peut être utilisée pour représenter un arc simple sur un modèle.
 *
 * @author Clément Demoulins
 */
public class SimpleArc extends RoundedPolylineConnection {

	/**
	 * Constructeur
	 */
	public SimpleArc() {
		PolygonDecoration decoration = new PolygonDecoration();
		decoration.setTemplate(PolygonDecoration.TRIANGLE_TIP);
		decoration.setScale(3, 3);

//		setLineStyle(Graphics.LINE_DOT);
		setTargetDecoration(decoration);
	}

}
