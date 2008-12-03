package fr.lip6.move.coloane.core.model;

import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.model.INodeGraphicInfo;

import java.util.logging.Logger;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;

/**
 * Description graphique d'un noeud
 */
public class NodeGraphicInfo implements INodeGraphicInfo {
	/** Logger 'fr.lip6.move.coloane.core'. */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** Le noeud enrichi */
	private final NodeModel node;

	/** Les coordonees */
	private int x;
	private int y;

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

	/** {@inheritDoc} */
	public final Point getLocation() {
		return new Point(this.x, this.y);
	}

	/** {@inheritDoc} */
	public final void setLocation(Point location) {
		LOGGER.finest("setLocation(" + location.x + ", " + location.y + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		Point oldLocation = new Point(this.x, this.y);
		this.x = location.x;
		this.y = location.y;

		// Lever un evenement
		node.firePropertyChange(LOCATION_PROP, oldLocation, location.getCopy());
	}

	/**
	 * @return la largeur du noeud en tenant compte du zoom
	 */
	private int getWidth() {
		return (this.node.getNodeFormalism().getGraphicalDescription().getWidth() * scale) / 100;
	}

	/**
	 * @return la hauteur du noeud en tenant compte du zoom
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

	/** {@inheritDoc} */
	public final boolean isFilled() {
		return this.node.getNodeFormalism().getGraphicalDescription().isFilled();
	}

	/** {@inheritDoc} */
	public final Color getBackground() {
		return background;
	}

	/** {@inheritDoc} */
	public final void setBackground(Color background) {
		LOGGER.finest("setBackground(" + background + ")"); //$NON-NLS-1$ //$NON-NLS-2$
		Color oldValue = this.background;
		this.background = background;
		node.firePropertyChange(INode.BACKGROUND_COLOR_PROP, oldValue, background);
	}

	/** {@inheritDoc} */
	public final Color getForeground() {
		return foreground;
	}

	/** {@inheritDoc} */
	public final void setForeground(Color foreground) {
		LOGGER.finest("setForeground(" + foreground + ")"); //$NON-NLS-1$ //$NON-NLS-2$
		Color oldValue = this.foreground;
		this.foreground = foreground;
		node.firePropertyChange(INode.FOREGROUND_COLOR_PROP, oldValue, foreground);
	}

	/** {@inheritDoc} */
	public final void setScale(int scale) {
		LOGGER.finest("setScale(" + scale + ")"); //$NON-NLS-1$ //$NON-NLS-2$
		Dimension oldSize = new Dimension();
		oldSize.height = getHeight();
		oldSize.width = getWidth();

		this.scale = scale;
		Dimension newSize = new Dimension();
		newSize.height = getHeight();
		newSize.width = getWidth();
		node.firePropertyChange(INode.RESIZE_PROP, oldSize, newSize);
	}

	/** {@inheritDoc} */
	public final int getScale() {
		return scale;
	}

	/** {@inheritDoc} */
	public final void setSize(Dimension newDimension) {
		return;
	}
}
