package fr.lip6.move.coloane.core.model;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.model.INodeGraphicInfo;

import java.util.HashMap;
import java.util.Map;
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

	private static final Long ZERO = new Long(0);

	/** Le noeud enrichi */
	private final NodeModel node;

	/** Les coordonees */
	private int x;
	private int y;

	private Map<IArc, Long> lastMove = new HashMap<IArc, Long>();

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

	/**
	 * Positionne le noeud en tenant compte des points d'inflexion
	 * @param xPos La position en abcisse
	 * @param yPos La position en ordonnée
	 */
	private void setLocation(int xPos, int yPos) {
		LOGGER.finest("setLocation(" + xPos + ", " + yPos + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

	/** {@inheritDoc} */
	public final void setLocation(Point location) {
		Dimension delta = location.getDifference(getLocation());
		setLocation(location.x, location.y);
		node.updateAttributesPosition(delta.width, delta.height);
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
	public final Long getLastMove(IArc key) {
		Long time = lastMove.get(key);
		if (time == null) {
			return ZERO;
		}
		return time;
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
}
