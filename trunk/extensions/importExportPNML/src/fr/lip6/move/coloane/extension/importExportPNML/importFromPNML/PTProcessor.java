package fr.lip6.move.coloane.extension.importExportPNML.importFromPNML;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.draw2d.geometry.Point;

import fr.lip6.move.coloane.core.model.GraphModel;
import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
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

	/**
	 * Processor of P/T nets.
	 */
	public PTProcessor() {
		super();
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
	public final IGraph process(HLAPIRootClass rootClass, String formalism) throws ModelException {
		final PetriNetDocHLAPI root = (PetriNetDocHLAPI) rootClass;
		IGraph topGraph = new GraphModel(formalism);

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
	private IGraph processNet(PetriNetHLAPI pnmlNet, String formalism) throws ModelException {
		IGraph netGraph = new GraphModel(formalism);
		netGraph.getAttribute("title").setValue(pnmlNet.getName() != null ? pnmlNet.getName().getText() : "");

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
	private INode processNode(NodeHLAPI pnmlNode, String nodeType, IGraph netGraph) throws ModelException {
		INode node = netGraph.createNode(nodeType);

		// Store the reference into the hasmap to be able to create arc later
		nodesIds.put(pnmlNode.getId(), node.getId());
		NodeGraphicsHLAPI nodeGraphicInfo = pnmlNode.getNodegraphicsHLAPI();
		NameHLAPI nodeName = pnmlNode.getNameHLAPI();
		AnnotationGraphics nameGraphics = nodeName.getAnnotationgraphics();

		node.getAttribute("name").setValue(nodeName != null ? nodeName.getText() : "");
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
		INode node = processNode(pnmlPlace, "place", netGraph);
		node.getAttribute("marking").setValue(String.valueOf(pnmlPlace.getInitialMarkingHLAPI() != null ? pnmlPlace.getInitialMarkingHLAPI().getText() : ""));
	}

	/**
	 * Processes a Transition.
	 *
	 * @param pnmlTransition The transition to process
	 * @param netGraph The Coloane graph model currently in construction
	 * @throws ModelException Something went wrong during transition fetching
	 */
	private void processTransition(TransitionHLAPI pnmlTransition, IGraph netGraph) throws ModelException {
		processNode(pnmlTransition, "transition", netGraph);
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
		IArc coloaneArc = netGraph.createArc("arc", netGraph.getNode(nodesIds.get(src.getId())), netGraph.getNode(nodesIds.get(target.getId())));

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
