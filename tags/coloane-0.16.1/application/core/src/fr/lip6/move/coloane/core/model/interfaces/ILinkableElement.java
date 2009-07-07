package fr.lip6.move.coloane.core.model.interfaces;

import java.util.List;

/**
 * Les éléments qui implémentent cette interface pourront être liés entre eux.
 */
public interface ILinkableElement {
	/**
	 * @param link lien à ajouter
	 */
	void addLink(ILink link);

	/**
	 * @param link lien à supprimer
	 * @return <code>true</code> si le lien a bien été supprimé.
	 */
	boolean removeLink(ILink link);

	/**
	 * @return la liste des liens de cette élément
	 */
	List<ILink> getLinks();
}
