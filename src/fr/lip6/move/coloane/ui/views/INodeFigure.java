package fr.lip6.move.coloane.ui.views;

import org.eclipse.draw2d.IFigure;

public interface INodeFigure extends IFigure {
	public IFigure getSymbol();
	public void setNodeName(String name); 
}
