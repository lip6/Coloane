package fr.lip6.move.coloane.core.model;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.model.INodeGraphicInfo;

import java.util.HashMap;

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

	private HashMap<IArc, Long> lastMove = new HashMap<IArc, Long>();
	private static final Long ZERO = new Long(0);

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

	/**
	 * Positionne le noeud en tenant compte des points d'inflexion
	 * @param xPos
	 * @param yPos
	 */
	private void setLocation(int xPos, int yPos) {
		Point oldLocation = new Point(this.x, this.y);
		int dx = xPos - this.x;
		int dy = yPos - this.y;
		this.x = xPos;
		this.y = yPos;

		// Mise a jour de la date de dernier mouvement
		Long currentTime = System.currentTimeMillis();

		// Déplacement des points d'inflexion si la différence de temps entre le déplacement
		// des 2 noeuds d'un arc est inférieur à 256 ms.
		for (IArc arc : node.getOutcomingArcs()) {
			lastMove.put(arc, currentTime);
			if (Math.abs(arc.getTarget().getGraphicInfo().getLastMove(arc) - currentTime) < 256) {
				arc.modifyInflexPoints(dx, dy);
				lastMove.put(arc, ZERO);
			}
		}
		for (IArc arc : node.getIncomingArcs()) {
			lastMove.put(arc, currentTime);
			if (Math.abs(arc.getSource().getGraphicInfo().getLastMove(arc) - currentTime) < 256) {
				arc.modifyInflexPoints(dx, dy);
				lastMove.put(arc, ZERO);
			}
		}
		// Lever un evenement
		node.firePropertyChange(INode.LOCATION_PROP, oldLocation, new Point(x, y));

		// Il faut avertir FrameKit
		Coloane.notifyModelChange(node);
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.INodeGraphicInfo#setLocation(org.eclipse.draw2d.geometry.Point)
	 */
	public final void setLocation(Point location) {
		Dimension delta = location.getDifference(getLocation());
		try {
			setLocation(location.x, location.y);
		} catch (Exception e) {
			e.printStackTrace();
		}
		node.updateAttributesPosition(delta.width, delta.height);
	}

	/**
	 * @return La largeur du noeud en tenant compte du zoom
	 */
	private int getWidth() {
		return (this.node.getNodeFormalism().getGraphicalDescription().getWidth() * scale) / 100;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.INodeGraphicInfo#getHeight()
	 */
	private int getHeight() {
		return (this.node.getNodeFormalism().getGraphicalDescription().getHeight() * scale) / 100;
	}

	/**
	 * @return La hauteur du noeud en tenant compte du zoom
	 */
	public final Dimension getSize() {
		return new Dimension(getWidth(), getHeight());
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
	public final Long getLastMove(IArc key) {
		Long time = lastMove.get(key);
		if (time == null) {
			return ZERO;
		}
		return time;
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

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INodeGraphicInfo#setForeground(org.eclipse.swt.graphics.Color)
	 */
	public final void setForeground(Color foreground) {
		Color oldValue = this.foreground;
		this.foreground = foreground;
		node.firePropertyChange(INode.FOREGROUND_COLOR_PROP, oldValue, foreground);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INodeGraphicInfo#setScale(int)
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

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INodeGraphicInfo#getScale()
	 */
	public final int getScale() {
		return scale;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.ILocationInfo#setSize(org.eclipse.draw2d.geometry.Dimension)
	 */
	public final void setSize(Dimension newDimension) {
		return;
	}
}
