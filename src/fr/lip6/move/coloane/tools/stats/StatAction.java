package fr.lip6.move.coloane.tools.stats;

import java.util.ArrayList;

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
		ArrayList<INode> tabNodes = new ArrayList<INode>(model.getNodes());
		ArrayList<IArc> tabArcs = new ArrayList<IArc>(model.getArcs());
		
		Result res1 = new Result("", "Plop", null);
		SubResult subres1 = new SubResult("Places et transitions");
		SubResult subres2 = new SubResult("Arcs");
		
		for(int i = 0; i < tabNodes.size(); i++) {
			subres1.addObjectOutline(tabNodes.get(i).getId());
		}
		
		for(int i = 0; i < tabArcs.size(); i++) {
			subres2.addObjectOutline(tabArcs.get(i).getId());
		}
		
		subres2.addTextualResult("gfgrtrthge");
		subres2.addTextualResult("gfgrtrthge");

		subres1.addTextualResult("jhiohouh");
		subres1.addTextualResult("jhiohouh");
		subres1.addTextualResult("jhiohouh");

		
		subres1.addSubResult(subres2);
		res1.addSubResult(subres1);
		

		Result res2 = new Result("", "Plip", null);
		SubResult subres21 = new SubResult("Places et transitions");
		
		for(int i = 0; i < tabNodes.size(); i++) {
			subres21.addObjectOutline(tabNodes.get(i).getId());
		}
		res2.addSubResult(subres21);
		
		
		ArrayList<IResult> al = new ArrayList<IResult>();
		al.add(res1);
		al.add(res2);
		return al;
	}
}
