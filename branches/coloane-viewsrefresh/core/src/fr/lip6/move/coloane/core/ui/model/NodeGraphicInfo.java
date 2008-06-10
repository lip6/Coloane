package fr.lip6.move.coloane.core.ui.model;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;

public class NodeGraphicInfo implements INodeGraphicInfo {

	/** Le noeud enrichi */
	private final INodeImpl nodeAdapter;

	/** Les coordonees */
	private int x;
	private int y;

	/** Taille */
	private int zoom = 100;

	/** Couleurs du noeud */
	private Color foreground = ColorConstants.black;
	private Color background = ColorConstants.white;

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
	public final Point getLocation() {
		return new Point(this.x, this.y);
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.INodeGRaphicInfo#setLocation(int, int)
	 */
	public final void setLocation(int xPos, int yPos) {
		this.x = xPos;
		this.y = yPos;

		// Mise a jour du noeud generique
		this.nodeAdapter.getGenericNode().setPosition(x, y);

		// Lever un evenement
		((NodeImplAdapter) this.nodeAdapter).firePropertyChange(NodeImplAdapter.LOCATION_PROP, null, new Point(x, y));
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
	public final Dimension getSize() {
		return new Dimension(
				getWidth() * (zoom / 100),
				getHeight() * (zoom / 100)
				);
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.INodeGRaphicInfo#getFigureStyle()
	 */
	public final int getFigureStyle() {
		return this.nodeAdapter.getElementBase().getNumFigure();
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.INodeGRaphicInfo#isFilled()
	 */
	public final boolean isFilled() {
		return this.nodeAdapter.getElementBase().getIsFilled();
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.INodeGraphicInfo#getBackground()
	 */
	public final Color getBackground() {
		return background;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.INodeGraphicInfo#setBackground(org.eclipse.swt.graphics.Color)
	 */
	public final void setBackground(Color background) {
		Color oldValue = this.background;
		this.background = background;
		((NodeImplAdapter) this.nodeAdapter).firePropertyChange(INodeImpl.BACKGROUND_COLOR_PROP, oldValue, background);
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.INodeGraphicInfo#getForeground()
	 */
	public final Color getForeground() {
		return foreground;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.INodeGraphicInfo#setForeground(org.eclipse.swt.graphics.Color)
	 */
	public final void setForeground(Color foreground) {
		Color oldValue = this.foreground;
		this.foreground = foreground;
		((NodeImplAdapter) this.nodeAdapter).firePropertyChange(INodeImpl.FOREGROUND_COLOR_PROP, oldValue, foreground);
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.INodeGraphicInfo#setZoom(int)
	 */
	public final void setZoom(int zoom) {
		this.zoom = zoom;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.INodeGraphicInfo#getZoom()
	 */
	public final int getZoom() {
		return zoom;
	}
}
