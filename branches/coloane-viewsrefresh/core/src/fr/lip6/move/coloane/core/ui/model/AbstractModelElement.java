package fr.lip6.move.coloane.core.ui.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Hashtable;

/**
 * Cette classe abstraite de base fournit les fonctionalites pour tous les elements de modele, y compris :
 * <ul>
 * <li>support de changement de propriete : utilise pour notifier les controleurs (dans le pattern MVC)</li>
 * <li>support de source de propriete : utilise pour afficher les proprietes dans Property View du workbench d'Eclipse.</li>
 * <li>support de stockage : cette classe implemente l'interface Serializable pour un support de stockage standard.
 * </ul>
 */

public abstract class AbstractModelElement implements IElement {

	private PropertyChangeSupport pcsDelegate = new PropertyChangeSupport(this);

	/**
	 * Table des attributs en fonction de leurs identifiant
	 * A surcharger et a implementer dans le constructeur.
	 */
	private Hashtable<Integer, IAttributeImpl> properties = new Hashtable<Integer, IAttributeImpl>();

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.IElement#addPropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public final synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
		if (listener != null) {
			pcsDelegate.addPropertyChangeListener(listener);
		}
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.IElement#removePropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public final synchronized void removePropertyChangeListener(PropertyChangeListener listener) {
		if (listener != null) {
			pcsDelegate.removePropertyChangeListener(listener);
		}
	}

	/**
	 * Notifier un changement de propriete et un ecouteur (normalement c'est un controleur).
	 * @param property Nom de la propriete changee
	 * @param oldValue valeur ancienne
	 * @param newValue valeur nouvelle
	 */
	protected final void firePropertyChange(String property, Object oldValue, Object newValue) {
		if (pcsDelegate.hasListeners(property)) {
			pcsDelegate.firePropertyChange(property, oldValue, newValue);
		}
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.IElement#getProperties()
	 */
	public final Hashtable<Integer, IAttributeImpl> getProperties() {
		return properties;
	}

	/**
	 * Vide la table des proprietes
	 * @return void
	 */
	protected final void clearProperties() {
		this.properties.clear();
	}

	/**
	 * Ajoute une propriete a la liste
	 * @param key La clef d'insertion dans la table de hachage
	 * @param attr L'attribut a ajouter dans la table
	 */
	protected final void addProperty(Integer key, IAttributeImpl attr) {
		this.properties.put(key, attr);
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.IElement#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	public final void setPropertyValue(Object id, Object newValue) {
		IAttributeImpl attribute = (IAttributeImpl) this.properties.get(id.toString());

		// Sauvegarde de l'ancienne valeur
		String oldValue = attribute.getValue();

		// Nouvelle valeur pour l'attribut
		attribute.setValue(oldValue, (String) newValue);
	}
}
