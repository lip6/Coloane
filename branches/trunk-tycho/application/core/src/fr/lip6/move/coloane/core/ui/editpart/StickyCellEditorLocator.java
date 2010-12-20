package fr.lip6.move.coloane.core.ui.editpart;

import fr.lip6.move.coloane.core.ui.figures.sticky.StickyNoteFigure;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Text;


/**
 * Compute the best location for the editing zone associated to a sticky note
 * @see CellEditorLocator
 */
public final class StickyCellEditorLocator implements CellEditorLocator {

	private StickyNoteFigure stickyNote;

	/**
	 * @param stickyNote Associated sticky note
	 */
	public StickyCellEditorLocator(StickyNoteFigure stickyNote) {
		setLabel(stickyNote);
	}

	/** 
	 * {@inheritDoc}
	 */
	public void relocate(CellEditor celleditor) {
		Text text = (Text) celleditor.getControl();
		Rectangle rect = stickyNote.getClientArea();
		stickyNote.translateToAbsolute(rect);
		org.eclipse.swt.graphics.Rectangle trim = text.computeTrim(0, 0, 0, 0);
		rect.translate(trim.x, trim.y);
		rect.width += trim.width;
		rect.height += trim.height;
		text.setBounds(rect.x, rect.y, rect.width, rect.height);
	}

	/**
	 * @return the stickyNote figure.
	 */
	protected StickyNoteFigure getLabel() {
		return stickyNote;
	}

	/**
	 * Sets the Sticky note figure.
	 * @param stickyNote The stickyNote to set
	 */
	protected void setLabel(StickyNoteFigure stickyNote) {
		this.stickyNote = stickyNote;
	}
}
