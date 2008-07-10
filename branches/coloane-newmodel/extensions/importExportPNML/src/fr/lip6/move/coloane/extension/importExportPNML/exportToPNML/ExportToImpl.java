package fr.lip6.move.coloane.extension.importExportPNML.exportToPNML;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.extensions.IExportTo;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.pnml.framework.general.PnmlExport;
import fr.lip6.move.pnml.framework.utils.IdRepository;
import fr.lip6.move.pnml.framework.utils.exception.InvalidIDException;
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
	public ExportToImpl() { }

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.extensions.IExportTo#export(fr.lip6.move.coloane.interfaces.model.IGraph, java.lang.String)
	 */
	public final void export(IGraph graph, String filePath) throws ColoaneException {
		PetriNetDocHLAPI doc;

		try {
			doc = new PetriNetDocHLAPI();
			PetriNetHLAPI net = new PetriNetHLAPI("colo-export", PNTypeHLAPI.COREMODEL, new NameHLAPI("coloane"), doc);
			PageHLAPI page = new PageHLAPI("main", new NameHLAPI("main"), null, net);

			for (INode node : graph.getNodes()) {
				if (node.getNodeFormalism().getName().equalsIgnoreCase("place")) {
					PlaceHLAPI place = new PlaceHLAPI(String.valueOf(node.getId()), page);
					place.setName(new NameHLAPI(node.getAttribute("name").getValue()));
					place.setInitialMarking(new PTMarkingHLAPI(Integer.valueOf(node.getAttribute("marking").getValue())));
				}

				if (node.getNodeFormalism().getName().equalsIgnoreCase("transition")) {
					TransitionHLAPI transition = new TransitionHLAPI(String.valueOf(node.getId()), page);
					transition.setName(new NameHLAPI(node.getAttribute("name").getValue()));
				}
			}

			for (IArc arc : graph.getArcs()) {
				NodeHLAPI source = (NodeHLAPI) IdRepository.getObject(String.valueOf(arc.getSource().getId()));
				NodeHLAPI target = (NodeHLAPI) IdRepository.getObject(String.valueOf(arc.getTarget().getId()));
				ArcHLAPI a = new ArcHLAPI(String.valueOf(arc.getId()), source, target);
				a.setContainerPage(page);
			}
		} catch (InvalidIDException e) {
			System.out.println("Double aie...");
			return;
		}

		try {
			PnmlExport pex = new PnmlExport();
			pex.exportObject(doc, filePath);
		} catch (Exception e) {
			System.out.println("Echec...");
		}
	}
}
