package fr.lip6.move.coloane.projects.its.ctl;

public class CTLEquiv extends CTLInfixBinop {

	public CTLEquiv(CTLFormula left, CTLFormula right) {
		super(left, right);
	}

	public String getOperator() {
		return CTLFormula.EQUIV;
	}

}
