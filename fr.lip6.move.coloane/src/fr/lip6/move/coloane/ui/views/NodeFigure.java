package fr.lip6.move.coloane.ui.views;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;

import fr.lip6.move.coloane.interfaces.models.INodeGraphicInfo;
import fr.lip6.move.coloane.motor.models.AbstractModelElement;
import fr.lip6.move.coloane.motor.models.NodeImplAdapter;

public class NodeFigure extends Figure implements INodeFigure, HandleBounds {

	private IFigure figure;
	private IFigure figure2;
	private Label label;
	
	private INodeGraphicInfo nodeGraphInfo;

	
	public NodeFigure (AbstractModelElement element) {
		if (element instanceof NodeImplAdapter) {
			createNodeFigure((NodeImplAdapter) element);
		}
	}
	
	private void createNodeFigure(NodeImplAdapter node) {
		// Recupere les options graphiques
		nodeGraphInfo = node.getGraphicInfo();
		
		FlowLayout layout = new FlowLayout();
		layout.setMajorAlignment(FlowLayout.ALIGN_LEFTTOP);
		setLayoutManager(layout);	
		
		
		if (nodeGraphInfo.getFigureStyle() == INodeGraphicInfo.FIG_CIRCLE) {
			figure = new Ellipse();
			figure.setForegroundColor(ColorConstants.black);
			figure.setSize(16, 16);
			
			label = new Label("");
			label.setSize(10, 10);
			
			add(figure);
			add(label);
		} else if (nodeGraphInfo.getFigureStyle() == INodeGraphicInfo.FIG_DBLCIRCLE) {
			figure = new Ellipse();
			figure.setForegroundColor(ColorConstants.black);
			figure.setSize(16, 16);
			add(figure);
			
			figure2 = new Ellipse();
			figure2.setForegroundColor(ColorConstants.black);
			figure2.setSize(12, 12);
			figure2.setLocation(new Point(2,2));
			add(figure2);
			
			label = new Label("");
			label.setSize(10, 10);
			add(label);
		} else if (nodeGraphInfo.getFigureStyle() == INodeGraphicInfo.FIG_QUEUE) {
			figure = new RoundedRectangle();
			figure.setSize(16, 8);
			figure.setForegroundColor(ColorConstants.black);
			add(figure);	
			
			label = new Label("");
			label.setSize(10, 10);
			add(label);
		} else {
			figure = new RectangleFigure();
			figure.setSize(24, 8);
			figure.setForegroundColor(ColorConstants.black);
			
			if (nodeGraphInfo.isFilled()) {
				figure.setBackgroundColor(ColorConstants.black);
			}			
			add(figure);
			
			label = new Label("");
			label.setSize(10, 10);
			add(label);
		}
	}
	
	/**
	 * Indique les bornes reelles de la figure.
	 */
	public Rectangle getHandleBounds() {
		return new Rectangle(nodeGraphInfo.getLocation(),nodeGraphInfo.getSize());
	}
	
	/**
	 * Retourne le symbole de la figure.<br>
	 * Il faut considérer ce symbole lorsqu'on essaie de creer des connexions
	 */
	public IFigure getSymbol () {
		return figure;
	}
	
	/**
	 * Modifie la valeur du label attache
	 * @param name
	 */
	public void setNodeName(String name) {
		label.setText(name);	
	}
}