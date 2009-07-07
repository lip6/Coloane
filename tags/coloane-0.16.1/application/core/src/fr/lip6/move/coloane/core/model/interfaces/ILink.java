package fr.lip6.move.coloane.core.model.interfaces;

/**
 * Interface pour un lien.
 */
public interface ILink {

	/**
	 * Reconnection du lien
	 * @param newSource nouvelle note source
	 * @param newTarget nouvelle cible
	 */
	void reconnect(IStickyNote newSource, ILinkableElement newTarget);

	/**
	 * @return source du lien
	 */
	ILinkableElement getElement();

	/**
	 * @return cible du lien
	 */
	IStickyNote getNote();
}
