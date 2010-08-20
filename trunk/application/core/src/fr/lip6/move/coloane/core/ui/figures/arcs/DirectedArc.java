package fr.lip6.move.coloane.core.ui.figures.arcs;

import fr.lip6.move.coloane.core.ui.figures.AbstractArcFigure;
import fr.lip6.move.coloane.core.ui.figures.IArcFigure;

import org.eclipse.draw2d.PolygonDecoration;

/**
 * Directed arc definition.<br>
 * This arc only defines a small triangle at its end.
 *
 * @author Jean-Baptiste Voron
 */
public class DirectedArc extends AbstractArcFigure implements IArcFigure {
	/**
	 * Constructor
	 */
	public DirectedArc() {
		super();
		setTargetDecoration(buildDecoration());
	}

	/**
	 * Create the decoration that will be used for the target-side of the arc
	 * @return the decoration
	 */
	protected final PolygonDecoration buildDecoration() {
		PolygonDecoration decoration = new PolygonDecoration();
		decoration.setTemplate(PolygonDecoration.TRIANGLE_TIP);
		decoration.setScale(3, 3);
		
		return decoration;
	}
}
