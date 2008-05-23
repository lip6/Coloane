package fr.lip6.move.coloane.core.ui.model;

import fr.lip6.move.coloane.core.ui.AttributePropertyDescriptor;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Enumeration;
import java.util.Hashtable;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

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

// FIXME : à supprimer ??? Il y a juste un getter et l'attribut n'est jamais utilisé.
//	/**
//	 * Liste des descriptions des proprietes definies dans ce projet.
//	 * Elles comprennent toutes les informations necessaires pour l'affichage et l'edition de chaque propriete.<br>
//	 * A partir de cette liste, les classes xxxFactory peuvent creer un tableau de IPropertyDescriptor correspondant.
//	 *
//	 * Cet attribut est fonction du formalisme
//	 * @see org.eclipse.ui.views.properties.IPropertyDescriptor
//	 */
//	private IAttributeImpl[] propsList;

	/**
	 * Table des attributs en fonction de leurs identifiant
	 * A surcharger et a implementer dans le constructeur.
	 */
	private Hashtable<Integer, IAttributeImpl> properties = new Hashtable<Integer, IAttributeImpl>();

//	/**
//	 * @return instance de la classe //pas implementer
//	 * @see org.eclipse.ui.views.properties.IPropertySource#getEditableValue()
//	 */
//	public final Object getEditableValue() {
//		return this;
//	}

//	/**
//	 * Indique quelles sont les proprietes qui seront affichees dans la fenetre dediee
//	 * @return IPropertyDescriptor[]
//	 */
//	public final IPropertyDescriptor[] getPropertyDescriptors() {
//
//		// Preparation de la liste des descripteurs
//		IPropertyDescriptor[] liste = new IPropertyDescriptor[this.properties.size()];
//
//		// Recupere la table contenant toutes les proprietes (attributs) des objets
//		for (Enumeration<IAttributeImpl> e = this.properties.elements(); e.hasMoreElements();) {
//			IAttributeImpl prop = (IAttributeImpl) e.nextElement();
//
//			// Calcul de l'indice d'insertion dans la fenetre
//			int indice = prop.getId();
//
//			// Selection du descripteur selon le type d'attribut
//			if (prop.isMultiline()) { // Multiligne
//				liste[indice - 1] = new AttributePropertyDescriptor(prop.getId(), prop.getDisplayName(), "", prop.getValue()); //$NON-NLS-1$
//			} else { // Normal
//				liste[indice - 1] = new TextPropertyDescriptor(prop.getId(), prop.getDisplayName());
//			}
//		}
//
//		return liste;
//	}

//	/**
//	 * Getter pour une propriete.
//	 * Les classe filles doivent surcharge cette methode.
//	 * Dans cette implementation par defaut elles retournent null et font rien.
//	 *
//	 * @param id Nom de la propriete
//	 */
//	public final Object getPropertyValue(Object id) {
//		String idS = id.toString();
//		IAttributeImpl prop = (IAttributeImpl) this.properties.get(idS);
//
//		// Si l'attribut a une veritable valeur
//		if (prop.getValue() != null) {
//
//			// Si l'attribut est multiligne, on ne prend que la premiere ligne
//			if (prop.isMultiline() && (!prop.getValue().equalsIgnoreCase(""))) { //$NON-NLS-1$
//				return (prop.getValue().split("\r"))[0] + " ..."; //$NON-NLS-1$ //$NON-NLS-2$
//			// Sinon on retourne la valeur normale
//			} else {
//				return (String) prop.getValue();
//			}
//		}
//		return new String(""); //$NON-NLS-1$
//	}

//	/**
//	 * Setter pour la propriete.
//	 * Les classe filles doivent surcharge cette methode.
//	 * Dans cette implementation par default elle fait rien.
//	 *
//	 * @param id Nom de la propriete
//	 * @param value Valeur de la propriete
//	 */
//	public final void setPropertyValue(Object id, Object newValue) {
//		IAttributeImpl attribute = this.properties.get(id.toString());
//
//		// Sauvegarde de l'ancienne valeur
//		String oldValue = attribute.getValue();
//
//		// Nouvelle valeur pour l'attribut
//		attribute.setValue(oldValue, (String) newValue);
//	}

//	/**
//	 * Methode qui indique si il ya eu un changement par rapport a une valeur par defaut
//	 * Les classes filles doivent surcharger cette methode.
//	 * Dans cette implementation par defaut elle retourne false.
//	 *
//	 * @param id Nom de la propriete
//	 * @return boolean retourne false (par defaut)
//	 */
//	public final boolean isPropertySet(Object id) {
//		return false;
//	}

//	/**
//	 * Remet l'attribut a sa valeur par defaut, ne rien faire si pas de defaut
//	 * @param id Nom de la propriete
//	 */
//	public void resetPropertyValue(Object id) { }

	/**
	 * Attache un listener (ecouteur) a l'objet
	 * L'objet est donc maintenant sensible aux evenements recus
	 */
	public final synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
		if (listener != null) {
			pcsDelegate.addPropertyChangeListener(listener);
		}
	}

	/**
	 * Enlever a PropertyChangeListener de cet objet.
	 * pas impl�ment�e
	 * @param l une instance non-null de PropertyChangeListener
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

// FIXME : à supprimer ???
//	/**
//	 * Getter pour la liste des proprietes
//	 * @return Liste of descripteurs des propri�t�s
//	 */
//	public final IAttributeImpl[] getPropertyList() {
//		return propsList;
//	}

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
	 * @param attr L'attribut a a jouter dans la table
	 */
	protected final void addProperty(Integer key, IAttributeImpl attr) {
		this.properties.put(key, attr);
	}
}
