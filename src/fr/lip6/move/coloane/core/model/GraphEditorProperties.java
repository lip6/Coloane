package fr.lip6.move.coloane.core.model;

import fr.lip6.move.coloane.core.ui.rulers.EditorRuler;
import org.eclipse.draw2d.PositionConstants;

/**
 * Propriétés de l'éditeur de graphe
 */
public class GraphEditorProperties {

	/** Affichage des règles sur l'éditeur ? */
	private boolean rulersVisibility = true;

	/** État du magnétisme entre objets */
	private boolean snapState = true;

	/** Les règles associées à l'éditeur */
	private EditorRuler leftRuler, topRuler;

	/**
	 * Constructeur
	 */
	GraphEditorProperties() {
		this.leftRuler = new EditorRuler(false);
		this.topRuler = new EditorRuler(true);
	}

	/**
	 * @return <code>true</code> si les règles doivent être visibles
	 */
	public final boolean getRulersVisibility() {
		return rulersVisibility;
	}

	/**
	 * Indique l'état de visibilité des règles qui doivent être affichées sur l'éditeur
	 * @param rulersVisibility <code>true</code> si les règles doivent être visibles
	 */
	public final void setRulersVisibility(boolean rulersVisibility) {
		this.rulersVisibility = rulersVisibility;
	}

	/**
	 * @return <code>true</code> si le magnétisme doit être activé entre les objets
	 */
	public final boolean getSnapState() {
		return snapState;
	}

	/**
	 * Indique l'état du magnétisme entre les objets du graphe
	 * @param snapState <code>true</code> si le magnétisme doit être activé
	 */
	public final void setSnapState(boolean snapState) {
		this.snapState = snapState;
	}

	/**
	 * @param orientation indique quelle règle doitêtre renvoyée
	 * @return la règle de l'éditeur désignée par son <code>orientation</code>
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
