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
