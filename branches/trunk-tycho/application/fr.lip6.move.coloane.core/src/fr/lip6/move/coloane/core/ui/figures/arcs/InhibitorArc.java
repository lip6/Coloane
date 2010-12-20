package fr.lip6.move.coloane.core.ui.figures.arcs;

import fr.lip6.move.coloane.core.ui.figures.AbstractArcFigure;
import fr.lip6.move.coloane.core.ui.figures.IArcFigure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.RotatableDecoration;
/**
 * Inhibitor arc definition.<br>
 * This arc defines is graphically terminated by a small round.
 *
 * @author Clément Demoulins
 * @author Jean-Baptiste Voron
 */
public class InhibitorArc extends AbstractArcFigure implements IArcFigure {
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
	private RotatableDecoration buildDecoration() {
		CircleDecoration decoration = new CircleDecoration(7);

		decoration.setFill(true);
		decoration.setBackgroundColor(ColorConstants.white);
		return decoration;
	}
}
