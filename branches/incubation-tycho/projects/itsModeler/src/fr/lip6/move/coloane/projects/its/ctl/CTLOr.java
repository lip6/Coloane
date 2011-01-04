package fr.lip6.move.coloane.projects.its.ctl;

public class CTLOr extends CTLInfixBinop {

	public CTLOr(CTLFormula left, CTLFormula right) {
		super(left, right);
	}

	public String getOperator() {
		return CTLFormula.OR;
	}

}
