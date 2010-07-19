package fr.lip6.move.coloane.core.model;

import fr.lip6.move.coloane.core.ui.rulers.EditorRuler;
import fr.lip6.move.coloane.core.ui.rulers.EditorRulerProvider;

import org.eclipse.draw2d.PositionConstants;

/**
 * Graph editor properties
 */
public class GraphEditorProperties {

	/** Drawing rulers */
	private boolean rulersVisibility = true;

	/** Magnetism */
	private boolean snapState = true;

	/** Rulers associated to the editor */
	private EditorRuler leftRuler, topRuler;

	/**
	 * Constructor
	 */
	GraphEditorProperties() {
		this.leftRuler = new EditorRuler(EditorRulerProvider.VERTICAL_ORIENTATION);
		this.topRuler = new EditorRuler(EditorRulerProvider.VERTICAL_ORIENTATION);
	}

	/**
	 * @return The rulers visibility status
	 */
	public final boolean getRulersVisibility() {
		return rulersVisibility;
	}

	/**
	 * Set the state of rulers visibility
	 * @param rulersVisibility <code>true</code> if rulers have to be visible
	 */
	public final void setRulersVisibility(boolean rulersVisibility) {
		this.rulersVisibility = rulersVisibility;
	}

	/**
	 * @return The magnetism status
	 */
	public final boolean getSnapState() {
		return snapState;
	}

	/**
	 * Set the magnetism status
	 * @param snapState <code>true</code> if the magnetisme has to be activated
	 */
	public final void setSnapState(boolean snapState) {
		this.snapState = snapState;
	}

	/**
	 * Fetch the ruler object
	 * @param orientation what kind of ruler
	 * @return the ruler according to the asked orientation
	 */
	public final EditorRuler getRuler(int orientation) {
		EditorRuler result = null;
		switch (orientation) {
			case PositionConstants.NORTH :
				result = topRuler;
				break;
			case PositionConstants.WEST :
				result = leftRuler;
				break;
			default:
				break;
		}
		return result;
	}
}
