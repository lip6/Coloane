package fr.lip6.move.coloane.core.ui.editpart;

import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Text;


public final class AttributeCellEditorLocator implements CellEditorLocator {

	private Label attributeFigure;

	public AttributeCellEditorLocator(Label attributeFigure) {
		this.attributeFigure = attributeFigure;
	}

	public void relocate(CellEditor celleditor) {
		Text text = (Text) celleditor.getControl();
		Rectangle rect = attributeFigure.getTextBounds();
		rect.setLocation(attributeFigure.getLocation());
		attributeFigure.translateToAbsolute(rect);
		org.eclipse.swt.graphics.Rectangle trim = text.computeTrim(0, 0, 0, 0);
		rect.translate(trim.x, trim.y);
		rect.width += trim.width;
		rect.width = Math.max(rect.width, 4);
		rect.height += trim.height;
		text.setBounds(rect.x, rect.y, rect.width, rect.height);
	}

	/**
	 * Returns the stickyNote figure.
	 */
	protected Label getLabel() {
		return attributeFigure;
	}

	/**
	 * Sets the Sticky note figure.
	 * @param stickyNote The stickyNote to set
	 */
	protected void setLabel(Label attributeFigure) {
		this.attributeFigure = attributeFigure;
	}

}
