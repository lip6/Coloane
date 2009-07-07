package fr.lip6.move.coloane.tools.stats;

import java.util.ArrayList;
import java.util.Iterator;

import java.util.List;

import fr.lip6.move.coloane.tools.stats.results.Result;
import fr.lip6.move.coloane.tools.stats.results.SubResult;
import fr.lip6.move.coloane.core.extensions.IColoaneAction;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;

public class StatAction implements IColoaneAction {

	public List<IResult> run(IGraph model) {
		
		Result res1 = new Result("Nom du menu racine", "Plop", null);
		SubResult subres1 = new SubResult("Places et transitions");
		SubResult subres2 = new SubResult("Arcs");
		
		Iterator<INode> itn = model.getNodes().iterator();
		while(itn.hasNext()) {
			INode node = itn.next();
			subres1.addObjectOutline(node.getId());
		}
		
		subres1.setTextualResultsMenu("1","2","3");
		subres1.addTextualResult("plop","plip","plup");
		
		
		
		/*
		Iterator<IArc> it = model.getArcs().iterator();
		while(it.hasNext()) {
			IArc arc = it.next();
			subres2.addObjectOutline(arc.getId());
		}
		*/
		
		res1.addSubResult(subres1);
		//res1.addSubResult(subres2);
			
		
		ArrayList<IResult> al = new ArrayList<IResult>();
		al.add(res1);
		return al;
	}
}
