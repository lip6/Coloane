package fr.lip6.move.coloane.ui.figures;

import fr.lip6.move.coloane.ui.model.AbstractModelElement;
import fr.lip6.move.coloane.ui.model.INodeGraphicInfo;
import fr.lip6.move.coloane.ui.model.INodeImpl;
import fr.lip6.move.coloane.ui.model.NodeImplAdapter;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Point;

public class NodeFigure extends Figure implements INodeFigure {

	/** La figure en Draw2D */
	private IFigure figure;

	/** Les considerations graphiques du noeud */
	private INodeGraphicInfo nodeGraphInfo;

	/**
	 * Constructeur de l'objet graphique representant un noeud augemente.
	 * Toute modification graphique concernant le noeud augmente passe par cet objet.
	 * @param element
	 */
	public NodeFigure(AbstractModelElement element) {
		if (element instanceof NodeImplAdapter) {
			createNodeFigure((INodeImpl) element);
		}
	}

	/**
	 * Creation de la figure associee a un noeud
	 * @param node Le modele enrichi du noeud
	 */
	private void createNodeFigure(final INodeImpl node) {

		// Recupere les options graphiques definies pour le formalisme
		nodeGraphInfo = node.getGraphicInfo();

		// Le cas d'un place ou d'un etat simple
		if (nodeGraphInfo.getFigureStyle() == INodeGraphicInfo.FIG_CIRCLE) {
			figure = new Ellipse();
			figure.setForegroundColor(ColorConstants.black);
			figure.setSize(16, 16);

			if (nodeGraphInfo.isFilled()) {
				figure.setBackgroundColor(ColorConstants.black);
			}
			add(figure);

		// Le cas d'un etat initial
		} else if (nodeGraphInfo.getFigureStyle() == INodeGraphicInfo.FIG_DBLCIRCLE) {
			figure = new Ellipse();
			figure.setForegroundColor(ColorConstants.black);
			figure.setSize(16, 16);

			// Le cercle interieur
			IFigure figure2 = new Ellipse();
			figure2.setForegroundColor(ColorConstants.black);
			figure2.setSize(12, 12);
			figure2.setLocation(new Point(2, 2));
			figure.add(figure2);

			add(figure);

		// Le cas d'une queue
		} else if (nodeGraphInfo.getFigureStyle() == INodeGraphicInfo.FIG_QUEUE) {
			figure = new RoundedRectangle();
			figure.setSize(16, 8);
			figure.setForegroundColor(ColorConstants.black);
			add(figure);

		// Le reste des cas (transition)
		} else {
			figure = new RectangleFigure();
			figure.setSize(24, 8);
			figure.setForegroundColor(ColorConstants.black);

			if (nodeGraphInfo.isFilled()) {
				figure.setBackgroundColor(ColorConstants.black);
			}
			add(figure);
		}

		// Ecoute des evenements ENTER et EXIT de la souris
		MouseMotionListener listener = new MouseMotionListener.Stub() {
			public void mouseEntered(MouseEvent me) {
				((Shape) me.getSource()).setBackgroundColor(ColorConstants.yellow);
				node.setAttributesSelected(true, true);
			}
			public void mouseExited(MouseEvent me) {
				((Shape) me.getSource()).setBackgroundColor(ColorConstants.white);
				node.setAttributesSelected(true, false);
			}
		};

		figure.addMouseMotionListener(listener);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.views.INodeFigure#setSelect()
	 */
	public final void setSelect() {
		figure.setForegroundColor(ColorConstants.blue);
		((Shape) figure).setLineWidth(3);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.figures.INodeFigure#setSelectSpecial()
	 */
	public final void setSelectSpecial() {
		figure.setForegroundColor(ColorConstants.red);
		((Shape) figure).setLineWidth(3);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.views.INodeFigure#setUnselect()
	 */
	public final void setUnselect() {
		figure.setForegroundColor(ColorConstants.black);
		((Shape) figure).setLineWidth(1);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.figures.INodeFigure#unsetSelectSpecial()
	 */
	public final void unsetSelectSpecial() {
		this.setUnselect();
	}
}
