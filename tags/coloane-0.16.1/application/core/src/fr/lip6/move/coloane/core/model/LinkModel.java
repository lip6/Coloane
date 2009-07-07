package fr.lip6.move.coloane.core.model;

import fr.lip6.move.coloane.core.model.interfaces.ILink;
import fr.lip6.move.coloane.core.model.interfaces.ILinkableElement;
import fr.lip6.move.coloane.core.model.interfaces.IStickyNote;

import java.util.logging.Logger;

/**
 * Modèle d'un lien entre deux éléments (ILinkableElement).
 */
public class LinkModel implements ILink {
	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	private IStickyNote note;
	private ILinkableElement element;

	/**
	 * Création d'un lien entre deux éléments. Un lien n'est pas orienté, il n'y
	 * a aucune différence entre la source et la cible.
	 * <br><br>
	 * Attention, il n'y a aucune restriction au niveau du modèle sur la source
	 * et la cible. Exemple : On pourrait avoir un lien entre deux noeuds.
	 * @param note source du lien
	 * @param element cible du lien
	 */
	LinkModel(IStickyNote note, ILinkableElement element) {
		LOGGER.fine("Création d'un link : " + note + "--" + element); //$NON-NLS-1$ //$NON-NLS-2$
		if (note == null || element == null) {
			throw new NullPointerException("Argument must be not null"); //$NON-NLS-1$
		}
		this.note = note;
		this.element = element;
	}

	/** {@inheritDoc} */
	public final ILinkableElement getElement() {
		return element;
	}

	/** {@inheritDoc} */
	public final IStickyNote getNote() {
		return note;
	}

	/** {@inheritDoc} */
	public final void reconnect(IStickyNote newNote, ILinkableElement newElement) {
		note.removeLink(this);
		element.removeLink(this);
		note = newNote;
		element = newElement;
		note.addLink(this);
		element.addLink(this);
	}
}
