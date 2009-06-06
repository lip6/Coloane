package fr.lip6.move.coloane.extension.importExportPNML.exportToPNML;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.AbsoluteBendpoint;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.extensions.IExportTo;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.pnml.framework.general.PnmlExport;
import fr.lip6.move.pnml.framework.utils.ModelRepository;
import fr.lip6.move.pnml.framework.utils.exception.InvalidIDException;
import fr.lip6.move.pnml.framework.utils.exception.VoidRepositoryException;
import fr.lip6.move.pnml.ptnet.hlapi.AnnotationGraphicsHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.ArcGraphicsHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.ArcHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.NameHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.NodeGraphicsHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.NodeHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.OffsetHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PNTypeHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PTArcAnnotationHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PTMarkingHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PageHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PetriNetDocHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PetriNetHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PlaceHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PositionHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.TransitionHLAPI;

public class ExportToImpl implements IExportTo {

	/**
	 * Constructeur
	 */
	public ExportToImpl() {
	}

	/** {@inheritDoc} */
	public final void export(IGraph graph, String filePath, IProgressMonitor monitor) throws ColoaneException {
		PetriNetDocHLAPI doc;

		int totalWork = graph.getNodes().size() + graph.getArcs().size();
		monitor.beginTask("Export to PNML", totalWork);

		try {
			// Remise a zero pour eviter des collisions entre ID d'objets
			ModelRepository.reset();
			ModelRepository.getInstance().createDocumentWorkspace("coloane-workspace-export");

			// Creation de l'interface de haut-niveau
			doc = new PetriNetDocHLAPI();
			PetriNetHLAPI net = new PetriNetHLAPI("colo-export", PNTypeHLAPI.PTNET, new NameHLAPI("coloane"), doc);
			PageHLAPI page = new PageHLAPI("main-page", new NameHLAPI("main-page"), null, net);

			// Création des noeuds
			monitor.subTask("Export nodes");
			for (INode node : graph.getNodes()) {
				final int nodeX = node.getGraphicInfo().getLocation().x;
				final int nodeY = node.getGraphicInfo().getLocation().y;
				// Les places
				if (node.getNodeFormalism().getName().equalsIgnoreCase("place")) {
					PlaceHLAPI place = new PlaceHLAPI(String.valueOf("n" + node.getId()), page);
					// place graphics
					final NodeGraphicsHLAPI placeGraphics = new NodeGraphicsHLAPI(new PositionHLAPI(node.getGraphicInfo().getLocation().x, node
							.getGraphicInfo().getLocation().y), null, null, null);
					place.setNodegraphicsHLAPI(placeGraphics);
					// place name
					NameHLAPI placeName = new NameHLAPI(node.getAttribute("name").getValue());
					place.setNameHLAPI(placeName);
					final OffsetHLAPI nameOffset = new OffsetHLAPI(nodeX - node.getAttribute("name").getGraphicInfo().getLocation().x, nodeY
							- node.getAttribute("name").getGraphicInfo().getLocation().y);
					final AnnotationGraphicsHLAPI nameAnnotation = new AnnotationGraphicsHLAPI(nameOffset, null, null, null);
					placeName.setAnnotationgraphicsHLAPI(nameAnnotation);
					// place marking
					PTMarkingHLAPI placeMarking = new PTMarkingHLAPI(Integer.valueOf(node.getAttribute("marking").getValue()));
					place.setInitialMarkingHLAPI(placeMarking);
					final OffsetHLAPI markingOffset = new OffsetHLAPI(nodeX - node.getAttribute("marking").getGraphicInfo().getLocation().x, nodeY
							- node.getAttribute("marking").getGraphicInfo().getLocation().y);
					final AnnotationGraphicsHLAPI markingAnnotation = new AnnotationGraphicsHLAPI(markingOffset, null, null, null);
					placeMarking.setAnnotationgraphicsHLAPI(markingAnnotation);
				}

				// Les transitions
				if (node.getNodeFormalism().getName().equalsIgnoreCase("transition")) {
					TransitionHLAPI transition = new TransitionHLAPI(String.valueOf("n" + node.getId()), page);
					final NodeGraphicsHLAPI transitionGraphics = new NodeGraphicsHLAPI(new PositionHLAPI(node.getGraphicInfo().getLocation().x, node
							.getGraphicInfo().getLocation().y), null, null, null);
					transition.setNodegraphicsHLAPI(transitionGraphics);
					transition.setNameHLAPI(new NameHLAPI(node.getAttribute("name").getValue()));
					// transition name
					NameHLAPI trName = new NameHLAPI(node.getAttribute("name").getValue());
					transition.setNameHLAPI(trName);
					final OffsetHLAPI nameOffset = new OffsetHLAPI(nodeX - node.getAttribute("name").getGraphicInfo().getLocation().x, nodeY
							- node.getAttribute("name").getGraphicInfo().getLocation().y);
					final AnnotationGraphicsHLAPI nameAnnotation = new AnnotationGraphicsHLAPI(nameOffset, null, null, null);
					trName.setAnnotationgraphicsHLAPI(nameAnnotation);
				}
				monitor.worked(1);
			}

			// Création des arcs
			monitor.subTask("Export arcs");
			for (IArc arc : graph.getArcs()) {
				NodeHLAPI source = (NodeHLAPI) ModelRepository.getInstance().getCurrentIdRepository().getObject("n" + String.valueOf(arc.getSource().getId()));
				NodeHLAPI target = (NodeHLAPI) ModelRepository.getInstance().getCurrentIdRepository().getObject("n" + String.valueOf(arc.getTarget().getId()));
				ArcHLAPI PNMLarc = new ArcHLAPI(String.valueOf("a" + arc.getId()), source, target, page);
				new PTArcAnnotationHLAPI(Integer.valueOf(arc.getAttribute("valuation").getValue()), PNMLarc);
				final ArcGraphicsHLAPI myArcGraphics = new ArcGraphicsHLAPI(PNMLarc);
				for (AbsoluteBendpoint aPi : arc.getInflexPoints()) {
					final PositionHLAPI arcPosition = new PositionHLAPI(Integer.valueOf(aPi.x), Integer.valueOf(aPi.y));
					myArcGraphics.addPositionsHLAPI(arcPosition);
				}
				monitor.worked(1);
			}

		} catch (InvalidIDException e) {
			System.out.println("Double aie..." + e);
			e.printStackTrace();
			return;
		} catch (VoidRepositoryException vre) {
			System.out.println("Triple aie..." + vre);
			vre.printStackTrace();
			return;
		}

		try {
			PnmlExport pex = new PnmlExport();
			pex.exportObject(doc, filePath, true);
		} catch (Exception e) {
			System.out.println("Echec...");
		}
		monitor.done();
	}
}
