package fr.lip6.move.coloane.core.model.interfaces;

/**
 * Interface pour un lien.
 */
public interface ILink {

	/**
	 * Reconnection du lien
	 * @param newSource nouvelle source
	 * @param newTarget nouvelle cible
	 */
	void reconnect(ILinkableElement newSource, ILinkableElement newTarget);

	/**
	 * @return source du lien
	 */
	ILinkableElement getSource();

	/**
	 * @return cible du lien
	 */
	ILinkableElement getTarget();
}
