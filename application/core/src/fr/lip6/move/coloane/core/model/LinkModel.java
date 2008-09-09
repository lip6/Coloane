package fr.lip6.move.coloane.core.model;

import fr.lip6.move.coloane.core.model.interfaces.ILink;
import fr.lip6.move.coloane.core.model.interfaces.ILinkableElement;

import java.util.logging.Logger;

/**
 * Modèle d'un lien entre deux éléments (ILinkableElement).
 */
public class LinkModel implements ILink {
	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	private ILinkableElement source;
	private ILinkableElement target;

	/**
	 * Création d'un lien entre deux éléments. Un lien n'est pas orienté, il n'y
	 * a aucune différence entre la source et la cible.
	 * <br><br>
	 * Attention, il n'y a aucune restriction au niveau du modèle sur la source
	 * et la cible. Exemple : On pourrait avoir un lien entre deux noeuds.
	 * @param source source du lien
	 * @param target cible du lien
	 */
	LinkModel(ILinkableElement source, ILinkableElement target) {
		LOGGER.fine("Création d'un link : " + source + "--" + target); //$NON-NLS-1$ //$NON-NLS-2$
		this.source = source;
		this.target = target;
	}

	/** {@inheritDoc} */
	public final ILinkableElement getSource() {
		return source;
	}

	/** {@inheritDoc} */
	public final ILinkableElement getTarget() {
		return target;
	}

	/** {@inheritDoc} */
	public final void reconnect(ILinkableElement newSource, ILinkableElement newTarget) {
		source.removeLink(this);
		target.removeLink(this);
		source = newSource;
		target = newTarget;
		source.addLink(this);
		target.addLink(this);
	}
}
