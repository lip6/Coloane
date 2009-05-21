package fr.lip6.move.coloane.core.model.interfaces;

/**
 * Les élements implémentant cette interface supporte la mise en valeur à condition
 * que l'editPart associé récupère l'évenement de demande de mise en valeur.
 */
public interface ISpecialState {
	String SPECIAL_STATE_CHANGE = "Element.SpecialStateChange"; //$NON-NLS-1$

	/**
	 * @param state nouvelle état
	 */
	void setSpecialState(boolean state);
}
