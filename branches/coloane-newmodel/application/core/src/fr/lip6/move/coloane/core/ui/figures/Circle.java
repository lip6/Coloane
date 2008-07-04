package fr.lip6.move.coloane.core.ui.figures;

import fr.lip6.move.coloane.interfaces.model.INodeGraphicInfo;

import org.eclipse.draw2d.Ellipse;

public class Circle extends AbstractNodeFigure {
	private Ellipse figure;

	public Circle(INodeGraphicInfo graphicInfo) {
		super(graphicInfo);
	}

	@Override
	protected final void createFigure(INodeGraphicInfo graphicInfo) {
		figure = new Ellipse();
		figure.setSize(graphicInfo.getSize());
		figure.setForegroundColor(graphicInfo.getForeground());
		figure.setBackgroundColor(graphicInfo.getBackground());

		add(figure);
	}

}
