package fr.lip6.move.coloane.ui.views;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;

public class AttributeFigure extends Figure implements IAttributeFigure {
	private IFigure figure;

	public AttributeFigure () {
		//setLayoutManager(new FreeformLayout());
		figure = new Ellipse();
		figure.setForegroundColor(ColorConstants.red);
		figure.setBackgroundColor(ColorConstants.red);
		figure.setSize(3, 3);
		figure.setLocation(new Point(4,4));
		add(figure);
		System.out.println("OK pour la figure");
	}

	public Rectangle getHandleBounds() {
		// TODO Auto-generated method stub
		return null;
	}

}
