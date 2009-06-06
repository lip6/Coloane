package fr.lip6.move.coloane.extension.importExportPNML.importFromPNML;

import java.util.HashMap;

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
 * 
 * @author lom
 * 
 */
public class PTProcessor extends Processor {

	/**
	 * Keep correspondence between Coloane node ids and PNML Nodes. We need them
	 * to create Coloana arcs.
	 */
	private HashMap<NodeHLAPI, Integer> nodesIds = null;

	/**
	 * Processor of P/T nets.
	 */
	public PTProcessor() {
		super();
	}

	/**
	 * Processes top-level class in the Petri net document. This top-level class
	 * should be a PetriNetDoc(HLAPI).
	 * 
	 * @param rcl
	 *            the Petri net document root class
	 * @param formalism
	 *            the Coloane PN formalism
	 * @return {@link IGraph} aColoane graph
	 * @throws ModelException
	 *             something went wrong during models fetching
	 * 
	 */
	@Override
	public final IGraph process(HLAPIRootClass rcl, String formalism) throws ModelException {

		final PetriNetDocHLAPI root = (PetriNetDocHLAPI) rcl;
		IGraph topGraph = new GraphModel(formalism);
		nodesIds = new HashMap<NodeHLAPI, Integer>();
		for (PetriNetHLAPI iterableElement : root.getNetsHLAPI()) {
			topGraph.addGraph(processNet(iterableElement, formalism));
		}
		return null;

	}

	/**
	 * Processes a Petri net model.
	 * 
	 * @param ptn
	 *            the top-level class of a Petri net model.
	 * @param formalism
	 *            a Coloane graph formalims
	 * @return {@link IGraph} a Coloane graph
	 * @throws ModelException
	 */
	private IGraph processNet(PetriNetHLAPI ptn, String formalism) throws ModelException {
		IGraph netGraph = new GraphModel(formalism);
		netGraph.getAttribute("name").setValue(ptn.getName() != null ? ptn.getName().getText() : "");

		for (PageHLAPI iterableElement : ptn.getPagesHLAPI()) {
			processPages(iterableElement, netGraph);
		}

		return netGraph;

	}

	/**
	 * Processes a Page.
	 * 
	 * @param pth
	 *            the Page to process
	 * @param netGraph
	 *            a Coloane graph
	 * @throws ModelException
	 *             something went wrong during Page processing
	 */
	private void processPages(PageHLAPI pth, IGraph netGraph) throws ModelException {

		for (PageHLAPI iterableElement : pth.getObjects_PageHLAPI()) {
			processPages(iterableElement, netGraph);
		}
		for (PlaceHLAPI iterableElement : pth.getObjects_PlaceHLAPI()) {
			processPlace(iterableElement, netGraph);
		}
		for (TransitionHLAPI iterableElement : pth.getObjects_TransitionHLAPI()) {
			processTransition(iterableElement, netGraph);
		}

		for (ArcHLAPI iterableElement : pth.getObjects_ArcHLAPI()) {
			processArc(iterableElement, netGraph);
		}

	}

	/**
	 * Processes a Node.
	 * 
	 * @param nhp
	 *            the node to process
	 * @param shape
	 *            the shape it should be given in dot
	 * @param netGraph
	 *            Coloane net graph
	 * @return {@link INode} a Coloane graph node
	 * @throws ModelException
	 */
	private INode processNode(NodeHLAPI nhp, String shape, IGraph netGraph) throws ModelException {
		INode node = netGraph.createNode(shape);
		nodesIds.put(nhp, node.getId());
		NodeGraphicsHLAPI nodeGrap = nhp.getNodegraphicsHLAPI();
		NameHLAPI nodeName = nhp.getNameHLAPI();
		AnnotationGraphics nameGraphics = nodeName.getAnnotationgraphics();

		node.getAttribute("name").setValue(nodeName != null ? nodeName.getText() : "");
		if (nodeGrap != null && nodeGrap.getPosition() != null) {
			node.getGraphicInfo().setLocation(new Point(nodeGrap.getPosition().getX(), nodeGrap.getPosition().getY()));

			if (nameGraphics != null && nameGraphics.getOffset() != null) {
				int x = nameGraphics.getOffset().getX() + nodeGrap.getPositionHLAPI().getX();
				int y = nameGraphics.getOffset().getY() + nodeGrap.getPositionHLAPI().getY();
				node.getAttribute("name").getGraphicInfo().setLocation(new Point(x, y));
			}
		}
		return node;
	}

