package fr.lip6.move.coloane.core.ui.figures;

import fr.lip6.move.coloane.core.model.interfaces.ICoreTip;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;

/**
 * A rounded rectangle with text inside.
 */
public class TipFigure extends RoundedRectangle {
	private static final int MARGIN = 5;

	/**
	 * Create a tip with a unmodifiable text.
	 * @param tip text of the tip
	 */
	public TipFigure(ICoreTip tip) {
		setBorder(new MarginBorder(MARGIN));
		setCornerDimensions(new Dimension(10, 10));

		Label label = new Label(tip.getName() + " : " + tip.getValue()); //$NON-NLS-1$
		// TODO : sortir la police dans les préférences
		label.setFont(new Font(null, "arial", 10, SWT.NORMAL)); //$NON-NLS-1$

		setSize(label.getTextBounds().getSize().expand(MARGIN * 2, MARGIN * 2));
		setBackgroundColor(ColorConstants.lightGray);
		setLayoutManager(new StackLayout());
		add(label);
	}
}
