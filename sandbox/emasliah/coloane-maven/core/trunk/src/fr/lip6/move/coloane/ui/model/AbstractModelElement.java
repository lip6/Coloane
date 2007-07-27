package fr.lip6.move.coloane.ui.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import fr.lip6.move.coloane.ui.AttributePropertyDescriptor;


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
	 * TODO : A documenter
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
	protected IAttributeImpl[] propsList;

	/**
	 * Table des attributs en fonction de leurs identifiant
	 * A surcharger et a implementer dans le constructeur.
	 */
	protected Hashtable<Object,IAttributeImpl> properties = new Hashtable<Object,IAttributeImpl>();

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

		// Preparation de la liste des descripteurs
		IPropertyDescriptor[] liste = new IPropertyDescriptor[this.properties.size()];
		
		// Recupere la table contenant toutes les proprietes (attributs) des objets
		for (Enumeration e = this.properties.elements(); e.hasMoreElements();) {
			IAttributeImpl prop = (IAttributeImpl) e.nextElement();
			
			// TODO : Verifier qu'il n'existe pas de solution plus elegante sans passer par une HashMap
			// Calcul de l'indice d'insertion dans la fenetre
			int indice = Integer.parseInt(prop.getId());
			
			// Selection du descripteur selon le type d'attribut
			if (prop.isMultiline()) { // Multiligne
				liste[indice-1] = new AttributePropertyDescriptor(prop.getId(), prop.getDisplayName(), "", prop.getValue());
			} else { // Normal
				liste[indice-1] = new TextPropertyDescriptor(prop.getId(), prop.getDisplayName());
			}
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
		IAttributeImpl prop = (IAttributeImpl) this.properties.get(id);
		
		// Si l'attribut a une veritable valeur
		if (prop.getValue() != null) {
			
			// Si l'attribut est multiligne, on ne prend que la premiere ligne
			if (prop.isMultiline() && (!prop.getValue().equalsIgnoreCase(""))) {
				return (prop.getValue().split("\r"))[0]+" ...";
			// Sinon on retourne la valeur normale
			} else {
				return (String) prop.getValue();
			}
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
	public void setPropertyValue(Object id, Object newValue) {
		IAttributeImpl attribute = (IAttributeImpl) this.properties.get(id);
		
		// Sauvegarde de l'ancienne valeur
		String oldValue = attribute.getValue();
		
		// Nouvelle valeur pour l'attribut
		attribute.setValue(oldValue,(String)newValue);
	}

	/**
	 * Methode qui indique si il ya eu un changement par rapport a une valeur par defaut
	 * Les classes filles doivent surcharger cette methode. 
	 * Dans cette implementation par defaut elle retourne false.
	 * 
	 * @param id Nom de la propriete
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
	 * Attache un listener (ecouteur) a l'objet
	 * L'objet est donc maintenant sensible aux evenements recus
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
	public IAttributeImpl[] getPropertyList() {
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
