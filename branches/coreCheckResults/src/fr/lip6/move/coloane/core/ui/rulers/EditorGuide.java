package fr.lip6.move.coloane.core.ui.rulers;

import fr.lip6.move.coloane.core.model.interfaces.ILocatedElement;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

/**
 * Définition d'un guide
 */
public class EditorGuide {
	/** Property used to notify listeners when the parts attached to a guide are changed */
	public static final String PROPERTY_CHILDREN = "elements changed"; //$NON-NLS-1$

	/** Property used to notify listeners when the guide is re-positioned */
	public static final String PROPERTY_POSITION = "position changed"; //$NON-NLS-1$

	private PropertyChangeSupport listeners = new PropertyChangeSupport(this);

	/** Une map d'élément de modèle fixés sur ce guide */
	private Map<ILocatedElement, Integer> map;

	/** La position de ce guide */
	private int position;

	/** L'indicateur de configuration du guide : <code>true</code> si le guide est horizontal */
	private boolean horizontal;

	/**
	 * Constructeur
	 */
	public EditorGuide() { }

	/**
	 * Constructeur
	 * @param isHorizontal <code>true</code> si le guide est horizontal
	 * Le guide est horizontal ssi il est placé sur un règle verticale
	 */
	public EditorGuide(boolean isHorizontal) {
		setHorizontal(isHorizontal);
	}

	/**
	 * Attache l'EditPart considéré au guide.
	 * L'Edit part est lui aussi modifié pour tenir compte de cet attachement
	 * @param locatedElement L'editpart qui doit être attaché au guide. Si l'editpart est déjà attaché, son alignement est modifié
	 * @param alignment -1 : gauche ou haut; 0 center; 1, droite ou bas
	 */
	public final void attachElement(ILocatedElement locatedElement, int alignment) {
		// Verification de l'existence de l'editpart
		if (getMap().containsKey(locatedElement) && getAlignment(locatedElement) == alignment) {
			return;
		}

		// Sinon... On ajoute l'editpart
		getMap().put(locatedElement, Integer.valueOf(alignment));
		EditorGuide guide;
		if (isHorizontal()) {
			guide = locatedElement.getHorizontalGuide();
		} else {
			guide = locatedElement.getVerticalGuide();
		}

		// Si un guide existe déjà pour cet objet... On le détache d'abord
		if (guide != null && guide != this) {
			guide.detachElement(locatedElement);
		}

		// Accrochage du nouveau guide
		if (isHorizontal()) {
			locatedElement.setHorizontalGuide(this);
		} else {
			locatedElement.setVerticalGuide(this);
		}

		listeners.firePropertyChange(PROPERTY_CHILDREN, null, locatedElement);
	}

	/**
	 * Détache le guide de l'élément de modèle.
	 * @param locatedElement Element de modèle concerné par le détachement du guide
	 */
	public final void detachElement(ILocatedElement locatedElement) {
		if (getMap().containsKey(locatedElement)) {
			getMap().remove(locatedElement);
			if (isHorizontal()) {
				locatedElement.setHorizontalGuide(null);
			} else {
				locatedElement.setVerticalGuide(null);
			}
			listeners.firePropertyChange(PROPERTY_CHILDREN, null, locatedElement);
		}
	}

	/**
	 * Retourne le coté sur lequel est attaché le guide.
	 * Cette information est nécessaire pour permettre l'attachement ou le détachement
	 * d'un élément de modèle pendant le redimensionnement de l'objet.
	 * @param locatedElement L'élément de modèle concerné
	 * @return 1 pour le bas ou la droite; 0 pour le centre; -1 pour le haut ou la gauche; -2 si le guide n'est pas attaché
	 * @see ColoaneEditPolicy
	 */
	public final int getAlignment(ILocatedElement locatedElement) {
		if (getMap().get(locatedElement) != null) {
			return ((Integer) getMap().get(locatedElement)).intValue();
		}
		return -2;
	}

	/**
	 * @return La map contenant tous les objets du modèle attaché au guide (ainsi que leurs alignements)
	 */
	public final Map<ILocatedElement, Integer> getMap() {
		if (map == null) {
			map = new Hashtable<ILocatedElement, Integer>();
		}
		return map;
	}

	/**
	 * @return tous les objets du modèle attachés au guide
	 */
	public final Set<ILocatedElement> getModelObjects() {
		return getMap().keySet();
	}

	/**
	 * @return La position du guide (pixels)
	 */
	public final int getPosition() {
		return position;
	}

	/**
	 * @return <code>true</code> si le guide est horizontal
	 */
	public final boolean isHorizontal() {
		return horizontal;
	}

	/**
	 * Définit l'orientation du guide
	 * @param isHorizontal <code>true</code> si le guide est positionné sur un règle verticale
	 */
	public final void setHorizontal(boolean isHorizontal) {
		horizontal = isHorizontal;
	}

	/**
	 * Positionne le guide
	 * @param offset La position du quige (en pixels)
	 */
	public final void setPosition(int offset) {
		if (this.position != offset) {
			int oldValue = this.position;
			this.position = offset;
			listeners.firePropertyChange(PROPERTY_POSITION, Integer.valueOf(oldValue), Integer.valueOf(position));
		}
	}

	/**
	 * Supprime le listener de modifications de propriétés
	 * @param listener Le listener à supprimer
	 * @see PropertyChangeSupport#removePropertyChangeListener(PropertyChangeListener)
	 */
	public final void removePropertyChangeListener(PropertyChangeListener listener) {
		listeners.removePropertyChangeListener(listener);
	}

	/**
	 * Ajoute le listener de modifications de propriétés
	 * @param listener Le listener à supprimer
	 * @see PropertyChangeSupport#addPropertyChangeListener(PropertyChangeListener)
	 */
	public final void addPropertyChangeListener(PropertyChangeListener listener) {
		listeners.addPropertyChangeListener(listener);
	}
}
