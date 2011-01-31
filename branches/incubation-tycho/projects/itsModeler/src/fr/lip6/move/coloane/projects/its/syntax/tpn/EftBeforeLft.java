/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jean-Baptiste VORON (LIP6) - Project Head / Initial contributor
 *   Clément DÉMOULINS (LIP6) - Project Manager
 *   Yann THIERRY-MIEG (LIP6)
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.projects.its.syntax.tpn;

import fr.lip6.move.coloane.core.motor.formalisms.FormalismManager;
import fr.lip6.move.coloane.interfaces.formalism.IElementFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IGraphFormalism;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.projects.its.syntax.ISyntaxRule;
import fr.lip6.move.coloane.projects.its.syntax.Result;
import fr.lip6.move.coloane.projects.its.syntax.SubResult;

import java.util.ArrayList;
import java.util.List;


/**
 * A syntax rule to check the EFT is before LFT.
 * @author Yann
 *
 */
public final class EftBeforeLft implements ISyntaxRule {

	private List<IElementFormalism> ruleTypes;

	/**
	 * Ctor.
	 */
	public EftBeforeLft() {
		ruleTypes = new ArrayList<IElementFormalism>();
		IGraphFormalism tpn = FormalismManager.getInstance().getFormalismByName("TPN").getMasterGraph();
		ruleTypes.add(tpn.getElementFormalism("transition"));
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean check(IElement elt, Result result) {
		String eft = elt.getAttribute("eft").getValue();
		String lft = elt.getAttribute("lft").getValue();
		int eftv;
		try {
			eftv = Integer.parseInt(eft);
		} catch (NumberFormatException e) {
			SubResult sr = new SubResult();
			sr.addAttributeOutline(elt.getId(), "eft");
			sr.addObjectOutline(elt.getId());
			sr.addTextualResults("The attribute earliest firing time \"eft\" is not an integer.\n"
					+ "It is set to value \"" + eft + "\".");
			result.addChild(sr);
			return false;
		}
		if (lft.equals("inf")) {
			return true;
		}
		int lftv;
		try {
			lftv = Integer.parseInt(lft);
		} catch (NumberFormatException e) {
			SubResult sr = new SubResult();
			sr.addAttributeOutline(elt.getId(), "eft");
			sr.addAttributeOutline(elt.getId(), "lft");
			sr.addObjectOutline(elt.getId());
			result.addChild(sr);
			return false;
		}
		if (eftv > lftv) {
			SubResult sr = new SubResult();
			sr.addAttributeOutline(elt.getId(), "eft");
			sr.addAttributeOutline(elt.getId(), "lft");
			sr.addObjectOutline(elt.getId());
			result.addChild(sr);
			return false;
		}

		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getName() {
		return "Earliest firing time before Latest firing time.";
	}

	/**
	 * {@inheritDoc}
	 */
	public Iterable<IElementFormalism> getRuleTypes() {
		return ruleTypes;
	}

}