	/**
	 * Processes a Place.
	 * 
	 * @param pla
	 *            the place to process.
	 * @param netGraph
	 *            the Coloane net graph
	 * @throws ModelException
	 *             something went wrong during transition fetching
	 */
	private void processPlace(PlaceHLAPI pla, IGraph netGraph) throws ModelException {
		INode moi = processNode(pla, "place", netGraph);
		moi.getAttribute("marking").setValue(String.valueOf(pla.getInitialMarkingHLAPI() != null ? pla.getInitialMarkingHLAPI().getText() : ""));

	}

	/**
	 * Processes a Transition.
	 * 
	 * @param tra
	 *            the transition to process
	 * @param shape
	 *            transition
	 * @param netGraph
	 *            Coloane net graph
	 * @throws ModelException
	 *             something went wrong during transition fetching
	 */
	private void processTransition(TransitionHLAPI tra, IGraph netGraph) throws ModelException {
		processNode(tra, "transition", netGraph);
	}

	/**
	 * Processes an Arc.
	 * 
	 * @param arc
	 *            the arc to process.
	 * @param netGraph
	 * 
	 * @throws ModelException
	 *             something went wrong during arc processing.
	 */
	private void processArc(ArcHLAPI arc, IGraph netGraph) throws ModelException {

		NodeHLAPI src = arc.getSourceHLAPI();
		if (src.getClass().equals(fr.lip6.move.pnml.ptnet.hlapi.RefPlaceHLAPI.class)) {
			RefPlaceHLAPI refPlace = (fr.lip6.move.pnml.ptnet.hlapi.RefPlaceHLAPI) src;
			src = refPlace.getRefHLAPI();
		} else if (src.getClass().equals(fr.lip6.move.pnml.ptnet.hlapi.RefTransitionHLAPI.class)) {
			RefTransitionHLAPI refTransition = (fr.lip6.move.pnml.ptnet.hlapi.RefTransitionHLAPI) src;
			src = refTransition.getRefHLAPI();
		}
		NodeHLAPI target = arc.getTargetHLAPI();
		if (target.getClass().equals(fr.lip6.move.pnml.ptnet.hlapi.RefPlaceHLAPI.class)) {
			RefPlaceHLAPI refPlace = (fr.lip6.move.pnml.ptnet.hlapi.RefPlaceHLAPI) target;
			target = refPlace.getRefHLAPI();
		} else if (target.getClass().equals(fr.lip6.move.pnml.ptnet.hlapi.RefTransitionHLAPI.class)) {
			RefTransitionHLAPI refTransition = (fr.lip6.move.pnml.ptnet.hlapi.RefTransitionHLAPI) target;
			target = refTransition.getRefHLAPI();
		}
		// sets sources and target
		IArc coloaneArc = netGraph.createArc("arc", netGraph.getNode(nodesIds.get(src)), netGraph.getNode(nodesIds.get(target)));
		// Sets Inscription
		if (arc.getInscriptionHLAPI() != null) {
			coloaneArc.getAttribute("valuation").setValue(arc.getInscriptionHLAPI().getText().toString());
		}
		// Sets bend points
		if (arc.getArcgraphicsHLAPI() != null) {
			for (PositionHLAPI pos : arc.getArcgraphicsHLAPI().getPositionsHLAPI()) {
				coloaneArc.addInflexPoint(new Point(pos.getX(), pos.getY()));
			}
		}
	}
}
