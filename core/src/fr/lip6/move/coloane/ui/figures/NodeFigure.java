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
import org.eclipse.swt.graphics.Color;

public class NodeFigure extends Figure implements INodeFigure {

	/** La figure en Draw2D */
	private IFigure figure;

	/** Les considerations graphiques du noeud */
	private INodeGraphicInfo nodeGraphInfo;

	/** L'epaisseur des lignes lors de la selection */
	private static final int LINE_WIDTH = 3;

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
			figure.setSize(node.getElementBase().getWidth(), node.getElementBase().getHeight());

			if (nodeGraphInfo.isFilled()) {
				figure.setBackgroundColor(ColorConstants.black);
			}
			add(figure);

		// Le cas d'un etat initial
		} else if (nodeGraphInfo.getFigureStyle() == INodeGraphicInfo.FIG_DBLCIRCLE) {
			figure = new Ellipse();
			figure.setForegroundColor(ColorConstants.black);
			figure.setSize(node.getElementBase().getWidth(), node.getElementBase().getHeight());

			// Le cercle interieur
			IFigure figure2 = new Ellipse();
			figure2.setForegroundColor(ColorConstants.black);
			figure2.setSize(node.getElementBase().getWidth() - INodeGraphicInfo.DIFF_CIRCLE, node.getElementBase().getHeight() - INodeGraphicInfo.DIFF_CIRCLE);
			figure2.setLocation(new Point(2, 2));
			figure.add(figure2);
			add(figure);

		// Le cas d'une queue
		} else if (nodeGraphInfo.getFigureStyle() == INodeGraphicInfo.FIG_QUEUE) {
			figure = new RoundedRectangle();
			figure.setSize(node.getElementBase().getWidth(), node.getElementBase().getHeight());
			figure.setForegroundColor(ColorConstants.black);
			add(figure);

		// Le reste des cas (transition)
		} else if (nodeGraphInfo.getFigureStyle() == INodeGraphicInfo.FIG_RECT) {
			figure = new RectangleFigure();
			figure.setSize(node.getElementBase().getWidth(), node.getElementBase().getHeight());
			figure.setForegroundColor(ColorConstants.black);

			if (nodeGraphInfo.isFilled()) {
				figure.setBackgroundColor(ColorConstants.black);
			}
			add(figure);
		}

		// Ecoute des evenements ENTER et EXIT de la souris
		MouseMotionListener listener = new MouseMotionListener.Stub() {

			/** Sauvegarde de la couleur de fond */
			private Color previousBackgroundColor;

			/*
			 * (non-Javadoc)
			 * @see org.eclipse.draw2d.MouseMotionListener$Stub#mouseEntered(org.eclipse.draw2d.MouseEvent)
			 */
			public void mouseEntered(MouseEvent me) {
				previousBackgroundColor = ((Shape) me.getSource()).getBackgroundColor();
				((Shape) me.getSource()).setBackgroundColor(ColorConstants.yellow);
				node.setAttributesSelected(true, true);
			}
			/*
			 * (non-Javadoc)
			 * @see org.eclipse.draw2d.MouseMotionListener$Stub#mouseExited(org.eclipse.draw2d.MouseEvent)
			 */
			public void mouseExited(MouseEvent me) {
				((Shape) me.getSource()).setBackgroundColor(previousBackgroundColor);
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
		((Shape) figure).setLineWidth(LINE_WIDTH);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.figures.INodeFigure#setSelectSpecial()
	 */
	public final void setSelectSpecial() {
		figure.setForegroundColor(ColorConstants.red);
		((Shape) figure).setLineWidth(LINE_WIDTH);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.figures.INodeFigure#setHighlight()
	 */
	public final void setHighlight() {
		figure.setBackgroundColor(ColorConstants.lightGreen);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.views.INodeFigure#setUnselect()
	 */
	public final void setUnselect() {
		figure.setForegroundColor(ColorConstants.black);
		figure.setBackgroundColor(ColorConstants.white);

		if (nodeGraphInfo.isFilled()) {
			figure.setBackgroundColor(ColorConstants.black);
		}

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
