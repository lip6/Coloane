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
package fr.lip6.move.coloane.projects.its.ctl;

import fr.lip6.move.coloane.projects.its.checks.CTLFormulaDescription;

public class CTLFormulaReference implements CTLFormula {

	private String formulaName;
	private CTLFormulaDescription formulaDescription;

	public CTLFormulaReference(String formulaName) {
		this.formulaName = formulaName; 
	}

	public String getFormulaName() {
		return formulaName;
	}
	
	public String getOperator() {
		return CTLFormula.REFERENCE;
	}

	public void setFormulaDescription(CTLFormulaDescription cfd) {
		this.formulaDescription = cfd;
	}
	
	@Override
	public String toString() {
		if (formulaDescription != null)
			return formulaDescription.toString();
		return "TRUE";
	}

}
