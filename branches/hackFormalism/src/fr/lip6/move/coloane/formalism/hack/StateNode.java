package fr.lip6.move.coloane.formalism.hack;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import fr.lip6.move.coloane.core.ui.figures.AbstractNodeFigure;
import fr.lip6.move.coloane.core.ui.figures.INodeFigure;
import fr.lip6.move.coloane.interfaces.model.INode;

public class StateNode extends AbstractNodeFigure implements INodeFigure {

	@Override
	public ConnectionAnchor getConnectionAnchor() {
		return new EllipseAnchor(this);
	}

	@Override
	protected void fillShape(Graphics graphics) {
		graphics.fillOval(getBounds().getResized(-1, -1));
	}

	@Override
	protected void outlineShape(Graphics graphics) {
		Rectangle r = Rectangle.SINGLETON;
		r.setBounds(getBounds());
		r.width--;
		r.height--;
		r.shrink((lineWidth - 1) / 2, (lineWidth - 1) / 2);
		graphics.drawOval(r);
				
		if (Boolean.parseBoolean(getModel().getAttribute("Initial").getValue())) {
			this.initTriangle(graphics);
		}
		if (Boolean.parseBoolean(getModel().getAttribute("Final").getValue())) {
			this.finalTriangle(graphics);
		}
	}
	
	private void initTriangle(Graphics graphics){
		Color oldbg = graphics.getBackgroundColor();
		Color noir = new Color(Display.getCurrent(), 0,0,0);
		graphics.setBackgroundColor(noir);
		Rectangle bounds = Rectangle.SINGLETON;
		bounds.setBounds(getBounds());
		PointList triangle = new PointList();
		triangle.addPoint(bounds.x + bounds.width/2,bounds.y + bounds.height/2);
		triangle.addPoint(bounds.x,bounds.y + bounds.height/4);
		triangle.addPoint(bounds.x + bounds.width/4,bounds.y);
		graphics.fillPolygon(triangle);
		graphics.setBackgroundColor(oldbg);
	}

	private void finalTriangle(Graphics graphics){
		Color oldbg = graphics.getBackgroundColor();
		Color noir = new Color(Display.getCurrent(), 0,0,0);
		graphics.setBackgroundColor(noir);
		Rectangle bounds = Rectangle.SINGLETON;
		bounds.setBounds(getBounds());
		PointList triangle = new PointList();
		triangle.addPoint(bounds.x + bounds.width,bounds.y + bounds.height);
		triangle.addPoint(bounds.x + 3*bounds.width/4,bounds.y + bounds.height/2);
		triangle.addPoint(bounds.x + bounds.width/2,bounds.y + 3*bounds.height/4);
		graphics.fillPolygon(triangle);
		graphics.setBackgroundColor(oldbg);
	}
}
