package fr.lip6.move.coloane.core.ui.figures.arcs;

/**
 * Test arc definition.<br>
 * This arc has a grey diamond at its end.
 *
 * @author Yann Thierry-Mieg based on Jean-Baptiste Voron
 */
public class TestArc extends DirectedArc {
	/**
	 * Constructor
	 */
	public TestArc() {
		setSourceDecoration(buildDecoration());
	}
}
