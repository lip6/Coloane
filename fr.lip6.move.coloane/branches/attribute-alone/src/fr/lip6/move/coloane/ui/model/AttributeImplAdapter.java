package fr.lip6.move.coloane.ui.model;

import java.util.List;

import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.motor.formalism.AttributeFormalism;

/**
 * C'est ici que le veritable attribut est cree.<br>
 * Cet attribut est l'attribut generique plus quelques proprietes (IAttribute)<br>
 * @see IAttributeImpl
 */
public class AttributeImplAdapter extends AbstractModelElement implements IAttributeImpl, IElement {

	/** Id pour la serialisation */
	private static final long serialVersionUID = 1L;
	
	/** Attribut generique a adapter */
	private IAttribute attribute;
	
	/** Considerations graphiques */
	private IAttributeGraphicInfo attGraphicInfo;
	
	/** Element (Noeud) de rattachement */
	private IElement reference;

	/** Un identifiant unique pour etre gere par Ecplipse */
	private String id;

	/** Indique si l'attribut est afficheable dans la fenetre propriete */
	private boolean drawable;
	
	/** Indique si l'attribut est multiligne */
	private boolean multiline;
	
	/** Indique la valeur par defaut de l'attribut */
	private String defaultValue;

	/**
	 * Constructeur<br>
	 * Creation d'un adapter a partir d'un attribut generique
	 * @param attribute attribut a adapter
	 * @param drawable indique si l'atrribut est afficheable, a determiner en fonction du formalism
	 */
	public AttributeImplAdapter(IAttribute attribute, AttributeFormalism formalism, IElement ref) {
		super();
		
		// On attache l'attribut generique
		this.attribute = attribute;
		
		// On attache le referent
		this.reference = ref;
		
		// Affectation de l'identifiant
		this.id = String.valueOf(formalism.getOrder());
		
		// Divers parametres
		this.drawable = formalism.isDrawable();			// L'attribut doit-il etre affiche dans la fenetre des proprietes
		this.multiline = formalism.isMultiLines();		// L'attribut est-il multiligne ?
		this.defaultValue = formalism.getDefaultValue();
		
		// L'objet contenant toutes les consideration graphiques (comme la position)
		this.attGraphicInfo = new AttributeGraphicInfo(this);
	}

	   
	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IAttributeImpl#getId()
	 */
	public String getId() {
		return this.id;
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IElement#getAttributes()
	 */
    public List<IElement> getAttributes() {
    	return null;
    }

    /*
     * (non-Javadoc)
     * @see fr.lip6.move.coloane.ui.model.IAttributeImpl#getGenericAttribute()
     */
    public IAttribute getGenericAttribute() {
    	return this.attribute;
    }
    
    
    public IAttributeGraphicInfo getGraphicInfo() {
    	return this.attGraphicInfo;
    }
   
	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IAttributeImpl#getDisplayName()
	 */
	public String getDisplayName() {
		return this.attribute.getName();
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IAttributeImpl#getValue()
	 */
	public String getValue() {
		return this.attribute.getValue();
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IAttributeImpl#setValue(java.lang.String)
	 */
	public void setValue(String oldValue,String newValue) {
		
		// Affectation de la nouvelle valeur au modele
		this.attribute.setValue((newValue==null)?"":newValue);

		// Si l'attribut est affichable ET que son ancienne valeur etait null ... On doit créer une figure
		if (this.isDrawable() && oldValue.equals("")) {
			this.reference.getModelAdapter().annouceAttribute();
		}
		
		if (this.isDrawable() && newValue.equals("")) {
			this.reference.getModelAdapter().annouceAttribute();
		}
		
		firePropertyChange(AttributeImplAdapter.VALUE_PROP, null, null);
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IAttributeImpl#isDrawable()
	 */
	public boolean isDrawable() {
		return this.drawable;
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IAttributeImpl#isMultiline()
	 */
	public boolean isMultiline() {
		return this.multiline;
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IAttributeImpl#getDefaultValue()
	 */
	public String getDefaultValue() {
		return this.defaultValue;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IAttributeImpl#getValidation()
	 */
	public boolean getValidation() {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IAttributeImpl#getValidationMessage()
	 */
	public String getValidationMessage() {
		// TODO Auto-generated method stub
		return null;
	}


	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IElement#getModelAdapter()
	 */
	public IModelImpl getModelAdapter() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IAttributeImpl#getReference()
	 */
	public IElement getReference() {
		return this.reference;
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IAttributeImpl#setSelect(boolean, boolean)
	 */
	public void setSelect(boolean light, boolean state) {
		if (state) {
			if (light) {
				firePropertyChange(AttributeImplAdapter.SELECT_LIGHT_PROP, null, null);
			} else {
				firePropertyChange(AttributeImplAdapter.SELECT_HEAVY_PROP, null, null);
			}			
		} else {
			if (light) {
				firePropertyChange(AttributeImplAdapter.UNSELECT_LIGHT_PROP, null, null);
			} else {
				firePropertyChange(AttributeImplAdapter.UNSELECT_HEAVY_PROP, null, null);
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IAttributeImpl#setSelect(boolean)
	 */
	public void setSelect(boolean state) {
		if (state) {
			firePropertyChange(AttributeImplAdapter.SELECT_HEAVY_PROP, null, null);
		} else {
			firePropertyChange(AttributeImplAdapter.UNSELECT_HEAVY_PROP, null, null);
		}
	}
}
