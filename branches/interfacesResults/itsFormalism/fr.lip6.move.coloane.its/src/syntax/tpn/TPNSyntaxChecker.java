package syntax.tpn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import syntax.ISyntaxChecker;
import syntax.ISyntaxRule;
import fr.lip6.move.coloane.interfaces.formalism.IArcFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IElementFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IGraphFormalism;
import fr.lip6.move.coloane.interfaces.formalism.INodeFormalism;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;

public class TPNSyntaxChecker implements ISyntaxChecker {

	private List<ISyntaxRule> rules  = new ArrayList<ISyntaxRule>();
	private Map<IElementFormalism, List<ISyntaxRule>> eltRules = new HashMap<IElementFormalism, List<ISyntaxRule>>();
	
	@Override
	public void addRule(ISyntaxRule rule) {
		rules.add(rule);
		for (IElementFormalism ief: rule.getRuleTypes()) {
			getRulesPerElt (ief).add(rule);
		}			
	}

	
	
	private List<ISyntaxRule> getRulesPerElt(IElementFormalism ief) {
		if (! eltRules.containsKey(ief)) {
			eltRules.put(ief, new ArrayList<ISyntaxRule>());
		}
		return eltRules.get(ief);
	}



	@Override
	public IResult check(IGraph graph) {
		Result result = new Result();

		// TODO : check this is true
		graph.getFormalism().getName().equals(getFormalism());
		
		IGraphFormalism tpn = graph.getFormalism().getMasterGraph();
		
		for (ISyntaxRule rule : getRulesPerElt(tpn.getElementFormalism("graph"))) {
			rule.check(graph, result);
		}
		for (INode node : graph.getNodes()) {
			INodeFormalism ief = node.getNodeFormalism();
			for (ISyntaxRule rule : getRulesPerElt(ief)) {
				rule.check(graph, result);
			}
		}
		for (IArc arc : graph.getArcs()) {
			IArcFormalism iaf = arc.getArcFormalism();
			for (ISyntaxRule rule : getRulesPerElt(iaf)) {
				rule.check(graph, result);
			}
		}
		
		return result;
	}

	@Override
	public String getFormalism() {
		return "Time Petri Nets";
	}

}
