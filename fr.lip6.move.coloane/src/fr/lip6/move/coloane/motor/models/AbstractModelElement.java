package fr.lip6.move.coloane.motor.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;


import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import fr.lip6.move.coloane.interfaces.models.IAttribute;
import fr.lip6.move.coloane.main.Coloane;

/**
 * Cette classe abstraite de base fournit les fonctionalites pour tous les elements de modele, y compris :
 * <ul>
 * <li>support de changement de propriete : utilise pour notifier les controleurs (dans le pattern MVC)</li>
 * <li>support de source de propriete : utilise pour afficher les proprietes dans Property View du workbench d'Eclipse.</li>
 * <li>support de stockage : cette classe implemente l'interface Serializable pour un support de stockage standard.
 * </ul>
 */

public abstract class AbstractModelElement implements IPropertySource, Serializable {

	/**
	 * Non-implementee
	 * Pour supporter le changement de propriete
	 */
	private PropertyChangeSupport pcsDelegate = new PropertyChangeSupport(this);

	/**
	 * Liste des descriptions des proprietes definies dans ce projet. 
	 * Elles comprennent toutes les informations necessaires pour l'affichage et l'edition de chaque propriete.<br>
	 * A partir de cette liste, les classes xxxFactory peuvent creer un tableau de IPropertyDescriptor correspondant.
	 * 
	 * Cet attribut est fonction du formalisme
	 * @see org.eclipse.ui.views.properties.IPropertyDescriptor
	 */
	protected IAttribute[] propsList;

	/**
	 * Identifiant unique pour le modele
	 * Utile pour framekit qui a besoin que les noeuds et arcs soient identifies
	 */
	public static int uniqueId = 2;

	/**
	 * Table des attributs en fonction de leurs identifiant
	 * A surcharger et a implementer dans le constructeur.
	 */
	protected Hashtable<Object,AttributeImplAdapter> properties = new Hashtable<Object,AttributeImplAdapter>();

	/**
	 * @return instance de la classe //pas implementer
	 * @see org.eclipse.ui.views.properties.IPropertySource#getEditableValue()
	 */
	public Object getEditableValue() {
		return this;
	}

	/**
	 * Indique quelles sont les proprietes qui seront affichees dans la fenetre dediee 
	 * @return IPropertyDescriptor[]
	 */
	public IPropertyDescriptor[] getPropertyDescriptors() {

		IPropertyDescriptor[] liste = new TextPropertyDescriptor[this.properties.size()];
		int i = 0;
		for (Enumeration e = this.properties.elements(); e.hasMoreElements();) {
			AttributeImplAdapter prop = (AttributeImplAdapter) e.nextElement();
			liste[i++] = new TextPropertyDescriptor(prop.getId(), prop.getDisplayName());
		}

		return liste;
	}

	/**
	 * Getter pour une propriete. 
	 * Les classe filles doivent surcharge cette methode. 
	 * Dans cette implementation par defaut elles retournent null et font rien.
	 * 
	 * @param id Nom de la propriete
	 */
	public Object getPropertyValue(Object id) {
		AttributeImplAdapter prop = (AttributeImplAdapter) this.properties.get(id);
		if (prop.getValue() != null) {
			return (String) prop.getValue();
		}
		
		return new String("");

	}

	/**
	 * Setter pour la propriete.
	 * Les classe filles doivent surcharge cette methode. 
	 * Dans cette implementation par default elle fait rien.
	 * 
	 * @param id Nom de la propriete
	 * @param value Valeur de la propriete
	 */
	public void setPropertyValue(Object id, Object value) {
		AttributeImplAdapter prop = (AttributeImplAdapter) this.properties.get(id);
		prop.setValue(value != null ? (String)value: "");
	}

	/**
	 * Methode qui indique si il ya eu un changement par rapport a une valeur par defaut
	 * Les classes filles doivent surcharger cette methode. 
	 * Dans cette implementation par defaut elle retourne false.
	 * 
	 * @param id Nom de la propriété
	 * @return boolean retourne false (par defaut) 
	 */
	public boolean isPropertySet(Object id) {
		return false;
	}

	/**
	 * Remet l'attribut a sa valeur par defaut, ne rien faire si pas de defaut
	 * @param id Nom de la propriete
	 */
	public void resetPropertyValue(Object id) {
	}

	/**
	 * Attacher un non-null PropertyChangeListener et cet objet.
	 * @param l une instance non-null de PropertyChangeListener
	 * @throws IllegalArgumentException
	 */
	public synchronized void addPropertyChangeListener(PropertyChangeListener l) {
		if (l == null) {
			throw new IllegalArgumentException();
		}
		pcsDelegate.addPropertyChangeListener(l);
	}

	/**
	 * Enlever a PropertyChangeListener de cet objet.
	 * pas implémentée
	 * @param l une instance non-null de PropertyChangeListener
	 */
	public synchronized void removePropertyChangeListener(PropertyChangeListener l) {
		if (l != null) {
			pcsDelegate.removePropertyChangeListener(l);
		}
	}

	/**
	 * Notifier un changement de propriete et un ecouteur (normalement c'est un controleur).
	 * @param property Nom de la propriete changee
	 * @param oldValue valeur ancienne
	 * @param newValue valeur nouvelle
	 */
	protected void firePropertyChange(String property, Object oldValue, Object newValue) {
		if (pcsDelegate.hasListeners(property)) {
			pcsDelegate.firePropertyChange(property, oldValue, newValue);
		}
	}

	/**
	 * Getter pour la liste des proprietes
	 * @return Liste of descripteurs des propriétés
	 */
	public IAttribute[] getPropertyList() {
		return propsList;
	}

	/**
	 * Table des PropertyImplAdapter de l'element du noeud
	 * @return la table des PropertyImplAdapter de l'element du noeud
	 */
	public Hashtable getProperties() {
		return properties;
	}

	/**
	 * TODO: Description
	 */
	public void initPropertyChange() {
		if (pcsDelegate == null) {
			pcsDelegate = new PropertyChangeSupport(this);
		}
	}

}
