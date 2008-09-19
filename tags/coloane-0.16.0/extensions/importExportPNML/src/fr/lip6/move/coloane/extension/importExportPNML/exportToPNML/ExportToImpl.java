package fr.lip6.move.coloane.extension.importExportPNML.exportToPNML;

import org.eclipse.core.runtime.IProgressMonitor;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.extensions.IExportTo;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.pnml.framework.general.PnmlExport;
import fr.lip6.move.pnml.framework.utils.ModelRepository;
import fr.lip6.move.pnml.framework.utils.exception.InvalidIDException;
import fr.lip6.move.pnml.framework.utils.exception.VoidRepositoryException;
import fr.lip6.move.pnml.ptnet.hlapi.ArcHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.NameHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.NodeHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PNTypeHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PTMarkingHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PageHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PetriNetDocHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PetriNetHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PlaceHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.TransitionHLAPI;

public class ExportToImpl implements IExportTo {

	/**
	 * Constructeur
	 */
	public ExportToImpl() { System.err.println("mayou"); }

	/** {@inheritDoc} */
	public final void export(IGraph graph, String filePath, IProgressMonitor monitor) throws ColoaneException {
		PetriNetDocHLAPI doc;

		int totalWork = graph.getNodes().size() + graph.getArcs().size();
		monitor.beginTask("Export to PNML", totalWork);

		try {
			ModelRepository.reset();
			ModelRepository.getInstance().createModelWorkspace("toto");
			doc = new PetriNetDocHLAPI();
			PetriNetHLAPI net = new PetriNetHLAPI("colo-export", PNTypeHLAPI.COREMODEL, new NameHLAPI("coloane"), doc);
			PageHLAPI page = new PageHLAPI("main", new NameHLAPI("main"), null, net);

			// Création des noeuds
			monitor.subTask("Export nodes");
			for (INode node : graph.getNodes()) {
				
				// Les places
				if (node.getNodeFormalism().getName().equalsIgnoreCase("place")) {
					PlaceHLAPI place = new PlaceHLAPI(String.valueOf("colo" + node.getId()), page);
					place.setName(new NameHLAPI(node.getAttribute("name").getValue()));
					place.setInitialMarking(new PTMarkingHLAPI(Integer.valueOf(node.getAttribute("marking").getValue())));
				}

				// Les transitions
				if (node.getNodeFormalism().getName().equalsIgnoreCase("transition")) {
					TransitionHLAPI transition = new TransitionHLAPI(String.valueOf("colo" + node.getId()), page);
					transition.setName(new NameHLAPI(node.getAttribute("name").getValue()));
				}
				monitor.worked(1);
			}

			// Création des arcs
			monitor.subTask("Export arcs");
			for (IArc arc : graph.getArcs()) {
				NodeHLAPI source = (NodeHLAPI) ModelRepository.getInstance().getCurrentIdRepository().getObject("colo" + String.valueOf(arc.getSource().getId()));
				NodeHLAPI target = (NodeHLAPI) ModelRepository.getInstance().getCurrentIdRepository().getObject("colo" + String.valueOf(arc.getTarget().getId()));
				ArcHLAPI a = new ArcHLAPI(String.valueOf("colo" + arc.getId()), source, target);
				a.setContainerPage(page);
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
			pex.exportObject(doc, filePath);
		} catch (Exception e) {
			System.out.println("Echec...");
		}
		monitor.done();
	}
}
