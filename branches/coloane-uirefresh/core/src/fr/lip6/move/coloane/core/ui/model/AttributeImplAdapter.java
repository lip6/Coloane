package fr.lip6.move.coloane.core.ui.model;

import fr.lip6.move.coloane.core.motor.formalism.AttributeFormalism;
import fr.lip6.move.coloane.interfaces.model.IAttribute;

import java.util.List;

/**
 * C'est ici que le veritable attribut est cree.<br>
 * Cet attribut est l'attribut generique plus quelques proprietes (IAttribute)<br>
 * @see IAttributeImpl
 */
public class AttributeImplAdapter extends AbstractModelElement implements IAttributeImpl, IElement {

	/** Attribut generique a adapter */
	private IAttribute attribute;

	/** Considerations graphiques */
	private IAttributeGraphicInfo attGraphicInfo;

	/** Element (Noeud) de rattachement */
	private IElement reference;

	/** Un identifiant unique pour etre gere par Ecplipse */
	private int id;

	/** Indique si l'attribut est afficheable dans la fenetre propriete */
	private boolean drawable;

	/** Indique si l'attribut est multiligne */
	private boolean multiline;

	/** Indique le type d'affichage a considerer pour cet attribut */
	private int type;

	/** Indique la valeur par defaut de l'attribut */
	private String defaultValue;

	/**
	 * Constructeur<br>
	 * Creation d'un adapter a partir d'un attribut generique
	 * @param attribute attribut a adapter
	 * @param drawable indique si l'atrribut est afficheable, a determiner en fonction du formalism
	 */
	public AttributeImplAdapter(IAttribute a, AttributeFormalism f, IElement r) {
		super();

		// On attache l'attribut generique
		this.attribute = a;

		// On attache le referent
		this.reference = r;

		// Affectation de l'identifiant
		this.id = f.getOrder();

		// Divers parametres
		this.drawable = f.isDrawable();			// L'attribut doit-il etre affiche dans la fenetre des proprietes
		this.multiline = f.isMultiLines();		// L'attribut est-il multiligne ?
		this.defaultValue = f.getDefaultValue();
		this.type = f.getType();

		// L'objet contenant toutes les consideration graphiques (comme la position)
		this.attGraphicInfo = new AttributeGraphicInfo(this);
	}


	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IAttributeImpl#getId()
	 */
	public final int getId() {
		return this.id;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IElement#getAttributes()
	 */
	public final List<IElement> getAttributes() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IAttributeImpl#getGenericAttribute()
	 */
	public final IAttribute getGenericAttribute() {
		return this.attribute;
	}


	public final IAttributeGraphicInfo getGraphicInfo() {
		return this.attGraphicInfo;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IAttributeImpl#getDisplayName()
	 */
	public final String getDisplayName() {
		return this.attribute.getName();
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IAttributeImpl#getValue()
	 */
	public final String getValue() {
		return this.attribute.getValue();
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IAttributeImpl#setValue(java.lang.String)
	 */
	public final void setValue(String oldValue, String newValue) {

		// Affectation de la nouvelle valeur au modele
		if (newValue == null) {
			this.attribute.setValue(""); //$NON-NLS-1$
		} else {
			this.attribute.setValue(newValue);
		}

		// Si l'attribut est affichable ET que son ancienne valeur etait null ... On doit cr�er une figure
		if (this.isDrawable() && oldValue.equals(this.getDefaultValue())) {
			this.reference.getModelAdapter().annouceAttribute();
		}

		if (this.isDrawable() && newValue.equals(this.getDefaultValue())) {
			this.reference.getModelAdapter().annouceAttribute();
		}

		firePropertyChange(AttributeImplAdapter.VALUE_PROP, null, null);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IAttributeImpl#isDrawable()
	 */
	public final boolean isDrawable() {
		return this.drawable;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IAttributeImpl#isMultiline()
	 */
	public final boolean isMultiline() {
		return this.multiline;
	}

	public final int getType() {
		return this.type;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IAttributeImpl#getDefaultValue()
	 */
	public final String getDefaultValue() {
		return this.defaultValue;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IAttributeImpl#getValidation()
	 */
	public final boolean getValidation() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IAttributeImpl#getValidationMessage()
	 */
	public final String getValidationMessage() {
		return null;
	}


	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IElement#getModelAdapter()
	 */
	public final IModelImpl getModelAdapter() {
		return this.reference.getModelAdapter();
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IAttributeImpl#getReference()
	 */
	public final IElement getReference() {
		return this.reference;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IAttributeImpl#setSelect(boolean, boolean)
	 */
	public final void setSelect(boolean light, boolean state) {
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
	public final void setSelect(boolean state) {
		if (state) {
			firePropertyChange(AttributeImplAdapter.SELECT_HEAVY_PROP, null, null);
		} else {
			firePropertyChange(AttributeImplAdapter.UNSELECT_HEAVY_PROP, null, null);
		}
	}
}
