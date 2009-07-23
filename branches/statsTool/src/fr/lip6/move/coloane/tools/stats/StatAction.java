package fr.lip6.move.coloane.tools.stats;

import java.util.ArrayList;

import java.util.List;

import fr.lip6.move.coloane.tools.stats.results.Result;
import fr.lip6.move.coloane.tools.stats.results.SubResult;
import fr.lip6.move.coloane.tools.stats.results.Tip;
import fr.lip6.move.coloane.core.extensions.IColoaneAction;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;

public class StatAction implements IColoaneAction {

	public List<IResult> run(IGraph model) {
		ArrayList<INode> tabNodes = new ArrayList<INode>(model.getNodes());
		ArrayList<IArc> tabArcs = new ArrayList<IArc>(model.getArcs());

		Result res1 = new Result("", null);
		SubResult subres1 = new SubResult("");
		
		int i = 1;
		for (INode node : tabNodes) {
			subres1.addObjectOutline(node);
			res1.addTip(new Tip(node.getId(), "Nom "+i, "Value "+i));
			i++;
		}
		for (INode node : tabNodes) {
			subres1.addAttributeOutline(node,"name");
		}
		/*
		for (IArc arc : tabArcs) {
			subres1.addObjectOutline(arc);
		}
		 */
		for (IAttribute att : tabArcs.get(0).getAttributes())
			subres1.addAttributeOutline(tabArcs.get(0), att.getName());

		res1.addSubResult(subres1);
		ArrayList<IResult> al = new ArrayList<IResult>();
		al.add(res1);
		return al;
	}
	
	public List<IResult> run1(IGraph model) {
		ArrayList<INode> tabNodes = new ArrayList<INode>(model.getNodes());
		ArrayList<IArc> tabArcs = new ArrayList<IArc>(model.getArcs());

		Result res1 = new Result("StatTool", model);
		SubResult subres1 = new SubResult("Places et transitions", "Affiche tous les noeuds sur le graphe");
		SubResult subres2 = new SubResult("Arcs", "Affiche tous les arcs sur le graphe");

		for(int i = 0; i < tabNodes.size(); i++) {
			subres1.addObjectDesignation(tabNodes.get(i));
			//subres1.addObjectDesignation(tabNodes.get(i).getId());
		}
		for(int i = 0; i < tabArcs.size(); i++) {
			subres2.addObjectDesignation(tabArcs.get(i));
			//subres2.addObjectDesignation(tabArcs.get(i).getId());
		}


		SubResult subres3 = new SubResult("Places et transitions", "Liste tous les noeuds");
		SubResult subres4 = new SubResult("Arcs", "Liste tous les arcs");

		for(int i = 0; i < tabNodes.size(); i++) {
			subres3.addObjectOutline(tabNodes.get(i));
			//subres3.addObjectOutline(tabNodes.get(i).getId());
		}
		for(int i = 0; i < tabArcs.size(); i++) {
			subres4.addObjectOutline(tabArcs.get(i));
			//subres4.addObjectOutline(tabArcs.get(i).getId());
		}

		res1.addTextualResult("Nombre de noeuds :",String.valueOf(tabNodes.size()));
		res1.addTextualResult("Nombre d'arcs :",String.valueOf(tabArcs.size()));
		res1.addTextualResult("","","","");

		res1.addSubResult(subres1);
		res1.addSubResult(subres2);
		res1.addSubResult(subres3);
		res1.addSubResult(subres4);


		ArrayList<IResult> al = new ArrayList<IResult>();
		al.add(res1);
		return al;
	}
}
