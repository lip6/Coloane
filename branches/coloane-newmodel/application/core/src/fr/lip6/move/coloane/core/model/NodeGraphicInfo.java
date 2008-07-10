package fr.lip6.move.coloane.core.model;

import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.model.INodeGraphicInfo;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;

public class NodeGraphicInfo implements INodeGraphicInfo {

	/** Le noeud enrichi */
	private final NodeModel node;

	/** Les coordonees */
	private int x;
	private int y;

	private long lastMove;

	/** Taille */
	private int scale = 100;

	/** Couleurs du noeud */
	private Color foreground = ColorConstants.black;
	private Color background = ColorConstants.white;

	/**
	 * Constructeur
	 * @param node Le noeud enrichi
	 */
	public NodeGraphicInfo(INode node) {
		this.node = (NodeModel) node;
		if (isFilled()) {
			background = ColorConstants.black;
		}
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
		int dx = xPos - this.x;
		int dy = yPos - this.y;
		this.x = xPos;
		this.y = yPos;

		// Mise a jour de la date de dernier mouvement
		this.lastMove = System.currentTimeMillis();

		// Déplacement des points d'inflexion si la différence de temps entre le déplacement
		// des 2 noeuds d'un arc est inférieur à 256 ms.
		for (IArc arc : node.getOutcomingArcs()) {
			if (Math.abs(arc.getTarget().getGraphicInfo().getLastMove() - lastMove) < 256) {
				arc.modifyInflexPoints(dx, dy);
			}
		}
		for (IArc arc : node.getIncomingArcs()) {
			if (Math.abs(arc.getSource().getGraphicInfo().getLastMove() - lastMove) < 256) {
				arc.modifyInflexPoints(dx, dy);
			}
		}

		// Lever un evenement
		node.firePropertyChange(INode.LOCATION_PROP, null, new Point(x, y));
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.INodeGraphicInfo#setLocation(org.eclipse.draw2d.geometry.Point)
	 */
	public final void setLocation(Point location) {
		setLocation(location.x, location.y);
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.INodeGraphicInfo#getWidth()
	 */
	public final int getWidth() {
		return (this.node.getNodeFormalism().getGraphicalDescription().getWidth() * scale) / 100;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.INodeGraphicInfo#getHeight()
	 */
	public final int getHeight() {
		return (this.node.getNodeFormalism().getGraphicalDescription().getHeight() * scale) / 100;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.INodeGRaphicInfo#getSize()
	 */
	public final Dimension getSize() {
		return new Dimension(
				getWidth(),
				getHeight()
		);
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.INodeGRaphicInfo#isFilled()
	 */
	public final boolean isFilled() {
		return this.node.getNodeFormalism().getGraphicalDescription().isFilled();
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.INodeGraphicInfo#getLastMove()
	 */
	public final long getLastMove() {
		return lastMove;
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
		node.firePropertyChange(INode.BACKGROUND_COLOR_PROP, oldValue, background);
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
		node.firePropertyChange(INode.FOREGROUND_COLOR_PROP, oldValue, foreground);
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.INodeGraphicInfo#setZoom(int)
	 */
	public final void setScale(int scale) {
		Dimension oldSize = new Dimension();
		oldSize.height = getHeight();
		oldSize.width = getWidth();

		this.scale = scale;
		Dimension newSize = new Dimension();
		newSize.height = getHeight();
		newSize.width = getWidth();
		node.firePropertyChange(INode.RESIZE_PROP, oldSize, newSize);
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.INodeGraphicInfo#getZoom()
	 */
	public final int getScale() {
		return scale;
	}
}
