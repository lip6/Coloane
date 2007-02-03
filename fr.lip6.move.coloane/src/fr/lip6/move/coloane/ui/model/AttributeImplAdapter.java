package fr.lip6.move.coloane.ui.model;


import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import fr.lip6.move.coloane.interfaces.models.IAttributeImpl;
import fr.lip6.move.coloane.model.*;

/**
 * C'est ici que le veritable attribut est cree.<br>
 * Cet attribut est l'attribut generique plus quelques proprietes (IAttribute)<br>
 * Les proprietes du PropertiesView sont gerees ici !
 * @see IAttributeImpl
 */
public class AttributeImplAdapter extends AbstractModelElement implements IAttributeImpl {

	/** Id pour la serialisation */
	private static final long serialVersionUID = 1L;
	
	/** Attribut generique a adapter */
	private Attribute attribute;

	/** Un identifiant unique pour etre gere par Ecplipse */
	private String uniqueId;

	/** Un generateur d'id unique a incrementer pour les attributs */
	private static double nbAttribute = 0;

	/** Indique si l'attribut est afficheable dans le graphe */
	private boolean drawable;

	/**
	 * Constructeur<br>
	 * Creation d'un adapter a partir d'un attribut generique
	 * @param attribute attribut a adapter
	 * @param drawable indique si l'atrribut est afficheable, a determiner en fonction du formalism
	 */
	public AttributeImplAdapter(Attribute attribute, boolean drawable) {
		super();
		this.attribute = attribute;
		AttributeImplAdapter.nbAttribute++;
		this.uniqueId = (new Double(AttributeImplAdapter.nbAttribute)).toString();
		this.drawable = drawable;
	}

	/**
	 * Constructeur<br>
	 * Creation d'un attribut adapte
	 * @param displayName Le nom affiche dans la fenetre PropertiesView d'Eclipse
	 * @param defaultValue La valeur par defaut
	 * @param idParent L'element auquel est rattache l'attribut
	 * @param drawable Indique si l'attribut est affichable sur le graphe
	 */
	public AttributeImplAdapter(String displayName, String defaultValue, int idParent, boolean drawable) {
		AttributeImplAdapter.nbAttribute++;
		this.uniqueId = (new Double(AttributeImplAdapter.nbAttribute)).toString();
		this.drawable = drawable;
		this.attribute = new Attribute(displayName, new String[]{defaultValue == null ? "" : defaultValue},idParent);
	}

   
    
	/**
	 * Retourne la chaine qui sera affichee sur la fenetre PropertiesView d'Eclipse
	 * @return String
	 */
	public String getDisplayName() {
		return this.attribute.getName();
	}

	/**
	 * Retourne la valeur de l'attribut
	 * @return Object
	 */
	public Object getValue() {
		return this.attribute.getValue();
	}

	/**
	 * Inidique la nouvelle valeur pour l'attribut generique
	 * @param value La nouvelle valeur
	 */
	public void setValue(String value) {
		this.attribute.setValue((value==null)?"":value);		
	}
	
	/**
	 * TODO: Documenter
	 */
	public boolean getValidation() {
		return false;
	}

	/**
	 * TODO: Documenter
	 */
	public String getValidationMessage() {
		return null;
	}

	/**
	 * L'attribut est-il affichable sur le graphe
	 * @return boolean
	 */
	public boolean isDrawable() {
		return this.drawable;
	}

	
	/*****************************************************************
	 * 
	 * Methodes de  IPropertyDescriptor
	 * 
	 **************************************************************/

	/**
	 * Retourne l'identifiant de l'attribut
	 * @return Object
	 */
	public Object getId() {
		return this.uniqueId;
	}

	/**
	 * TODO: Documenter
	 */
	public CellEditor createPropertyEditor(Composite parent) {
		return null;
	}

	/**
	 * TODO: Documenter
	 */
	public String getCategory() {
		return null;
	}

	/**
	 * 
	 */
	public String getDescription() {
		return this.attribute.getName();
	}

	/**
	 * 
	 */
	public String[] getFilterFlags() {
		return null;
	}

	/**
	 * 
	 */
	public Object getHelpContextIds() {
		return null;
	}

	/**
	 * 
	 */
	public ILabelProvider getLabelProvider() {
		return null;
	}

	/**
	 * 
	 */
	public boolean isCompatibleWith(IPropertyDescriptor anotherProperty) {
		return false;
	}

    /**
	 * Retourne l'attribut generique
	 * @return Attribute
	 * @see Attribute
	 */
	public Attribute getGenericAttribute() {
		return attribute;
	}

    /**
     * Associe l'attribut avec cet objet
	 * @param attribute Attribut a affecter
	 */
	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

    /**
	 * @param drawable The drawable to set.
	 */
	public void setDrawable(boolean drawable) {
		this.drawable = drawable;
	}
	
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return super.getPropertyDescriptors();
	}
}
