package fr.lip6.move.coloane.core.ui.figures;

import fr.lip6.move.coloane.core.ui.dialogs.ColorsPrefs;
import fr.lip6.move.coloane.interfaces.model.INodeGraphicInfo;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Shape;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;

public abstract class AbstractNodeFigure extends Shape implements INodeFigure {

	private INodeGraphicInfo graphicInfo;
	private boolean isSelected;

	public AbstractNodeFigure(INodeGraphicInfo graphicInfo) {
		this.graphicInfo = graphicInfo;
	}

	protected final INodeGraphicInfo getGraphicInfo() {
		return graphicInfo;
	}

	public final void setHighlight() {
		isSelected = true;
		super.setBackgroundColor(ColorsPrefs.setColor("COLORNODE_HIGHLIGHT")); //$NON-NLS-1$
	}

	public final void setSelect() {
		isSelected = true;
		super.setForegroundColor(ColorsPrefs.setColor("COLORNODE")); //$NON-NLS-1$
		setLineWidth(LINE_WIDTH);
	}

	public final void setSelectSpecial() {
		isSelected = true;
		super.setForegroundColor(ColorConstants.red);
		setLineWidth(LINE_WIDTH);
	}

	public final void setUnselect() {
		if (!isSelected) {
			return;
		}
		isSelected = false;
		super.setForegroundColor(graphicInfo.getForeground());
		super.setBackgroundColor(graphicInfo.getBackground());
		setLineWidth(1);
	}

	@Override
	public final void paintFigure(Graphics graphics) {
		graphics.setAntialias(SWT.ON);
		super.paintFigure(graphics);
	}

	@Override
	public final void setForegroundColor(Color fg) {
		if (!isSelected) {
			super.setForegroundColor(fg);
		}
	}

}
