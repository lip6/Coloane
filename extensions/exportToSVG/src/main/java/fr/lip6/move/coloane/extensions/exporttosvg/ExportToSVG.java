/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jean-Baptiste VORON (LIP6) - Project Head / Initial contributor
 *   Clément DÉMOULINS (LIP6) - Project Manager
 *   Yann THIERRY-MIEG (LIP6)
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.extensions.exporttosvg;

import fr.lip6.move.coloane.core.ui.figures.IArcFigure;
import fr.lip6.move.coloane.core.ui.figures.INodeFigure;
import fr.lip6.move.coloane.core.ui.figures.RoundedPolylineConnection;
import fr.lip6.move.coloane.interfaces.exceptions.ExtensionException;
import fr.lip6.move.coloane.interfaces.extensions.IExportTo;
import fr.lip6.move.coloane.interfaces.formalism.IGraphicalDescription;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.ConnectionRouter;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.Font;

/**
 * Class to export IGraph model to a SVG File.
 * 
 * @author Clément Démoulins
 */
public class ExportToSVG implements IExportTo {

	private Map<INode, INodeFigure> nodeFigures = new HashMap<INode, INodeFigure>();

	private ConnectionRouter connectionRouter = new BendpointConnectionRouter();
	private ConnectionLayer connectionLayer = new ConnectionLayer();

	/**
	 * {@inheritDoc}
	 */
	public final void export(IGraph graph, String filePath,
			IProgressMonitor monitor) throws ExtensionException {
		monitor.beginTask("export model to svg", 7);
		Map<IFigure, IArc> arcs = new HashMap<IFigure, IArc>();
		List<IFigure> nodes = new ArrayList<IFigure>();
		List<IFigure> attributes = new ArrayList<IFigure>();

		SVGGraphics graphics = new SVGGraphics();

		// ***************************************************
		// *** 1 *** Recreate figures associated to this graph

		// Create attributes attach to the graph
		monitor.subTask("Create graph attributes");
		attributes
				.addAll(createAttributeFigures(graph.getDrawableAttributes()));
		monitor.worked(1);

		// Create node's figures
		monitor.subTask("Create nodes and node attributes");
		for (INode node : graph.getNodes()) {
			INodeFigure fig = createNodeFigure(node);
			nodes.add(fig);

			// Create attributes attach to this node
			attributes.addAll(createAttributeFigures(node
					.getDrawableAttributes()));
		}
		monitor.worked(1);

		// Create arc's figures
		monitor.subTask("Create arcs and arc attributes");
		for (IArc arc : graph.getArcs()) {
			IArcFigure fig = createArcFigure(arc);
			arcs.put(fig, arc);

			// Create attributes attach to this arc
			attributes.addAll(createAttributeFigures(arc
					.getDrawableAttributes()));
		}
		monitor.worked(1);

		// ***************************************************
		// *** 2 *** Simulate a draw to create the svg

		// The order define the layout :
		// 1 - Draw arcs
		monitor.subTask("Draw arcs");
		for (IFigure fig : arcs.keySet()) {
			IArc arc = arcs.get(fig);
			// Calcul the position of the target decoration
			if (fig instanceof RoundedPolylineConnection) {
				((RoundedPolylineConnection) fig).layout();
			} else if (fig instanceof PolylineConnection) {
				((PolylineConnection) fig).layout();
			}
			// Use AWT Cubic curve if curve is set on this arc and bypass
			// RoundedPolylineConnection implementation.
			graphics.setCurve(arc.getGraphicInfo().getCurve());
			fig.paint(graphics);
			graphics.setCurve(false);
		}
		monitor.worked(1);

		// 2 - Draw nodes
		monitor.subTask("Draw nodes");
		for (IFigure fig : nodes) {
			fig.paint(graphics);
		}
		monitor.worked(1);

		// 3 - Draw attributes
		monitor.subTask("Draw attributes");
		for (IFigure fig : attributes) {
			fig.paint(graphics);
		}
		monitor.worked(1);

		// Write the svg document
		monitor.subTask("Write the SVG file");
		try {
			graphics.write(filePath);
		} catch (IOException e) {
			throw new ExtensionException(e.getMessage());
		} finally {
			graphics.dispose();
		}
		monitor.worked(1);
		monitor.done();
	}

	/**
	 * @param node
	 *            node
	 * @return associated figure for this node with all graphical information
	 *         loaded
	 */
	private INodeFigure createNodeFigure(INode node) {
		IGraphicalDescription graphicalDescription = node.getGraphicInfo()
				.getAllNodeFormalismGraphicalDescriptions()
				.get(node.getGraphicInfo().getGdIndex());
		INodeFigure fig = (INodeFigure) graphicalDescription
				.getAssociatedFigure();
		fig.setLocation(node.getGraphicInfo().getLocation());
		fig.setSize(node.getGraphicInfo().getSize());
		fig.setForegroundColor(node.getGraphicInfo().getForeground());
		fig.setBackgroundColor(node.getGraphicInfo().getBackground());
		nodeFigures.put(node, fig);
		return fig;
	}

	/**
	 * @param arc
	 *            arc
	 * @return associated figure for this arc with all graphical information
	 *         loaded
	 */
	private IArcFigure createArcFigure(IArc arc) {
		IGraphicalDescription graphicalDescription = arc.getArcFormalism()
				.getGraphicalDescription();
		IArcFigure fig = (IArcFigure) graphicalDescription
				.getAssociatedFigure();
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
		return fig;
	}

	/**
	 * Create attribute figures
	 * 
	 * @param attributes
	 *            list of attribute
	 * @return list of figure for each attributes
	 */
	private Collection<IFigure> createAttributeFigures(
			Collection<IAttribute> attributes) {
		List<IFigure> list = new ArrayList<IFigure>();
		for (IAttribute attr : attributes) {
			if (attr.getAttributeFormalism().isDrawable()
					&& !attr.getValue().equals(
							attr.getAttributeFormalism().getDefaultValue())) {
				final Point location = attr.getGraphicInfo().getLocation();
				Label label = new Label() {
					@Override
					public void paint(Graphics graphics) {
						graphics.setFont(getFont());
						graphics.setForegroundColor(getForegroundColor());
						graphics.setBackgroundColor(getBackgroundColor());
						graphics.fillText(getText(), location);
					}
				};
				label.setOpaque(true);
				label.setText(attr.getValue());
				label.setLocation(attr.getGraphicInfo().getLocation());
				label.setForegroundColor(attr.getGraphicInfo().getForeground());
				label.setBackgroundColor(attr.getGraphicInfo().getBackground());

				Font font = JFaceResources.getDefaultFont();
				if (attr.getAttributeFormalism().isBold()) {
					font = JFaceResources.getFontRegistry().getBold(
							JFaceResources.DEFAULT_FONT);
				} else if (attr.getAttributeFormalism().isItalic()) {
					font = JFaceResources.getFontRegistry().getItalic(
							JFaceResources.DEFAULT_FONT);
				}
				label.setFont(font);
				label.setBounds(label.getTextBounds());

				list.add(label);
			}
		}
		return list;
	}
}
