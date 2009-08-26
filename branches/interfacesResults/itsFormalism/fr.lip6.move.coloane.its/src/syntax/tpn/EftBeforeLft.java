package syntax.tpn;

import java.util.ArrayList;
import java.util.List;

import syntax.ISyntaxRule;
import fr.lip6.move.coloane.core.motor.formalisms.FormalismManager;
import fr.lip6.move.coloane.interfaces.formalism.IElementFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IGraphFormalism;
import fr.lip6.move.coloane.interfaces.model.IElement;

public class EftBeforeLft implements ISyntaxRule {

	private List<IElementFormalism> ruleTypes;

	public EftBeforeLft() {
		ruleTypes = new ArrayList<IElementFormalism>();
		IGraphFormalism tpn = FormalismManager.getInstance().getFormalismByName("TPN").getMasterGraph();
		ruleTypes.add(tpn.getElementFormalism("transition"));
	}

	@Override
	public boolean check(IElement elt, Result result) {
		String eft = elt.getAttribute("eft").getValue();
		String lft = elt.getAttribute("lft").getValue();
		int eftv;
		try {
			eftv = Integer.parseInt(eft);
		} catch (NumberFormatException e) {
			SubResult sr = new SubResult();
			sr.addAttributeOutline(elt.getId(),"eft");
			sr.addObjectOutline(elt.getId());
			sr.addTextualResults("The attribute earliest firing time \"eft\" is not an integer.\n" +
					"It is set to value \""+ eft+"\".");
			result.addChild(sr);
			return false;
		}	
		if (lft.equals("inf"))
			return true;
		int lftv ;
		try {
			lftv = Integer.parseInt(lft);
		} catch (NumberFormatException e) {
			SubResult sr = new SubResult();
			sr.addAttributeOutline(elt.getId(),"eft");
			sr.addAttributeOutline(elt.getId(),"lft");
			sr.addObjectOutline(elt.getId());
			result.addChild(sr);
			return false;
		}	
		if (eftv > lftv) {
			SubResult sr = new SubResult();
			sr.addAttributeOutline(elt.getId(),"eft");
			sr.addAttributeOutline(elt.getId(),"lft");
			sr.addObjectOutline(elt.getId());
			result.addChild(sr);
			return false;
		}

		return true;
	}

	@Override
	public String getName() {
		return "Earliest firing time before Latest firing time.";
	}

	@Override
	public Iterable<IElementFormalism> getRuleTypes() {
		return ruleTypes;
	}

}
