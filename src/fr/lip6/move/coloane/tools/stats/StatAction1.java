package fr.lip6.move.coloane.tools.stats;

import java.util.ArrayList;
import java.util.List;

import fr.lip6.move.coloane.core.extensions.IColoaneAction;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;
import fr.lip6.move.coloane.tools.stats.results.Result;
import fr.lip6.move.coloane.tools.stats.results.SubResult;

public class StatAction1 implements IColoaneAction {


	public List<IResult> run(IGraph model) {
		ArrayList<INode> tabNodes = new ArrayList<INode>(model.getNodes());
		ArrayList<IArc> tabArcs = new ArrayList<IArc>(model.getArcs());

		Result res1 = new Result("Test 2", null);
		SubResult subres1 = new SubResult("Subres 1");
		
		for(INode node : tabNodes) {
			subres1.addObjectOutline(node);
		}
		for(IArc arc : tabArcs) {
			subres1.addObjectOutline(arc);
		}

		res1.addSubResult(subres1);
		ArrayList<IResult> al = new ArrayList<IResult>();
		al.add(res1);
		return al;
	}
}
