package fr.lip6.move.coloane.core.model;

import fr.lip6.move.coloane.core.ui.rulers.EditorGuide;
import fr.lip6.move.coloane.interfaces.formalism.IAttributeFormalism;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IAttributeGraphicInfo;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.core.ICoreAttribute;
import fr.lip6.move.coloane.interfaces.model.core.ICoreLocationInfo;

public class AttributeModel extends AbstractPropertyChange implements ICoreAttribute, ILocatedElement {
	private IElement reference;
	private IAttributeFormalism attributFormalism;

	private final String name;
	private String value;

	private IAttributeGraphicInfo graphicInfo = new AttributeGraphicInfo(this);

	AttributeModel(IElement reference, IAttributeFormalism attributeFormalism) {
		this.reference = reference;
		this.attributFormalism = attributeFormalism;
		this.name = attributeFormalism.getName();
		this.value = attributeFormalism.getDefaultValue();
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IAttribute#getName()
	 */
	public final String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IAttribute#getValue()
	 */
	public final String getValue() {
		return value;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IAttribute#setValue(java.lang.String)
	 */
	public final void setValue(String value) {
		String oldValue = this.value;
		this.value = value;
		firePropertyChange(IAttribute.VALUE_PROP, oldValue, value);
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IAttribute#getGraphicInfo()
	 */
	public final IAttributeGraphicInfo getGraphicInfo() {
		return graphicInfo;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IAttribute#getReference()
	 */
	public final IElement getReference() {
		return reference;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.core.ICoreAttribute#getAttributeFormalism()
	 */
	public final IAttributeFormalism getAttributeFormalism() {
		return attributFormalism;
	}


	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.model.ILocatedElement#getHorizontalGuide()
	 */
	public EditorGuide getHorizontalGuide() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.model.ILocatedElement#getLocationInfo()
	 */
	public ICoreLocationInfo getLocationInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.model.ILocatedElement#getVerticalGuide()
	 */
	public EditorGuide getVerticalGuide() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.model.ILocatedElement#setHorizontalGuide(fr.lip6.move.coloane.core.ui.rulers.EditorGuide)
	 */
	public void setHorizontalGuide(EditorGuide guide) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.model.ILocatedElement#setVerticalGuide(fr.lip6.move.coloane.core.ui.rulers.EditorGuide)
	 */
	public void setVerticalGuide(EditorGuide guide) {
		// TODO Auto-generated method stub
		
	}
}
