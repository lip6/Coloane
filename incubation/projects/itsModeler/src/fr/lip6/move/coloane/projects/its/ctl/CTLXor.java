package fr.lip6.move.coloane.projects.its.ctl;

public class CTLXor extends CTLInfixBinop {

	public CTLXor(CTLFormula left, CTLFormula right) {
		super(left, right);
	}

	public String getOperator() {
		return CTLFormula.XOR;
	}

}
