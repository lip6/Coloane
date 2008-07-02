package fr.lip6.move.coloane.core.ui.figures;

import fr.lip6.move.coloane.core.ui.dialogs.ColorsPrefs;
import fr.lip6.move.coloane.interfaces.model.AbstractElement;
import fr.lip6.move.coloane.interfaces.model.interfaces.INodeGraphicInfo;
import fr.lip6.move.coloane.interfaces.model.interfaces.INode;

import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;

public class NodeFigure extends Figure implements INodeFigure {

	/** La figure en Draw2D */
	private IFigure figure;

	/** Les considerations graphiques du noeud */
	private INodeGraphicInfo nodeGraphInfo;

	private boolean isSelected;

	/** L'epaisseur des lignes lors de la selection */
	private static final int LINE_WIDTH = 3;

	/**
	 * Constructeur de l'objet graphique representant un noeud augemente.
	 * Toute modification graphique concernant le noeud augmente passe par cet objet.
	 * @param element
	 */
	public NodeFigure(AbstractElement element) {
		if (element instanceof INode) {
			INode node = (INode) element;

			// Recupere les options graphiques definies pour le formalisme
			nodeGraphInfo = node.getGraphicInfo();

			createNodeFigure(node);
		}
	}

	/**
	 * Creation de la figure associee a un noeud
	 * @param node Le modele enrichi du noeud
	 */
	private void createNodeFigure(final INode node) {

		// Le cas d'un place ou d'un etat simple
		if (nodeGraphInfo.getFigureStyle() == INodeGraphicInfo.FIG_CIRCLE) {
			figure = new Ellipse();
			figure.setSize(nodeGraphInfo.getSize());
			figure.setForegroundColor(nodeGraphInfo.getForeground());
			figure.setBackgroundColor(nodeGraphInfo.getBackground());

			add(figure);

		// Le cas d'un etat initial
		} else if (nodeGraphInfo.getFigureStyle() == INodeGraphicInfo.FIG_DBLCIRCLE) {
			figure = new Ellipse();
			figure.setSize(nodeGraphInfo.getSize());
			figure.setForegroundColor(nodeGraphInfo.getForeground());
//			figure.setBackgroundColor(nodeGraphInfo.getBackground());

			// Le cercle interieur
			IFigure figure2 = new Ellipse();
			figure2.setForegroundColor(nodeGraphInfo.getForeground());
			figure2.setBackgroundColor(nodeGraphInfo.getBackground());
			figure2.setSize(nodeGraphInfo.getWidth() - INodeGraphicInfo.DIFF_CIRCLE, nodeGraphInfo.getHeight() - INodeGraphicInfo.DIFF_CIRCLE);
			figure2.setLocation(new Point(2, 2));
			figure.add(figure2);
			add(figure);

		// Le cas d'une queue
		} else if (nodeGraphInfo.getFigureStyle() == INodeGraphicInfo.FIG_QUEUE) {
			figure = new RoundedRectangle();
			figure.setSize(nodeGraphInfo.getSize());
			figure.setForegroundColor(nodeGraphInfo.getForeground());
			figure.setBackgroundColor(nodeGraphInfo.getBackground());
			add(figure);

		// Le reste des cas (transition)
		} else if (nodeGraphInfo.getFigureStyle() == INodeGraphicInfo.FIG_RECT) {
			figure = new RectangleFigure();
			figure.setSize(nodeGraphInfo.getSize());
			figure.setForegroundColor(nodeGraphInfo.getForeground());
			figure.setBackgroundColor(nodeGraphInfo.getBackground());

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
			@SuppressWarnings("unchecked")
			@Override
			public void mouseEntered(MouseEvent me) {
				IFigure currentFigure = (Shape) me.getSource();
				previousBackgroundColor = currentFigure.getBackgroundColor();
				currentFigure.setBackgroundColor(ColorsPrefs.setColor("COLORNODE_MOUSE")); //$NON-NLS-1$
				for (IFigure child : (List<IFigure>) currentFigure.getChildren()) {
					child.setBackgroundColor(ColorsPrefs.setColor("COLORNODE_MOUSE")); //$NON-NLS-1$
				}
				node.setAttributesSelected(true, true);
			}
			/*
			 * (non-Javadoc)
			 * @see org.eclipse.draw2d.MouseMotionListener$Stub#mouseExited(org.eclipse.draw2d.MouseEvent)
			 */
			@SuppressWarnings("unchecked")
			@Override
			public void mouseExited(MouseEvent me) {
				IFigure currentFigure = (Shape) me.getSource();
				currentFigure.setBackgroundColor(previousBackgroundColor);
				for (IFigure child : (List<IFigure>) currentFigure.getChildren()) {
					child.setBackgroundColor(previousBackgroundColor);
				}
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
		//figure.setForegroundColor(ColorConstants.blue);
		figure.setForegroundColor(ColorsPrefs.setColor("COLORNODE")); //$NON-NLS-1$
		((Shape) figure).setLineWidth(LINE_WIDTH);
		isSelected = true;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.figures.INodeFigure#setSelectSpecial()
	 */
	public final void setSelectSpecial() {
		figure.setForegroundColor(ColorConstants.red);
		((Shape) figure).setLineWidth(LINE_WIDTH);
		isSelected = true;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.figures.INodeFigure#setHighlight()
	 */
	public final void setHighlight() {
		figure.setBackgroundColor(ColorsPrefs.setColor("COLORNODE_HIGHLIGHT")); //$NON-NLS-1$
		isSelected = true;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.views.INodeFigure#setUnselect()
	 */
	public final void setUnselect() {
		figure.setForegroundColor(nodeGraphInfo.getForeground());
		if (nodeGraphInfo.isFilled()) {
			figure.setBackgroundColor(ColorConstants.black);
		} else {
			figure.setBackgroundColor(nodeGraphInfo.getBackground());
		}

		((Shape) figure).setLineWidth(1);
		isSelected = false;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.figures.INodeFigure#unsetSelectSpecial()
	 */
	public final void unsetSelectSpecial() {
		this.setUnselect();
	}

	@Override
	public final void setBackgroundColor(Color bg) {
		if (figure.getChildren().isEmpty()) {
			figure.setBackgroundColor(bg);
		}
		for (Object obj : figure.getChildren()) {
			IFigure child = (IFigure) obj;
			child.setBackgroundColor(bg);
		}
		nodeGraphInfo.setBackground(bg);
	}

	@Override
	public final void setForegroundColor(Color fg) {
		if (!isSelected) {
			figure.setForegroundColor(fg);
		}
		nodeGraphInfo.setForeground(fg);
	}

	@Override
	public final void setSize(int w, int h) {
		int dw = w - figure.getSize().width;
		int dh = h - figure.getSize().height;
		super.setSize(w, h);
		figure.setSize(w, h);
		for (Object o : figure.getChildren()) {
			Figure child = (Figure) o;
			Dimension dim = child.getSize();
			child.setSize(dim.width + dw, dim.height + dh);
		}
	}

	@Override
	public final Rectangle getBounds() {
		return figure.getBounds();
	}

	@Override
	public final void paint(Graphics graphics) {
		graphics.setAntialias(SWT.ON);
		super.paint(graphics);
	}
}
