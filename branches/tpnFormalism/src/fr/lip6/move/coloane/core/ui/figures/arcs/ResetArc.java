package fr.lip6.move.coloane.core.ui.figures.arcs;


import org.eclipse.draw2d.ColorConstants;

/**
 * Reset arc definition.<br>
 * This arc has a black diamond at its end.
 *
 * @author Yann Thierry-Mieg based on Jean-Baptiste Voron
 */
public class ResetArc extends DiamondArc {
	/**
	 * Constructor
	 */
	public ResetArc() {
		super(ColorConstants.black);
	}
}
