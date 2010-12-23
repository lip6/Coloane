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
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.extension.importExportPNML.importFromPNML;

import fr.lip6.move.coloane.core.model.factory.GraphModelFactory;
import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.formalism.IArcFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.formalism.INodeFormalism;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.pnml.framework.hlapi.HLAPIRootClass;
import fr.lip6.move.pnml.ptnet.AnnotationGraphics;
import fr.lip6.move.pnml.ptnet.hlapi.ArcHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.NameHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.NodeGraphicsHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.NodeHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PageHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PetriNetDocHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PetriNetHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PlaceHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PositionHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.RefPlaceHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.RefTransitionHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.TransitionHLAPI;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.draw2d.geometry.Point;

/**
 * Place/Transition Net processor.
 * @author Lom Messan Hillah
 */
public class PTProcessor extends Processor {
	/**
	 * Keep correspondence between Coloane node ids and PNML Nodes.<br>
	 * We need them to create Coloane arcs.
	 */
	private Map<String, Integer> nodesIds = null;
	private final GraphModelFactory modelFactory;
	private INodeFormalism placeFormalism;
	private INodeFormalism transitionFormalism;
	private IArcFormalism arcFormalism;

	/**
	 * Processor of P/T nets.
	 */
	public PTProcessor() {
		super();
		modelFactory = new GraphModelFactory();
	}

	/**
	 * Processes top-level class in the Petri net document.<br>
	 * This top-level class should be a PetriNetDoc(HLAPI).
	 *
	 * @param rootClass The Petri net document root class
	 * @param formalism The Coloane PN formalism
	 * @return {@link IGraph} a Coloane graph
	 * @throws ModelException Something went wrong during models fetching
	 */
	@Override
	public final IGraph process(HLAPIRootClass rootClass, IFormalism formalism) throws ModelException {
		final PetriNetDocHLAPI root = (PetriNetDocHLAPI) rootClass;
		IGraph topGraph = modelFactory.createGraph(formalism);

		this.placeFormalism = (INodeFormalism) formalism.getRootGraph().getElementFormalism("place");
		this.transitionFormalism = (INodeFormalism) formalism.getRootGraph().getElementFormalism("transition");
		this.arcFormalism = (IArcFormalism) formalism.getRootGraph().getElementFormalism("arch");

		nodesIds = new HashMap<String, Integer>();
		for (PetriNetHLAPI iterableElement : root.getNetsHLAPI()) {
			topGraph.addGraph(processNet(iterableElement, formalism));
		}
		return topGraph;
	}

	/**
	 * Processes a Petri net model.
	 *
	 * @param pnmlNet The net to process
	 * @param formalism Coloane graph formalism
	 * @return {@link IGraph} the Coloane graph for this net
	 * @throws ModelException Something went wrong during the model construction
	 */
	private IGraph processNet(PetriNetHLAPI pnmlNet, IFormalism formalism) throws ModelException {
		IGraph netGraph = modelFactory.createGraph(formalism);
		if (pnmlNet.getName() != null) { netGraph.getAttribute("title").setValue(pnmlNet.getName().getText()); }

		// Browse all net pages
		for (PageHLAPI iterableElement : pnmlNet.getPagesHLAPI()) {
			processPages(iterableElement, netGraph);
		}

		return netGraph;
	}

	/**
	 * Processes a Page (of a net).
	 *
	 * @param pnmlPage The page to process
	 * @param model The Coloane graph model currently in construction
	 * @throws ModelException Something went wrong during Page processing
	 */
	private void processPages(PageHLAPI pnmlPage, IGraph model) throws ModelException {
		// Process all included pages
		for (PageHLAPI iterableElement : pnmlPage.getObjects_PageHLAPI()) {
			processPages(iterableElement, model);
		}

		// Process all places
		for (PlaceHLAPI iterableElement : pnmlPage.getObjects_PlaceHLAPI()) {
			processPlace(iterableElement, model);
		}

		// Process all transitions
		for (TransitionHLAPI iterableElement : pnmlPage.getObjects_TransitionHLAPI()) {
			processTransition(iterableElement, model);
		}

		// Process all arcs
		for (ArcHLAPI iterableElement : pnmlPage.getObjects_ArcHLAPI()) {
			processArc(iterableElement, model);
		}

	}

