package fr.lip6.move.coloane.projects.its.ctl;

public class CTLAnd extends CTLInfixBinop {

	public CTLAnd(CTLFormula left, CTLFormula right) {
		super(left, right);
	}

	public String getOperator() {
		return CTLFormula.AND;
	}

}
