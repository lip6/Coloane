package fr.lip6.move.coloane.tools.stats;

import java.util.ArrayList;

import java.util.List;

import fr.lip6.move.coloane.apicami.objects.result.Result;
import fr.lip6.move.coloane.apicami.objects.result.SubResult;
import fr.lip6.move.coloane.apicami.objects.result.Tip;
import fr.lip6.move.coloane.core.extensions.IColoaneAction;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;
import fr.lip6.move.coloane.interfaces.objects.result.ITip;

public class StatAction implements IColoaneAction {

	public List<IResult> run(IGraph model) {
		//System.out.println("Nombre de noeuds : "+model.getNodes().size());
		//System.out.println("Nombre d'arcs : "+model.getArcs().size());

		Result res1 = new Result("", "Plop", null);
		SubResult subres1 = new SubResult("Name subresult1", 1);
		SubResult subres2 = new SubResult("Name subresult2", 2);
		SubResult subres3 = new SubResult("Name subresult3", 3);
		/*
		ITip tip = new Tip(1, "Name tip", "Value tip");
		res.addSubResult(subres1);
		res.addSubResult(subres2);
		res.addTip(tip);
		*/

		res1.addSubResult(subres1);
		res1.addSubResult(subres2);
		res1.addSubResult(subres3);
		
		//res.addSubResult(subres1);
		
		ArrayList<IResult> al = new ArrayList<IResult>();
		al.add(res1);
		return al;
	}
}
