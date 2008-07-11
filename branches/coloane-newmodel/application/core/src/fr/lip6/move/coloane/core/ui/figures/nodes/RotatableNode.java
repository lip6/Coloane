package fr.lip6.move.coloane.core.ui.figures.nodes;

import fr.lip6.move.coloane.core.ui.figures.INodeFigure;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.SWTGraphics;

public class RotatableNode extends RectangleFigure implements INodeFigure {

	@Override
	public final void paintFigure(Graphics graphics) {
		try {
			graphics.rotate(45);
			super.paintFigure(graphics);
			graphics.restoreState();
		} catch (Exception e) {
			e.printStackTrace();
			graphics.restoreState();
			super.paintFigure(graphics);
		}
	}

	public final ConnectionAnchor getConnectionAnchor() {
		return new ChopboxAnchor(this);
	}

}
