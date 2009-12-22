package fr.lip6.move.coloane.projects.its.syntax.tool;

import fr.lip6.move.coloane.core.extensions.IColoaneAction;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;
import fr.lip6.move.coloane.projects.its.syntax.ISyntaxChecker;
import fr.lip6.move.coloane.projects.its.syntax.tpn.IntegerExpressionCheck;
import fr.lip6.move.coloane.projects.its.syntax.tpn.TPNSyntaxChecker;

import java.util.ArrayList;
import java.util.List;

public class SyntaxCheckAction implements IColoaneAction {

	ISyntaxChecker tpncheck;
	
	public SyntaxCheckAction() {
		tpncheck = new TPNSyntaxChecker();
		tpncheck.addRule(new IntegerExpressionCheck());
	}
	
	public List<IResult> run(IGraph model) {
		List<IResult> res=new ArrayList<IResult>();
		res.add(tpncheck.check(model));
		return res;
	}

}
