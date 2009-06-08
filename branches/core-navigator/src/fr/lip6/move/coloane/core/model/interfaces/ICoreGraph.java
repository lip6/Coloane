package fr.lip6.move.coloane.core.model.interfaces;

import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.util.List;

/**
 * Interface "privé" (réservé au core) d'un graphe.
 */
public interface ICoreGraph extends IGraph {
	/** ID de propriété lorsqu'une note est ajoutée au modèle */
	String STICKY_ADD_PROP = "Model.AddSticky"; //$NON-NLS-1$

	/** ID de propriete lorsqu'une note est supprimée du modèle */
	String STICKY_REMOVED_PROP = "Model.RemovingSticky"; //$NON-NLS-1$

	/** ID de propriété lorsqu'une note est ajoutée au modèle */
	String LINK_ADD_PROP = "Model.AddLink"; //$NON-NLS-1$

	/** ID de propriete lorsqu'une note est supprimée du modèle */
	String LINK_REMOVED_PROP = "Model.RemovingLink"; //$NON-NLS-1$

	/**
	 * Ajoute la note au graphe courant
	 * @param sticky La stickyNote à ajouter
	 */
	void addSticky(IStickyNote sticky);

	/**
	 * Création d'une note
	 * @return la note créée
	 */
	IStickyNote createStickyNote();

	/**
	 * Supprime la note du graphe courante
	 * @param note La StickyNote à supprimer
	 * @return <tt>false</tt> si aucune note n'a été supprimée, <tt>true</tt> sinon
	 */
	boolean deleteSticky(IStickyNote note);

	/**
	 * @return La liste des toutes les notes du graphe
	 */
	List<IStickyNote> getStickyNotes();

	/**
	 * Ajoute le lien au graphe courant
	 * @param link Le lien à ajouter
	 */
	void addLink(ILink link);

	/**
	 * Créer un lien.
	 * @param note source
	 * @param element cible
	 * @return le lien créé
	 */
	ILink createLink(IStickyNote note, ILinkableElement element);

	/**
	 * @param link Lien à supprimer
	 * @return <code>true</code> si le lien a été supprimé
	 */
	boolean deleteLink(ILink link);

	/**
	 * @return liste des liens
	 */
	List<ILink> getLinks();
}
