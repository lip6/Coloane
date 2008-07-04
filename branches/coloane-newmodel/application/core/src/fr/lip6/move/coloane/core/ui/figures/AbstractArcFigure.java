package fr.lip6.move.coloane.core.ui.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;

import fr.lip6.move.coloane.core.ui.dialogs.ColorsPrefs;

public abstract class AbstractArcFigure {

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.views.INodeFigure#setSelect()
	 */
	public final void setSelect() {
		for (Object obj : getChildren()) {
			IFigure figure = (IFigure) obj;
			figure.setForegroundColor(ColorsPrefs.setColor("COLORNODE")); //$NON-NLS-1$
			((Shape) figure).setLineWidth(IElementFigure.LINE_WIDTH);
		}
		isSelected = true;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.figures.INodeFigure#setSelectSpecial()
	 */
	public final void setSelectSpecial() {
		for (Object obj : getChildren()) {
			IFigure figure = (IFigure) obj;
			figure.setForegroundColor(ColorConstants.red);
			((Shape) figure).setLineWidth(IElementFigure.LINE_WIDTH);
		}
		isSelected = true;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.figures.INodeFigure#setHighlight()
	 */
	public final void setHighlight() {
		for (Object obj : getChildren()) {
			IFigure figure = (IFigure) obj;
			figure.setBackgroundColor(ColorsPrefs.setColor("COLORNODE_HIGHLIGHT")); //$NON-NLS-1$
		}
		isSelected = true;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.views.INodeFigure#setUnselect()
	 */
	public final void setUnselect() {
		for (Object obj : getChildren()) {
			IFigure figure = (IFigure) obj;
			figure.setForegroundColor(graphicInfo.getForeground());

			if (graphicInfo.isFilled()) {
				figure.setBackgroundColor(IElementFigure.FILLED_BACKGROUND_COLOR);
			} else {
				figure.setBackgroundColor(graphicInfo.getBackground());
			}

			if (figure instanceof Shape) {
				((Shape) figure).setLineWidth(1);
			}
		}
		isSelected = false;
	}

	@Override
	public final void setBackgroundColor(Color bg) {
		for (Object obj : getChildren()) {
			IFigure figure = (IFigure) obj;
			figure.setBackgroundColor(bg);
		}
	}

	@Override
	public final void setForegroundColor(Color fg) {
		if (!isSelected) {
			for (Object obj : getChildren()) {
				IFigure child = (IFigure) obj;
				child.setForegroundColor(fg);
			}
		}
	}

	@Override
	public final void setSize(int w, int h) {
		int dw = w - getSize().width;
		int dh = h - getSize().height;
		super.setSize(w, h);
		for (Object o : getChildren()) {
			Figure child = (Figure) o;
			Dimension dim = child.getSize();
			child.setSize(dim.width + dw, dim.height + dh);
		}
	}

	@Override
	public final void paint(Graphics graphics) {
		graphics.setAntialias(SWT.ON);
		super.paint(graphics);
	}
}