	/**
	 * Processes a Node.
	 *
	 * @param pnmlNode The node to process
	 * @param nodeType The kind of node that is going to be processed (place, transition...)
	 * @param netGraph The Coloane graph model currently in construction
	 * @return {@link INode} a Coloane graph node
	 * @throws ModelException Somthing went wrogn during model construction
	 */
	private INode processNode(NodeHLAPI pnmlNode, INodeFormalism nodeType, IGraph netGraph) throws ModelException {
		INode node = netGraph.createNode(nodeType);

		// Store the reference into the hasmap to be able to create arc later
		nodesIds.put(pnmlNode.getId(), node.getId());
		NodeGraphicsHLAPI nodeGraphicInfo = pnmlNode.getNodegraphicsHLAPI();
		NameHLAPI nodeName = pnmlNode.getNameHLAPI();
		AnnotationGraphics nameGraphics = nodeName.getAnnotationgraphics();

		if (nodeName != null) { node.getAttribute("name").setValue(nodeName.getText()); }
		if (nodeGraphicInfo != null && nodeGraphicInfo.getPosition() != null) {
			node.getGraphicInfo().setLocation(new Point(nodeGraphicInfo.getPosition().getX(), nodeGraphicInfo.getPosition().getY()));

			if (nameGraphics != null && nameGraphics.getOffset() != null) {
				int x = nameGraphics.getOffset().getX() + nodeGraphicInfo.getPositionHLAPI().getX();
				int y = nameGraphics.getOffset().getY() + nodeGraphicInfo.getPositionHLAPI().getY();
				node.getAttribute("name").getGraphicInfo().setLocation(new Point(x, y));
			}
		}
		return node;
	}

	/**
	 * Processes a Place.
	 *
	 * @param pnmlPlace The place to process.
	 * @param netGraph The Coloane graph model currently in construction
	 * @throws ModelException Something went wrong during transition fetching
	 */
	private void processPlace(PlaceHLAPI pnmlPlace, IGraph netGraph) throws ModelException {
		INode node = processNode(pnmlPlace, placeFormalism, netGraph);
		if (pnmlPlace.getInitialMarkingHLAPI() != null) {
			node.getAttribute("marking").setValue(String.valueOf(pnmlPlace.getInitialMarkingHLAPI().getText()));
		}
	}

	/**
	 * Processes a Transition.
	 *
	 * @param pnmlTransition The transition to process
	 * @param netGraph The Coloane graph model currently in construction
	 * @throws ModelException Something went wrong during transition fetching
	 */
	private void processTransition(TransitionHLAPI pnmlTransition, IGraph netGraph) throws ModelException {
		processNode(pnmlTransition, transitionFormalism, netGraph);
	}

	/**
	 * Processes an Arc.
	 *
	 * @param pnmlArc The arc to process.
	 * @param netGraph The Coloane graph model currently in construction
	 * @throws ModelException Something went wrong during arc processing.
	 */
	private void processArc(ArcHLAPI pnmlArc, IGraph netGraph) throws ModelException {

		// Fetch arc source
		NodeHLAPI src = pnmlArc.getSourceHLAPI();
		if (src.getClass().equals(fr.lip6.move.pnml.ptnet.hlapi.RefPlaceHLAPI.class)) {
			RefPlaceHLAPI refPlace = (fr.lip6.move.pnml.ptnet.hlapi.RefPlaceHLAPI) src;
			src = refPlace.getRefHLAPI();
		} else if (src.getClass().equals(fr.lip6.move.pnml.ptnet.hlapi.RefTransitionHLAPI.class)) {
			RefTransitionHLAPI refTransition = (fr.lip6.move.pnml.ptnet.hlapi.RefTransitionHLAPI) src;
			src = refTransition.getRefHLAPI();
		}

		// Fetch arc target
		NodeHLAPI target = pnmlArc.getTargetHLAPI();
		if (target.getClass().equals(fr.lip6.move.pnml.ptnet.hlapi.RefPlaceHLAPI.class)) {
			RefPlaceHLAPI refPlace = (fr.lip6.move.pnml.ptnet.hlapi.RefPlaceHLAPI) target;
			target = refPlace.getRefHLAPI();
		} else if (target.getClass().equals(fr.lip6.move.pnml.ptnet.hlapi.RefTransitionHLAPI.class)) {
			RefTransitionHLAPI refTransition = (fr.lip6.move.pnml.ptnet.hlapi.RefTransitionHLAPI) target;
			target = refTransition.getRefHLAPI();
		}

		// Set source and target
		IArc coloaneArc = netGraph.createArc(arcFormalism, netGraph.getNode(nodesIds.get(src.getId())), netGraph.getNode(nodesIds.get(target.getId())));

		// Sets Inscription
		if (pnmlArc.getInscriptionHLAPI() != null) {
			coloaneArc.getAttribute("valuation").setValue(pnmlArc.getInscriptionHLAPI().getText().toString());
		}

		// Sets Bendpoints
		if (pnmlArc.getArcgraphicsHLAPI() != null) {
			for (PositionHLAPI pos : pnmlArc.getArcgraphicsHLAPI().getPositionsHLAPI()) {
				coloaneArc.addInflexPoint(new Point(pos.getX(), pos.getY()));
			}
		}
	}
}
