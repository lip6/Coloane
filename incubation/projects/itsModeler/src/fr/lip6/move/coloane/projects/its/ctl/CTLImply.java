package fr.lip6.move.coloane.projects.its.ctl;

public class CTLImply extends CTLInfixBinop {

	public CTLImply(CTLFormula left, CTLFormula right) {
		super(left, right);
	}

	public String getOperator() {
		return CTLFormula.IMPLY;
	}

}
