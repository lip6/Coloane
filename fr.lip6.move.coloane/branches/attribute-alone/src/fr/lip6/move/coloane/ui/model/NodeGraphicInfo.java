package fr.lip6.move.coloane.ui.model;

import java.io.Serializable;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;


public class NodeGraphicInfo implements INodeGraphicInfo, Serializable {

	/** Id pour la serialisation */
	private static final long serialVersionUID = 1L;
	
	/** Le noeud enrichi */
	private final INodeImpl nodeAdapter;
	
	/** Les coordonees */
	private int x;
	private int y;

	/** 
	 * Constructeur
	 * @param node Le noeud enrichi
	 */
	public NodeGraphicInfo(INodeImpl node) {
		this.nodeAdapter = node;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.INodeGRaphicInfo#getLocation()
	 */
	public Point getLocation() {
		return new Point(this.x,this.y);
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.INodeGRaphicInfo#setLocation(int, int)
	 */
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
		
		// Mise a jour du noeud generique
		this.nodeAdapter.getGenericNode().setPosition(x, y);
		
		// Lever un evenement
		((NodeImplAdapter)this.nodeAdapter).firePropertyChange(NodeImplAdapter.LOCATION_PROP,null,new Point(x, y));				
	}

	/**
	 * Retourne la largeur du noeud telle que prevue par le formalisme
	 * @return int La largeur
	 */
	private int getWidth() {
		return this.nodeAdapter.getElementBase().getWidth();
	}

	/**
	 * Retourne la hauteur du noeud telle que prevue par le formalisme
	 * @return int La hauteur
	 */
	private int getHeight() {
		return this.nodeAdapter.getElementBase().getHeight();
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.INodeGRaphicInfo#getSize()
	 */
	public Dimension getSize() {
		return new Dimension(getWidth(), getHeight());
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.INodeGRaphicInfo#getFigureStyle()
	 */
	public int getFigureStyle() {
		return this.nodeAdapter.getElementBase().getNumFigure();
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.INodeGRaphicInfo#isFilled()
	 */
	public boolean isFilled() {
		return this.nodeAdapter.getElementBase().getIsFilled();
	}
}
