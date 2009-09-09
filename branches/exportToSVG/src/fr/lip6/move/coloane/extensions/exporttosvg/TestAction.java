package fr.lip6.move.coloane.extensions.exporttosvg;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.ConnectionRouter;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.Font;

import fr.lip6.move.coloane.core.extensions.IColoaneAction;
import fr.lip6.move.coloane.core.ui.figures.IArcFigure;
import fr.lip6.move.coloane.core.ui.figures.INodeFigure;
import fr.lip6.move.coloane.core.ui.figures.RoundedPolylineConnection;
import fr.lip6.move.coloane.interfaces.formalism.IGraphicalDescription;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.model.command.ICommand;

public class TestAction implements IColoaneAction {

	@Override
	public List<ICommand> run(IGraph model) {
		try {
			Map<IFigure, IArc> arcs = new HashMap<IFigure, IArc>();
			List<IFigure> nodes = new ArrayList<IFigure>();
			List<IFigure> attributes = new ArrayList<IFigure>();
			
			Map<INode, INodeFigure> nodeFigures = new HashMap<INode, INodeFigure>();
			SVGGraphics graphics = new SVGGraphics();
	
			// Create node's figures
			for (INode node : model.getNodes()) {
				IGraphicalDescription graphicalDescription = node.getGraphicInfo().getAllNodeFormalismGraphicalDescriptions().get(node.getGraphicInfo().getGdIndex());
				INodeFigure fig = (INodeFigure) graphicalDescription.getAssociatedFigure();
				fig.setLocation(node.getGraphicInfo().getLocation());
				fig.setSize(node.getGraphicInfo().getSize());
				fig.setForegroundColor(node.getGraphicInfo().getForeground());
				fig.setBackgroundColor(node.getGraphicInfo().getBackground());
				
				nodeFigures.put(node, fig);

				nodes.add(fig);
				attributes.addAll(createAttributeFigures(node.getDrawableAttributes()));
			}

			System.err.println("—————————————————————————————————————————————————————————————————————— Arcs");
			//Create arc's figures
			ConnectionRouter connectionRouter = new BendpointConnectionRouter();
			ConnectionLayer connectionLayer = new ConnectionLayer();
			for (IArc arc : model.getArcs()) {
				IGraphicalDescription graphicalDescription = arc.getArcFormalism().getGraphicalDescription();
				IArcFigure fig = (IArcFigure) graphicalDescription.getAssociatedFigure();
				fig.setForegroundColor(arc.getGraphicInfo().getColor());

				fig.setConnectionRouter(connectionRouter);
				fig.setRoutingConstraint(arc.getInflexPoints());

				// Connection of the arc with the source node and the target node
				INodeFigure source = nodeFigures.get(arc.getSource());
				INodeFigure target = nodeFigures.get(arc.getTarget());
				if (source != null && target != null) {
					fig.setSourceAnchor(source.getConnectionAnchor());
					fig.setTargetAnchor(target.getConnectionAnchor());

					fig.setParent(connectionLayer);
				}

				arcs.put(fig, arc);
				attributes.addAll(createAttributeFigures(arc.getDrawableAttributes()));
			}

			// The order define the layout :
			System.err.println("—————————————————————————————————————————————————————————————————————— Draw Arcs");
			// 		1 - Draw arcs
			for (IFigure fig : arcs.keySet()) {
				IArc arc = arcs.get(fig);
				// Calcul the position of the target decoration
				if (fig instanceof RoundedPolylineConnection) {
					((RoundedPolylineConnection) fig).layout();
				} else if (fig instanceof PolylineConnection) {
					((PolylineConnection) fig).layout();
				}
				// Use AWT Cubic curve if curve is set on this arc.
				graphics.setCurve(arc.getGraphicInfo().getCurve());
				fig.paint(graphics);
				graphics.setCurve(false);
			}
			System.err.println("—————————————————————————————————————————————————————————————————————— Draw Nodes");
			// 		2 - Draw nodes
			for (IFigure fig : nodes) {
				fig.paint(graphics);
			}
			System.err.println("—————————————————————————————————————————————————————————————————————— Draw Attributes");
			// 		3 - Draw attributes
			for (IFigure fig : attributes) {
				fig.paint(graphics);
			}
		
			System.err.println("—————————————————————————————————————————————————————————————————————— Write");
			graphics.write("/home/clement/Desktop/test.svg");
			graphics.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Create attribute figures
	 * @param attributes list of attribute
	 * @return list of figure for each attributes
	 */
	private static Collection<IFigure> createAttributeFigures(Collection<IAttribute> attributes) {
		List<IFigure> list = new ArrayList<IFigure>();
		for (IAttribute attr : attributes) {
			if (attr.getAttributeFormalism().isDrawable() && !attr.getValue().equals(attr.getAttributeFormalism().getDefaultValue())) {
				Label label = new Label();
				label.setOpaque(true);
				label.setText(attr.getValue());
				label.setLocation(attr.getGraphicInfo().getLocation());
				label.setForegroundColor(attr.getGraphicInfo().getForeground());
				label.setBackgroundColor(attr.getGraphicInfo().getBackground());

				Font font = JFaceResources.getDefaultFont();
				if (attr.getAttributeFormalism().isBold()) {
					font = JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT);
				} else if (attr.getAttributeFormalism().isItalic()) {
					font = JFaceResources.getFontRegistry().getItalic(JFaceResources.DEFAULT_FONT);
				}
				label.setFont(font);
				label.setBounds(label.getTextBounds());

				list.add(label);
			}
		}
		return list;
	}
}
