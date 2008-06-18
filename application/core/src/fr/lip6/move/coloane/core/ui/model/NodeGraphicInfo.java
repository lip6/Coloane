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
	public NodeGraphicInfo(INodeImpl node) {
		this.nodeAdapter = node;
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

		// Mise a jour du noeud generique
		this.nodeAdapter.getGenericNode().setPosition(x, y);

		// Mise a jour de la date de dernier mouvement
		this.lastMove = System.currentTimeMillis();

		for (IArcImpl arc : nodeAdapter.getSourceArcs()) {
			if (Math.abs(arc.getTarget().getGraphicInfo().getLastMove() - lastMove) < 256) {
				arc.modifyInflexPoints(dx, dy);
			}
		}
		for (IArcImpl arc : nodeAdapter.getTargetArcs()) {
			if (Math.abs(arc.getSource().getGraphicInfo().getLastMove() - lastMove) < 256) {
				arc.modifyInflexPoints(dx, dy);
			}
		}

		// Lever un evenement
		((NodeImplAdapter) this.nodeAdapter).firePropertyChange(NodeImplAdapter.LOCATION_PROP, null, new Point(x, y));
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.INodeGraphicInfo#getWidth()
	 */
	public final int getWidth() {
		return (this.nodeAdapter.getElementBase().getWidth() * scale) / 100;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.INodeGraphicInfo#getHeight()
	 */
	public final int getHeight() {
		return (this.nodeAdapter.getElementBase().getHeight() * scale) / 100;
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
		((NodeImplAdapter) this.nodeAdapter).firePropertyChange(INodeImpl.BACKGROUND_COLOR_PROP, oldValue, this.background);
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
	public final void setScale(int scale) {
		Dimension oldSize = new Dimension();
		oldSize.height = (nodeAdapter.getElementBase().getHeight() * this.scale) / 100;
		oldSize.width = (nodeAdapter.getElementBase().getWidth() * this.scale) / 100;
		this.scale = scale;
		Dimension newSize = new Dimension();
		newSize.height = (nodeAdapter.getElementBase().getHeight() * this.scale) / 100;
		newSize.width = (nodeAdapter.getElementBase().getWidth() * this.scale) / 100;
		try {
			((NodeImplAdapter) this.nodeAdapter).firePropertyChange(INodeImpl.RESIZE_PROP, oldSize, newSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.INodeGraphicInfo#getZoom()
	 */
	public final int getScale() {
		return scale;
	}
}
