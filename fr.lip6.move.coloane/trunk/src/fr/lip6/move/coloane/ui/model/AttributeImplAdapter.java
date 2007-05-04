package fr.lip6.move.coloane.ui.model;

import fr.lip6.move.coloane.interfaces.model.IAttribute;

/**
 * C'est ici que le veritable attribut est cree.<br>
 * Cet attribut est l'attribut generique plus quelques proprietes (IAttribute)<br>
 * @see IAttributeImpl
 */
public class AttributeImplAdapter extends AbstractModelElement implements IAttributeImpl {

	/** Id pour la serialisation */
	private static final long serialVersionUID = 1L;
	
	/** Attribut generique a adapter */
	private IAttribute attribute;

	/** Un identifiant unique pour etre gere par Ecplipse */
	private String id;

	/** Un generateur d'id unique a incrementer pour les attributs */
	private static double nbAttribute = 0;

	/** Indique si l'attribut est afficheable dans la fenetre propriete */
	private boolean drawable;
	
	/** Indique si l'attribut est multiligne */
	private boolean multiline;

	/**
	 * Constructeur<br>
	 * Creation d'un adapter a partir d'un attribut generique
	 * @param attribute attribut a adapter
	 * @param drawable indique si l'atrribut est afficheable, a determiner en fonction du formalism
	 */
	public AttributeImplAdapter(IAttribute attribute, boolean drawable, boolean multiline) {
		super();
		
		// On attache l'attribut generique
		this.attribute = attribute;
		
		// Affectation de l'identifiant
		AttributeImplAdapter.nbAttribute++;
		this.id = (new Double(AttributeImplAdapter.nbAttribute)).toString();
		
		this.drawable = drawable;		// L'attribut doit-il etre affiche dans la fenetre des proprietes
		this.multiline = multiline;		// L'attribut est-il multiligne ?
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
	public void setValue(String value) {
		this.attribute.setValue((value==null)?"":value);		
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

	public boolean getValidation() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getValidationMessage() {
		// TODO Auto-generated method stub
		return null;
	}
}
