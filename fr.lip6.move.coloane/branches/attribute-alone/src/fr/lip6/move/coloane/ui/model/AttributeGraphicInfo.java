package fr.lip6.move.coloane.ui.model;

import java.io.Serializable;

import org.eclipse.draw2d.geometry.Point;

public class AttributeGraphicInfo implements IAttributeGraphicInfo, Serializable {

	/** Id pour la serialisation */
	private static final long serialVersionUID = 1L;
	
	/** Le noeud enrichi */
	private final IAttributeImpl attributeAdapter;
	
	/** Les coordonees */
	private int x;
	private int y;

	/** 
	 * Constructeur
	 * @param attributeImpl L'attribut enrichi
	 */
	public AttributeGraphicInfo(IAttributeImpl attributeAdapter) {
		this.attributeAdapter = attributeAdapter;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IAttributeGraphicInfo#getLocation()
	 */
	public Point getLocation() {
		return new Point(this.x,this.y);
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IAttributeGraphicInfo#setLocation(int, int)
	 */
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
		
		// Mise a jour du noeud generique
		this.attributeAdapter.getGenericAttribute().setPosition(x, y);
		
		// Lever un evenement
		((AttributeImplAdapter)this.attributeAdapter).firePropertyChange(NodeImplAdapter.LOCATION_PROP,null,new Point(x, y));				
	}
}

