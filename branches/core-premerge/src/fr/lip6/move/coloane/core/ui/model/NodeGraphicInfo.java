package fr.lip6.move.coloane.core.ui.model;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

public class NodeGraphicInfo implements INodeGraphicInfo {

	/** Le noeud enrichi */
	private final INodeImpl nodeAdapter;

	/** Les coordonees */
	private int x;
	private int y;

	private long lastMove;

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
		return new Dimension(getWidth(), getHeight());
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
}
