package fr.lip6.move.coloane.core.ui.figures;

import org.eclipse.draw2d.Figure;

public abstract class AbstractElementFigure extends Figure {
	public AbstractElementFigure() {
		createFigure();
	}

	protected abstract void createFigure();
}
