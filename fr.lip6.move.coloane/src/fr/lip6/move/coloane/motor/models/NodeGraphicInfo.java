package fr.lip6.move.coloane.motor.models;

import java.io.Serializable;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

import fr.lip6.move.coloane.interfaces.models.INodeGraphicInfo;
import fr.lip6.move.coloane.models.Node;

public class NodeGraphicInfo implements INodeGraphicInfo, Serializable{

	/** Id pour la serialisation */
	private static final long serialVersionUID = 1L;
	
	/** Le noeud enrichi */
	private NodeImplAdapter nodeAdapter;

	/** 
	 * Constructeur
	 * @param node Le noeud enrichi
	 */
	public NodeGraphicInfo(NodeImplAdapter node) {
		super();
		this.nodeAdapter = node;
	}

	/**
	 * Retourne la localisation du noeud
	 * @return Point
	 */
	public Point getLocation() {
		return new Point(this.nodeAdapter.getGenericNode().getXPosition(),this.nodeAdapter.getGenericNode().getYPosition());

	}

	/**
	 * Change l'emplacement d'un noeud
	 * @param newLocation Nouvelle localisation du noeud
	 */
	public void setLocation(Point newLocation) {
		Node node = this.nodeAdapter.getGenericNode();
		node.setPosition(newLocation.x, newLocation.y);
		Point newPosition = new Point(node.getXPosition(), node.getYPosition());
		
		// Lever un evenement
		this.nodeAdapter.firePropertyChange(NodeImplAdapter.LOCATION_PROP,null,newPosition);				
	}

	/**
	 * Retourne la largeur du noeud
	 * @return int
	 */
	public int getWidth() {
		return this.nodeAdapter.getElementBase().getWidth();
	}

	/**
	 * Retourne la hauteur du noeud
	 * @return int
	 */
	public int getHeight() {
		return this.nodeAdapter.getElementBase().getHeight();
	}

	/**
	 * Retourne les dimensions du noeud
	 * @return Dimension 
	 */
	public Dimension getSize() {
		return new Dimension(getWidth(), getHeight());
	}

	/**
	 * TODO: Documenter
	 */
	public void setSize(Dimension newSize) { }

	/**
	 * Retourne le style de dessin de la figure
	 * @return int
	 */
	public int getFigureStyle() {
		return this.nodeAdapter.getElementBase().getNumFigure();
	}

	/**
	 * Indique si le redimensionnement est possible
	 * @return boolean 
	 */
	public boolean isSizable() {
		return false;
	}

	/**
	 * Retourne un booleen indiquant si la figure est remplie ou non.
	 */
	public boolean isFilled() {
		return this.nodeAdapter.getElementBase().getIsFillede();
	}
}
