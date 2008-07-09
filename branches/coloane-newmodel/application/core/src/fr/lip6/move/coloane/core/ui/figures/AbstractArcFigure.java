package fr.lip6.move.coloane.core.ui.figures;

import fr.lip6.move.coloane.interfaces.model.IArcGraphicInfo;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.graphics.Color;

public abstract class AbstractArcFigure extends Shape implements IArcFigure {

	private IArcGraphicInfo graphicInfo;
	private boolean isSelected;

	public AbstractArcFigure(IArcGraphicInfo graphicInfo) {
		this.graphicInfo = graphicInfo;
	}

	public final void setUnselect() {
		if (!isSelected) {
			return;
		}
		isSelected = false;
		super.setForegroundColor(graphicInfo.getColor());
		setLineWidth(1);
	}

	@Override
	public final void setForegroundColor(Color fg) {
		if (!isSelected) {
			super.setForegroundColor(fg);
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
}
